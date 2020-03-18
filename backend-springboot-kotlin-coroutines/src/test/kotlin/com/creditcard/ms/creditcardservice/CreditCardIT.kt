package com.creditcard.ms.creditcardservice

import com.creditcard.ms.creditcardservice.api.CreditCardApi
import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@WebFluxTest(controllers = [CreditCardApi::class])
class CreditCardIT {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `register new credit card details`() {

        //prepare
        val requestBody = RequestBody(12345, "test user")

        //test
        webTestClient.post()
                .uri("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated


    }
}