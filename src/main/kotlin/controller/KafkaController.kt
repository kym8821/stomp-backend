package org.kym8821.websocket.controller

import org.kym8821.websocket.service.TestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController(
    val service: TestService
){
    @GetMapping("/test")
    fun test():String{
        return "test"
    }

    @GetMapping("/insert")
    fun insert(): String {
        return service.insert()
    }
}