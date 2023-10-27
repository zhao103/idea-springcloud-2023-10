package com.zhaohang.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DepartConfig {

    @LoadBalanced
    @Bean//RestTemplate是spring定义发送http请求的类
	public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
