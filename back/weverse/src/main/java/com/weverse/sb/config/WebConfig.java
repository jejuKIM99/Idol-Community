package com.weverse.sb.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig { // implements WebMvcConfigurer

	// ✅ CorsFilter를 스프링 빈(Bean)으로 등록하고, 구체적인 설정을 추가합니다.
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter());
        
        // 필터가 적용될 URL 패턴을 지정합니다. "/*"는 모든 요청을 의미합니다.
        bean.addUrlPatterns("/*");
        
        // 필터의 우선순위를 가장 높게 설정하여, 다른 필터들보다 먼저 실행되도록 합니다.
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        
        return bean;
    }
	
    /*
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
		.allowedOrigins("http://localhost:3000") // Next.js 개발 서버 주소
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
		.allowedHeaders("*") // 모든 헤더 허용
		.allowCredentials(true) // 자격 증명 (쿠키, HTTP 인증 등) 허용
		.maxAge(3600); // Pre-flight 요청 캐싱 시간 (초)
	}
	*/
}