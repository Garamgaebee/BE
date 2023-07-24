package com.garamgaebee.memberserivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
public class MemberSerivceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberSerivceApplication.class, args);
	}

}
