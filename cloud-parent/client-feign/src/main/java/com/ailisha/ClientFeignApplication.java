package com.ailisha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients//启用feign进行远程调用
@EnableDiscoveryClient
@SpringBootApplication
public class ClientFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientFeignApplication.class, args);
	}
}
