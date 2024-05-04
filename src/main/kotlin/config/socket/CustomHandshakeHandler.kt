package org.kym8821.websocket.config.socket

import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import java.security.Principal
import java.util.UUID

class CustomHandshakeHandler:DefaultHandshakeHandler(){
    override fun determineUser(
        request: ServerHttpRequest,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Principal {
        val uuid = UUID.randomUUID()
        println(uuid)
        return UsernamePasswordAuthenticationToken(uuid, null)
    }
}