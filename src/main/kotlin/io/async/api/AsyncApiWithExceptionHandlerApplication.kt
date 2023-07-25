package io.async.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
@Suppress("UtilityClassWithPublicConstructor")
open class AsyncApiWithExceptionHandlerApplication {

    @Bean
    open fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper().apply {
            registerKotlinModule()
            registerModule(JavaTimeModule())
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<AsyncApiWithExceptionHandlerApplication>(args = args)
        }
    }
}
