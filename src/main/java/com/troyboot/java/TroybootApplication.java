package com.troyboot.java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.troyboot.java.**.dao")
public class TroybootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TroybootApplication.class, args);
	}
}
