import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebCorsConfig: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/ws/**")
            .allowedOrigins("http://localhost:3000")
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowCredentials(true)
    }
}
