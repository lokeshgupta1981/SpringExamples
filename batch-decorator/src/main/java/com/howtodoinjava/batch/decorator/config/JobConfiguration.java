package com.howtodoinjava.batch.decorator.config;

import com.howtodoinjava.batch.decorator.aggregator.CustomLineAggregator;
import com.howtodoinjava.batch.decorator.classifier.CustomerClassifier;
import com.howtodoinjava.batch.decorator.mapper.CustomerRowMapper;
import com.howtodoinjava.batch.decorator.model.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JobConfiguration {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private PlatformTransactionManager transactionManager;

  @Autowired
  private DataSource dataSource;

  @Bean
  public JdbcPagingItemReader<Customer> customerPagingItemReader() {

    // reading database records using JDBC in a paging fashion

    JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();
    reader.setDataSource(this.dataSource);
    reader.setFetchSize(1000);
    reader.setRowMapper(new CustomerRowMapper());

    // Sort Keys
    Map<String, Order> sortKeys = new HashMap<>();
    sortKeys.put("id", Order.ASCENDING);

    // 	MySQL implementation of a PagingQueryProvider using database specific features.

    MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
    queryProvider.setSelectClause("id, firstName, lastName, birthdate");
    queryProvider.setFromClause("from customer");
    queryProvider.setSortKeys(sortKeys);
    reader.setQueryProvider(queryProvider);
    return reader;
  }

  @Bean
  public FlatFileItemWriter<Customer> jsonItemWriter() throws Exception {

    String customerOutputPath = File.createTempFile("customerOutput", ".out").getAbsolutePath();
    System.out.println(">> Output Path = " + customerOutputPath);
    FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
    writer.setLineAggregator(new CustomLineAggregator());
    writer.setResource(new FileSystemResource(customerOutputPath));
    writer.afterPropertiesSet();
    return writer;
  }

  @Bean
  public StaxEventItemWriter<Customer> xmlItemWriter() throws Exception {

    String customerOutputPath = File.createTempFile("customerOutput", ".out").getAbsolutePath();
    System.out.println(">> Output Path = " + customerOutputPath);
    Map<String, Class> aliases = new HashMap<>();
    aliases.put("customer", Customer.class);
    XStreamMarshaller marshaller = new XStreamMarshaller();
    marshaller.setAliases(aliases);

    // StAX and Marshaller for serializing object to XML.
    StaxEventItemWriter<Customer> writer = new StaxEventItemWriter<>();
    writer.setRootTagName("customers");
    writer.setMarshaller(marshaller);
    writer.setResource(new FileSystemResource(customerOutputPath));
    writer.afterPropertiesSet();
    return writer;
  }

  @Bean
  public ClassifierCompositeItemWriter<Customer> classifierCustomerCompositeItemWriter() throws Exception {
    ClassifierCompositeItemWriter<Customer> compositeItemWriter = new ClassifierCompositeItemWriter<>();
    compositeItemWriter.setClassifier(new CustomerClassifier(xmlItemWriter(), jsonItemWriter()));
    return compositeItemWriter;
  }

  @Bean
  public Step step1() throws Exception {
    return new StepBuilder("step1", jobRepository)
        .<Customer, Customer>chunk(10, transactionManager)
        .reader(customerPagingItemReader())
        .writer(classifierCustomerCompositeItemWriter())
        .stream(xmlItemWriter())
        .stream(jsonItemWriter())
        .build();
  }

  @Bean
  public Job job() throws Exception {
    return new JobBuilder("job", jobRepository)
        .start(step1())
        .build();
  }

}