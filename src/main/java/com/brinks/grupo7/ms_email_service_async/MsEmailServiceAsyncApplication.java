package com.brinks.grupo7.ms_email_service_async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class MsEmailServiceAsyncApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsEmailServiceAsyncApplication.class, args);
	}
}
