package com.creditcard.ms.creditcardservice.repo

import com.creditcard.ms.creditcardservice.entity.Card
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface CardRepository : CrudRepository<Card, Long> {

    fun findByCardNo(cardNo: String) :Card
}