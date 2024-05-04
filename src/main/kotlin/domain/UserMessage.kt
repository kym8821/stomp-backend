package org.kym8821.websocket.domain

import java.io.Serializable

// 메세지가 직렬화될 수 있도록 Serializable 인터페이스 상속
class UserMessage(
    val username: String?,
    val message: String?
):Serializable