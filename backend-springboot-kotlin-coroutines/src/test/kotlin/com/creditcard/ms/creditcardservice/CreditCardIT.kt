package com.creditcard.ms.creditcardservice

import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.repo.CardRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(classes = [CreditCardServiceApplication::class],
        webEnvironment = RANDOM_PORT)
class CreditCardIT {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var cardRepository: CardRepository

    @Test
    fun `register new credit card details`() {

        //prepare
        val requestBody = RequestBody("234567", "test user")

        //test
        webTestClient.post()
                .uri("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated


        //verify
        val card = cardRepository.findByCardNo("234567")
        assertNotNull(card)
        assertEquals("test user", card.cardHolderName)
        assertEquals(0.0, card.balance)
    }
}