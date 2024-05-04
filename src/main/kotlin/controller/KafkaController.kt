package org.kym8821.websocket.controller

import org.kym8821.websocket.domain.UserMessage
import org.kym8821.websocket.service.KafkaProducer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController(
    val service: KafkaProducer
){
    @GetMapping("/test")
    fun test():String{
        return "test"
    }

    @GetMapping("/insert")
    fun insert() :String{
        val userMessage = UserMessage("name", "message")
        service.sendToUser(userMessage)
        return "success"
    }
}