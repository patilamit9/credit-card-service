package com.creditcard.ms.creditcardservice.api

import com.creditcard.ms.creditcardservice.api.domain.CardResponseVO
import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.helper.Service
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    @Validated requestBody: RequestBody): Unit {
        service.register(requestBody)
    }

    @GetMapping(value = ["all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getAllRegisteredCreditCards(): List<CardResponseVO> {
        return service.getAllRegisteredCreditCards()
    }

}
