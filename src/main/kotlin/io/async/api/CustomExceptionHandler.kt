package io.async.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.ZonedDateTime

@ControllerAdvice
class CustomExceptionHandler {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<String> {

        val error = Error(
            details = ErrorDetails(
                "CustomExceptionHandler has intercepted an error: ${e.message}",
                "io.async.api.CustomExceptionHandler"
            ),
            dateTime = ZonedDateTime.now()
        )

        // Consider to use try/catch for below
        val serializedErrorResponse = objectMapper.writeValueAsString(error)

        val headers = HttpHeaders()
        headers[HttpHeaders.CONTENT_TYPE] = listOf(MediaType.APPLICATION_JSON_VALUE)

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .headers(headers)
            .body(serializedErrorResponse)
    }
}

internal data class ErrorDetails(val message: String?, val source: String)

internal data class Error(val details: ErrorDetails, val dateTime: ZonedDateTime)