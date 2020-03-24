package com.creditcard.ms.creditcardservice.api.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class CardResponseVO (
        @JsonProperty("card_number")
        val cardNo: String,
        @JsonProperty("holder_name")
        val cardHolderName: String,
        val balance: Double,
        val limit: Double
)
