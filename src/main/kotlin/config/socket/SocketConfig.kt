package org.kym8821.websocket.config.socket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker // websocket 서버 활성화
class SocketConfig():WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // 웹소켓 connect이면 당연히 socketConfig에서 origin pattern 설정을 해줘야지...
        registry
            .addEndpoint("/ws")
            .setAllowedOriginPatterns("*")
//            .setHandshakeHandler(CustomHandshakeHandler())
            .withSockJS()
            .setInterceptors(CustomHandshakeInterceptor())
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setUserDestinationPrefix("/user");
    }

    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(CustomInboundChannelConfig())
    }
}