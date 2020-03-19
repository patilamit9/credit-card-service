package com.creditcard.ms.creditcardservice.api

import com.creditcard.ms.creditcardservice.api.domain.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@Component
@RestController
class GlobalExceptionHandle {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandle::class.java)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun defaultHandler(ex: Exception): ApiResponse {
        logger.error("Api error: ", ex)
        return ApiResponse(ex.message ?: "error")
    }
}