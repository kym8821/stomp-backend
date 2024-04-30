package org.kym8821.websocket.config

import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import java.security.Principal

class CustomInboundChannelConfig:ChannelInterceptor {
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*> {
        val accessor:StompHeaderAccessor? = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)
        val user = accessor?.getNativeHeader("user")?.get(0)
        if(accessor!=null && accessor.command == StompCommand.CONNECT){
            val sessionAttributes = accessor.sessionAttributes
            if(sessionAttributes!=null) sessionAttributes["user"] = user
            val principal = UsernamePasswordAuthenticationToken(user, null)
            accessor.user = principal
        }else{
            val principal:Principal? = accessor?.user
            println("session user name : " + principal?.name)
        }
        return message
    }
}