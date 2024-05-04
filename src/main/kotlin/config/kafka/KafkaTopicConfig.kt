package org.kym8821.websocket.config.kafka

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig{
    @Value("\${spring.kafka.bootstrap-servers}")
    lateinit var bootstrapServer: String

    @Bean
    fun kafkaAdmin():KafkaAdmin{
        val configs:HashMap<String, Any> = hashMapOf()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        return KafkaAdmin(configs)
    }

    @Bean
    fun newTopic():NewTopic{
        return TopicBuilder.name("test-topic").build()
    }


}