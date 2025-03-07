package com.aurionpro.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aurionpro.spring.entity.Computer;
import com.aurionpro.spring.entity.Harddisk;

@Configuration
public class AppConfig 
{
	
	@Bean
	Computer computer()
	{
		return new Computer();
	}
	
	@Bean
	Harddisk harddisk()
	{
		return new Harddisk();
	}
}
