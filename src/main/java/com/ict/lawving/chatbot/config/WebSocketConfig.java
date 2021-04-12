package com.ict.lawving.chatbot.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	@Autowired
	private EchoHandler echoHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(echoHandler, "/echo").setAllowedOrigins("*").withSockJS()
		.setInterceptors(new HttpSessionHandshakeInterceptor())
		.setClientLibraryUrl("http://@203.236.220.89:8090/resources/js/sockjs.min.js");
	}
	
}