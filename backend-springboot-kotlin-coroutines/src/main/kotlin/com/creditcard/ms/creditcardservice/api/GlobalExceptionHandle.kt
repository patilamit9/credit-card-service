package com.creditcard.ms.creditcardservice.api

import com.creditcard.ms.creditcardservice.api.domain.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.support.WebExchangeBindException

@ControllerAdvice
@Component
@RestController
class GlobalExceptionHandle {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandle::class.java)

    @ExceptionHandler(WebExchangeBindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun defaultHandler(ex: WebExchangeBindException): ApiResponse {
        logger.error("Api error: ", ex)
        val message = ex.bindingResult.fieldErrors
                .stream().map { it.defaultMessage }
                .findFirst().orElse(ex.message)
        return ApiResponse(message ?: "error")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationException(ex: MethodArgumentNotValidException): ApiResponse {
        logger.error("Api validation fail :: ", ex)
        val message = ex.bindingResult.fieldErrors.stream()
                .map { it.defaultMessage }.findFirst().orElse(ex.message) ?: "error"
        return ApiResponse(message)
    }
}