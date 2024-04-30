import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain{
        http.cors {  }
            .csrf{csrf->csrf.disable()}
            .authorizeHttpRequests { req -> req.requestMatchers("/ws/**").hasAnyRole()}
        return http.build()
    }
}