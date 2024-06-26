package org.kym8821.websocket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = arrayOf(SecurityAutoConfiguration::class))
class WebsocketPracticeApplication

fun main(args: Array<String>) {
    runApplication<WebsocketPracticeApplication>(*args)
}