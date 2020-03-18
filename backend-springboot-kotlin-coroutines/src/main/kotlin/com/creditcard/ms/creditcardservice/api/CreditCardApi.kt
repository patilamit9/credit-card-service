package com.creditcard.ms.creditcardservice.api

import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.helper.Service
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class CreditCardApi {

    lateinit var service: Service

    constructor(service: Service) {
        this.service = service
    }

    @PostMapping(value = ["register"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun register(@org.springframework.web.bind.annotation.RequestBody
                             requestBody: RequestBody): Unit {
        service.register(requestBody)
    }

}
