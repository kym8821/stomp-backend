package org.kym8821.websocket.service

import org.kym8821.websocket.domain.UserMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, UserMessage>
){
    public fun sendToUser(msg:UserMessage){
        val future = kafkaTemplate.send("test-topic", msg)
        future.whenComplete { res, ex ->
            if(ex == null){
                val offset = res.recordMetadata.offset()
                val partition = res.recordMetadata.partition()
                println("success ( partition:${partition} , offset:${offset} )")
            }else{
                println("error messgae : " + ex.message)
            }
        }
    }
}