package com.example.takehome;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TakehomeApplication {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		SpringApplication.run(TakehomeApplication.class, args);
	}
	
}
