package com.garamgaebee.member.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients(basePackages = {"com.garamgaebee.*"})
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = { "com.garamgaebee"})
@SpringBootApplication(scanBasePackages = { "com.garamgaebee"})
@EntityScan(basePackages = { "com.garamgaebee"})
@EnableJpaAuditing
public class MemberServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MemberServiceApplication.class, args);
	}

}
