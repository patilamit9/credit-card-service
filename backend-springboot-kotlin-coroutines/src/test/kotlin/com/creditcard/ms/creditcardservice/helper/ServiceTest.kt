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
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(MockitoExtension::class)
//@ContextConfiguration(classes = [AppConfig::class])
class ServiceTest {

    //@Autowired
    private lateinit var service: Service

    @Mock
    private lateinit var cardRepository: CardRepository

    @BeforeEach
    fun init(){
        service = Service(cardRepository)
    }

    @BeforeEach
    fun clearTestData(){
        cardRepository.deleteAll()
    }

    @Test
    fun `get all registered card details`() {
        //Prepare
        val cards = listOf<Card>(Card(0, "1234567890", "test", 100.0, 200.0))
        val page = PageImpl<Card>(cards)

        `when`(cardRepository.findAll(any(Pageable::class.java))).thenReturn(page)

        //test
        val cardDetails = service.getAllRegisteredCreditCards()

        //verify
        assertNotNull(cardDetails)
        assertEquals(cards.size, cardDetails.size)
        assertEquals(cards[0].cardNo, cardDetails[0].cardNo)
        assertEquals(cards[0].cardHolderName, cardDetails[0].cardHolderName)
        assertEquals(cards[0].balance, cardDetails[0].balance)
        assertEquals(cards[0].limit, cardDetails[0].limit)
    }
}