package org.kym8821.websocket.config.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.kym8821.websocket.domain.UserMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

// application.yml에서 해주던 producer 설정을 코드로 해준거임
@Configuration
class KafkaProducerConfig {
    @Value(value = "\${spring.kafka.bootstrap-servers}")
    lateinit var bootstrapServer:String

    @Bean
    fun producerFactory(): ProducerFactory<String, UserMessage> {
        val config:HashMap<String, Any> = hashMapOf()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        return DefaultKafkaProducerFactory(config, StringSerializer(), JsonSerializer())
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, UserMessage> {
        return KafkaTemplate(producerFactory())
    }
}