package io.async.api.controllers

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@RequestMapping("/api")
class HelloWorldAsyncController {

    @GetMapping("/sync")
    fun runSync(crack: Boolean): String {
        if(crack) {
            throw TestException("Thrown from sync controller function")
        }
        return "Sync endpoint result"
    }

    @GetMapping("/async")
    suspend fun runAsync(crack: Boolean): String {

        delay(TEST_TASK_DELAY) // Simulate an asynchronous operation

        if(crack) {
            throw TestException("Thrown from async controller function")
        }
        return "Sync endpoint result"
    }

    companion object {
        private const val TEST_TASK_DELAY: Long = 5000
    }
}

internal class TestException(message: String): Throwable(message)