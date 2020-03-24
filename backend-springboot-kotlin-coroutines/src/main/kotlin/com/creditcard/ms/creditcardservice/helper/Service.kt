package com.creditcard.ms.creditcardservice.helper

import com.creditcard.ms.creditcardservice.api.domain.CardResponseVO
import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.entity.Card
import com.creditcard.ms.creditcardservice.repo.CardRepository
import org.springframework.stereotype.Component

@Component
class Service (val cardRepository: CardRepository){

    fun register(requestBody: RequestBody) {
        val card = Card(0, requestBody.cardNo, requestBody.cardHolderName, 0.0, 0.0);
        cardRepository.save(card)
    }

    fun getAllRegisteredCreditCards(): List<CardResponseVO> {
        val cardDetails = cardRepository.findAll()
        return cardDetails.map {
            CardResponseVO(it.cardNo, it.cardHolderName, it.balance, it.limit)
        }
    }

}