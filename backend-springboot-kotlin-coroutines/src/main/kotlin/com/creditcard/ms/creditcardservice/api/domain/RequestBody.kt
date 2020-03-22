package com.creditcard.ms.creditcardservice.api.domain

import com.creditcard.ms.creditcardservice.api.util.ValidCreditCardNumber
import javax.validation.constraints.NotNull

data class RequestBody (
        @NotNull
        @ValidCreditCardNumber
        val cardNo: String,
        @NotNull
        val cardHolderName: String)
