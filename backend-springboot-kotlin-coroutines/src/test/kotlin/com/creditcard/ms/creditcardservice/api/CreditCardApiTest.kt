package com.creditcard.ms.creditcardservice.api

import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.helper.Service
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.doNothing
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@WebFluxTest(CreditCardApi::class)
class CreditCardApiTest {
    private val logger = LoggerFactory.getLogger(CreditCardApiTest::class.java)

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var service: Service;

    @Test
    fun `register new credit card`(){
        //prepare
        val requestBody = RequestBody("234567", "test user")

        //mock
        doNothing().`when`(service).register(requestBody)

        //test
        webTestClient.post()
                .uri("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated
    }

    @Test
    fun `register new credit card without body`(){

        //test
        webTestClient.post()
                .uri("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isBadRequest
                .expectBody()
                .consumeWith { print(it) }
    }

    @Test
    fun `register new credit card with invalid credit card number Luhn rule incompatible`(){
        //prepare
        val requestBody = RequestBody("1234567", "test user")

        //mock
        doNothing().`when`(service).register(requestBody)

        //test
        webTestClient.post()
                .uri("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isBadRequest
                .expectBody()
                .consumeWith { print(it) }
                .jsonPath("$.status").isEqualTo("failure")
                .jsonPath("$.error").isEqualTo("Invalid credit card")
    }

}