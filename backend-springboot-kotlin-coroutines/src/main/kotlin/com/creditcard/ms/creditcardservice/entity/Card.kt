package com.creditcard.ms.creditcardservice.entity

import javax.persistence.*

@Entity
data class Card (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    @Column(name = "card_number")
    var cardNo: String,
    @Column(name = "card_holder_name")
    var cardHolderName: String,
    @Column(name = "credit_balance")
    var balance: Double,
    @Column(name = "credit_limit")
    var limit: Double
)