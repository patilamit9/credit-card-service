package com.creditcard.ms.creditcardservice.helper

import com.creditcard.ms.creditcardservice.AppConfig
import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.entity.Card
import com.creditcard.ms.creditcardservice.repo.CardRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [AppConfig::class])
class ServiceTest {

    @Autowired
    private lateinit var service: Service

    @Autowired
    private lateinit var cardRepository: CardRepository

    @BeforeEach
    fun clearTestData(){
        cardRepository.deleteAll()
    }

    @Test
    fun `register card`() {
        //Prepare
        val requestBody = RequestBody("123456", "test test")

        //test
        service.register(requestBody)

        //verify
        val card = cardRepository.findByCardNo("123456")
        assertNotNull(card)
    }

    @Test
    fun `get all registered card details`() {
        //Prepare
        val card = Card(0, "1234567890", "test", 100.0, 200.0)
        cardRepository.save(card)

        //test
        val cardDetails = service.getAllRegisteredCreditCards()

        //verify
        assertNotNull(cardDetails)
        assertEquals(1, cardDetails.size)
        assertEquals("1234567890", cardDetails[0].cardNo)
        assertEquals("test", cardDetails[0].cardHolderName)
        assertEquals(100.0, cardDetails[0].balance)
        assertEquals(200.0, cardDetails[0].limit)
    }
}