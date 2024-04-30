package org.kym8821.websocket.service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.function.BiConsumer

@Service
class TestService(
    val kafkaTemplate: KafkaTemplate<String, String>
){
    public fun insert():String{
        val future = kafkaTemplate.send("test-topic", null, LocalDateTime.now().toString())
        future.whenComplete { res, ex ->
            if(ex == null){
                println("success")
                println(res.recordMetadata.offset())
                println(res.recordMetadata.partition())
            }else{
                println("error messgae : " + ex.message)
            }
        }
        return "{}"
    }
}