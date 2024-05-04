package org.kym8821.websocket.config.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.kym8821.websocket.domain.UserMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

// application.yml에서 해주던 consumer 설정을 코드로 해준거임
@Configuration
class KafkaConsumerConfig {
    @Value(value = "\${spring.kafka.bootstrap-servers}")
    lateinit var bootstrapServer:String

    @Bean
    fun consumerFactory():ConsumerFactory<String, UserMessage>{
        // 역직렬화 시 타입을 명시적으로 지정해야 함
        val deserializer = JsonDeserializer(UserMessage::class.java)
        deserializer.trustedPackages("*")
        deserializer.setRemoveTypeHeaders(false)
        deserializer.setUseTypeMapperForKey(true)

        val config:HashMap<String, Any> = hashMapOf()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        return DefaultKafkaConsumerFactory(config, StringDeserializer(), deserializer)
    }

    @Bean
    fun kafkaListenerContainerFactory():ConcurrentKafkaListenerContainerFactory<String, UserMessage>{
        val factory = ConcurrentKafkaListenerContainerFactory<String, UserMessage>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}