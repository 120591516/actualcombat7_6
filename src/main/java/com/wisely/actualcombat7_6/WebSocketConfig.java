package com.wisely.actualcombat7_6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
/*
 * @EnableWebSocketMessageBroker
 * 通过@EnableWebSocketMessageBroker注解开启使用STOMP协议来传输基于代理 （message
 * broker）的消息，这时控制器支持使用@MessageMapping，就像是使用@RequestMapping一样
 */
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer implements WebSocketConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {// 注册STOMP的协议节点（endpoint），并映射指定url
		// 注册一个STOMP的endpoint，并指定使用SockJS协议
		registry.addEndpoint("endpointWisely").withSockJS();

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {// 配置消息代理
		// 广播式应配置一个topic消息代理
		registry.enableSimpleBroker("/topic");
	}

	public WebSocketConfig() {
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(systemWebSocketHandler(), "/websck").addInterceptors(new HandshakeInterceptor());

		System.out.println("registed!");
		registry.addHandler(systemWebSocketHandler(), "/sockjs/websck").addInterceptors(new HandshakeInterceptor())
				.withSockJS();

	}

	@Bean
	public WebSocketHandler systemWebSocketHandler() {
		// return new InfoSocketEndPoint();
		return new SystemWebSocketHandler();
	}
}