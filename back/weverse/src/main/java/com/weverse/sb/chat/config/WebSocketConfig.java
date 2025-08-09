package com.weverse.sb.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.weverse.sb.chat.handler.ChatHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

	private final ChatHandler chatHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		// setAllowedOrigins("*") 는 웹소켓 cors 정책으로 인해, 허용 도메인을 지정해야함
		registry.addHandler(chatHandler, "/ws/api/artistSNS/home/live").setAllowedOrigins("*");
		
	}
	
}
