package com.creditcard.ms.creditcardservice.api.domain

import javax.validation.constraints.NotNull

data class RequestBody (
        @NotNull
        val cardNo: Int,
        @NotNull
        val cardHolderName: String)
