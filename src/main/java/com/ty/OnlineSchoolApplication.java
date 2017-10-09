package com.ty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class OnlineSchoolApplication {
	@Value("${com.ty.name}")
	private String name;
	@Value("${com.ty.i}")
	private int i;


	@RequestMapping("/")
	public String index(){
		return "hello "+name+", springboot ."+i;
	}
	public static void main(String[] args) {
		SpringApplication.run(OnlineSchoolApplication.class, args);
	}
}
