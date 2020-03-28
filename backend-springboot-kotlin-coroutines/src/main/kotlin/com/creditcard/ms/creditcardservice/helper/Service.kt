package com.creditcard.ms.creditcardservice.helper

import com.creditcard.ms.creditcardservice.api.domain.CardResponseVO
import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.api.error.CardDetailsNotAvailable
import com.creditcard.ms.creditcardservice.entity.Card
import com.creditcard.ms.creditcardservice.repo.CardRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class Service (val cardRepository: CardRepository){

    fun register(requestBody: RequestBody) {
        val card = Card(0, requestBody.cardNo, requestBody.cardHolderName, 0.0, 0.0);
        cardRepository.save(card)
    }

    fun getAllRegisteredCreditCards(page: Int = 0, size: Int = 10): List<CardResponseVO> {
        val cardPage = cardRepository.findAll(PageRequest.of(page, size))

        if(cardPage.isEmpty){
            throw CardDetailsNotAvailable("No cards registered to system")
        }

        return cardPage.get().map {
            CardResponseVO(it.cardNo, it.cardHolderName, it.balance, it.limit)
        }.collect(Collectors.toList())
    }

}