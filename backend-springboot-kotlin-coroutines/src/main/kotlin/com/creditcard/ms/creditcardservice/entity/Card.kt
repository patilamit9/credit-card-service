package com.creditcard.ms.creditcardservice.entity

class Card {
    private var cardNo: String
    private var cardHolderName: String
    private var balance: Double
    private var limit: Double

    constructor(cardNo: String, cardHolderName: String, balance: Double = 0.0, limit: Double = 0.0) {
        this.cardNo = cardNo
        this.cardHolderName = cardHolderName
        this.balance = balance
        this.limit = limit
    }
}