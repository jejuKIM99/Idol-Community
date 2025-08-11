package com.weverse.sb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.portone.sdk.server.PortOneClient;

@Configuration
public class PortOneConfig {
	
	@Value("${portone.api-secret}")
	private String apiSecret; // 콘솔의 API Secret

	@Value("${portone.base-url}")
	private String baseUrl; // https://api.portone.io
	
	@Value("${portone.store-id}")
	private String storeId;

    @Bean
    PortOneClient portOneClient() {
        return new PortOneClient(apiSecret, baseUrl, storeId);
    }
    
}