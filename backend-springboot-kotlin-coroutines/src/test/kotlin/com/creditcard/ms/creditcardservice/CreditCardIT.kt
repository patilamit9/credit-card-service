package com.creditcard.ms.creditcardservice

import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.entity.Card
import com.creditcard.ms.creditcardservice.repo.CardRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
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

    @BeforeEach
    fun clearTestData(){
        cardRepository.deleteAll()
    }

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

    @Test
    fun `get all registered credit card details`() {
        //Prepare
        val card = Card(0, "1234567890", "test", 100.0, 200.0)
        cardRepository.save(card)

        //test
        webTestClient.get()
                .uri("/api/v1/all")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .consumeWith { print(it) }
                .jsonPath("$[0].card_number").isEqualTo("1234567890")
                .jsonPath("$[0].holder_name").isEqualTo("test")
                .jsonPath("$[0].balance").isEqualTo(100.0)
                .jsonPath("$[0].limit").isEqualTo(200.0)

    }

}