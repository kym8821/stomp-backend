package org.kym8821.websocket.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.addAllowedOriginPattern("*") // 모든 출처 허용
        config.addAllowedHeader("*") // 모든 헤더 허용
        config.addAllowedMethod("*") // 모든 HTTP 메소드 허용
        config.setAllowCredentials(true)
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}