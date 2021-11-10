package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
// XML(채팅 제어)

import com.sist.web.ChatHandler;
// ws://locahost:8080/web/chat-ws
@Configuration
// 활성화 
@EnableWebSocket
public class ChatConfig implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		System.out.println("1. registerWebSocketHandlers 채팅 동작!!");
		registry.addHandler(chatHandler(), "/chat-ws.do");
	}
	
	@Bean
	public ChatHandler chatHandler()
	{
		System.out.println("2. ChatHandler생성!!");
		return new ChatHandler();
	}

}
