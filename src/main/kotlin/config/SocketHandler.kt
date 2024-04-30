package org.kym8821.websocket.config

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class SocketHandler():TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val messageValue = message.payload
        val textMessage:TextMessage = TextMessage(messageValue)
        session.sendMessage(textMessage)
    }
}