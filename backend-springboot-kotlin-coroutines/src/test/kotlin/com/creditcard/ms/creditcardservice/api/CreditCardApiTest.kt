package com.creditcard.ms.creditcardservice.api

import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.entity.Card
import com.creditcard.ms.creditcardservice.helper.Service
import com.creditcard.ms.creditcardservice.repo.CardRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(CreditCardApi::class)
@Import(value = [Service::class])
class CreditCardApiTest {
    private val logger = LoggerFactory.getLogger(CreditCardApiTest::class.java)

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var cardRepository: CardRepository

    @Test
    fun `register new credit card`(){
        //prepare
        val requestBody = RequestBody("234567", "test user")

        //mock
        `when`(cardRepository.save(any(Card::class.java))).thenReturn(Card(0, "234567", "test user", 0.0, 0.0))

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