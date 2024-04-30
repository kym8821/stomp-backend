package org.kym8821.websocket.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.kym8821.websocket.domain.Message
import org.kym8821.websocket.domain.UserMessage
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessageType
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import java.security.Principal
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.messaging.simp.stomp.StompHeaderAccessor

@Controller
class SocketController(
    val template: SimpMessagingTemplate,
    val mapper:ObjectMapper = jacksonObjectMapper()
){
    fun createMessageHeaders(
        accessor: SimpMessageHeaderAccessor,
        principal:Principal
    ):MessageHeaders{
        val simpHeaderAccessor:SimpMessageHeaderAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE)
        simpHeaderAccessor.setLeaveMutable(true)
        simpHeaderAccessor.sessionId = accessor.sessionId
        simpHeaderAccessor.user = principal
        return simpHeaderAccessor.messageHeaders
    }

    @MessageMapping("/notice")
    fun sendNotice(@Payload message: Message, principal: Principal){
        print("sendTO : ")
        println(principal)
        template.convertAndSend("/topic/notice",message)
    }

    @MessageMapping("/sendToUser")
    fun sendMessageToUser(@Payload message: UserMessage, principal: Principal, accessor: SimpMessageHeaderAccessor){
        print("sendToUser : ")
        println(principal.name)
        println("receiver : " + message.username)
        println("message : " + message.message)
        template.convertAndSendToUser(message.username, "/queue/sendToUser", message, createMessageHeaders(accessor, principal))
    }
}