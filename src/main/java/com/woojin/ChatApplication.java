 package com.woojin;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.woojin.mapper.MemberMapper;

@EnableCaching



@SpringBootApplication
@MapperScan(basePackageClasses = MemberMapper.class)
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}
	
 

}
