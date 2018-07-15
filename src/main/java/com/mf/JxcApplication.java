package com.mf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JxcApplication {

	public static void main(String[] args) {
		System.out.println("正在启动。。。。。。");
		SpringApplication.run(JxcApplication.class, args);
		System.out.println("已经启动。。。。。。");
	}
}
