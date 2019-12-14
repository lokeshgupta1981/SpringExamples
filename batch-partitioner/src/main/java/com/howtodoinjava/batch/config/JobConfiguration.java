package com.howtodoinjava.batch.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.howtodoinjava.batch.mapper.CustomerRowMapper;
import com.howtodoinjava.batch.model.Customer;
import com.howtodoinjava.batch.partitioner.ColumnRangePartitioner;

@Configuration
public class JobConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Bean
	public ColumnRangePartitioner partitioner() 
	{
		ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();
		columnRangePartitioner.setColumn("id");
		columnRangePartitioner.setDataSource(dataSource);
		columnRangePartitioner.setTable("customer");
		return columnRangePartitioner;
	}

	@Bean
	@StepScope
	public JdbcPagingItemReader<Customer> pagingItemReader(
			@Value("#{stepExecutionContext['minValue']}") Long minValue,
			@Value("#{stepExecutionContext['maxValue']}") Long maxValue) 
	{
		System.out.println("reading " + minValue + " to " + maxValue);

		Map<String, Order> sortKeys = new HashMap<>();
		sortKeys.put("id", Order.ASCENDING);
		
		MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		queryProvider.setSelectClause("id, firstName, lastName, birthdate");
		queryProvider.setFromClause("from customer");
		queryProvider.setWhereClause("where id >= " + minValue + " and id < " + maxValue);
		queryProvider.setSortKeys(sortKeys);
		
		JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(1000);
		reader.setRowMapper(new CustomerRowMapper());
		reader.setQueryProvider(queryProvider);
		
		return reader;
	}
	
	
	@Bean
	@StepScope
	public JdbcBatchItemWriter<Customer> customerItemWriter()
	{
		JdbcBatchItemWriter<Customer> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO NEW_CUSTOMER VALUES (:id, :firstName, :lastName, :birthdate)");

		itemWriter.setItemSqlParameterSourceProvider
			(new BeanPropertyItemSqlParameterSourceProvider<>());
		itemWriter.afterPropertiesSet();
		
		return itemWriter;
	}
	
	// Master
	@Bean
	public Step step1() 
	{
		return stepBuilderFactory.get("step1")
				.partitioner(slaveStep().getName(), partitioner())
				.step(slaveStep())
				.gridSize(4)
				.taskExecutor(new SimpleAsyncTaskExecutor())
				.build();
	}
	
	// slave step
	@Bean
	public Step slaveStep() 
	{
		return stepBuilderFactory.get("slaveStep")
				.<Customer, Customer>chunk(1000)
				.reader(pagingItemReader(null, null))
				.writer(customerItemWriter())
				.build();
	}
	
	@Bean
	public Job job() 
	{
		return jobBuilderFactory.get("job")
				.start(step1())
				.build();
	}
}