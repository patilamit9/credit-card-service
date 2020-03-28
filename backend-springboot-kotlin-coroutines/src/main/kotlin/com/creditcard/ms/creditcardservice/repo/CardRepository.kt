package com.creditcard.ms.creditcardservice.repo

import com.creditcard.ms.creditcardservice.entity.Card
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

interface CardRepository : PagingAndSortingRepository<Card, Long> {

    fun findByCardNo(cardNo: String) :Card
}