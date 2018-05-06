package com.springexamples.demo;

import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(App.class);
        app.run(args);
    }
    
    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void run() {
    	System.out.println("Current time is :: " + Calendar.getInstance().getTime());
    }
}
