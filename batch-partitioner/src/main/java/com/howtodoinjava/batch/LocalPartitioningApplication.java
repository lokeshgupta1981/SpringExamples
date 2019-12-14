package com.howtodoinjava.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class LocalPartitioningApplication implements CommandLineRunner
{
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	
	public static void main(String[] args) {
		SpringApplication.run(LocalPartitioningApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		System.out.println("STATUS STARTED===================");
		
		JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobId", String.valueOf(System.currentTimeMillis()))
				.addDate("date", new Date())
                .addLong("time",System.currentTimeMillis()).toJobParameters();
		
		JobExecution execution = jobLauncher.run(job, jobParameters);

		System.out.println("STATUS :: "+execution.getStatus());
	}
}
