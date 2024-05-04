package org.kym8821.websocket.config.socket

import jakarta.servlet.http.HttpSession
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor
import java.lang.Exception
import java.security.Principal
import java.util.UUID


class CustomHandshakeInterceptor:HandshakeInterceptor{
    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        if( request is ServletServerHttpRequest){
            val servletRequest:ServletServerHttpRequest = request
            val session:HttpSession = servletRequest.servletRequest.session
            val sessionId = session.id
            attributes["sessionId"] = sessionId
        }
        return true
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        exception: Exception?
    ) {
        // 핸드 쉐이크 종료 후 실행할 로직들
    }
}