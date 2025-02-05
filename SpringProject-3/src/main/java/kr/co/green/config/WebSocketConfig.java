package kr.co.green.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker


public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/chatroom", "queue"); // 클라이언트가 메시지를 구독할 경로
        config.setApplicationDestinationPrefixes("/chat/app"); // 클라이언트가 메시지를 보낼 때 사용하는 경로
    }
	 @Override
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	        // 클라이언트가 WebSocket에 연결할 엔드포인트 설정
	        registry.addEndpoint("/chat-socket")
	                .setAllowedOriginPatterns("*") // CORS 허용
	                .withSockJS(); // SockJS 지원
	    }
}
