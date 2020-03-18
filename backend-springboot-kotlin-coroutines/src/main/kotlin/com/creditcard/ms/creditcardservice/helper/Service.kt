package com.creditcard.ms.creditcardservice.helper

import com.creditcard.ms.creditcardservice.api.domain.RequestBody
import com.creditcard.ms.creditcardservice.entity.Card
import org.springframework.stereotype.Component

@Component
class Service {

    fun register(requestBody: RequestBody) {
        val card = Card(cardNo = requestBody.cardNo, cardHolderName = requestBody.cardHolderName);

    }

}