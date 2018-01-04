package com.kumarvikas1.core.assets.app;

/**
 * Created by vikakumar on 12/31/17.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.kumarvikas1")
public class BankingApp {

	public static void main(String[] args) {
		SpringApplication.run(BankingApp.class, args);
	}
}
