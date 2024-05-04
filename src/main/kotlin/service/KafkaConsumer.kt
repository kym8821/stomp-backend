package org.kym8821.websocket.service

import org.kym8821.websocket.domain.UserMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class KafkaConsumer(
    val template: SimpMessagingTemplate
){
    @KafkaListener(topics = ["test-topic"], groupId = "foo")
    fun consume(@Payload msg: UserMessage){
        println("consume ${msg.username} ${msg.message}")
        if(msg.username == null || msg.message == null) return
        return template.convertAndSendToUser(msg.username, "/queue/sendToUser", msg.message)
    }
}