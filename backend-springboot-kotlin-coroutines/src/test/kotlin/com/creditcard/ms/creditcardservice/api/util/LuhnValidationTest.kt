package com.creditcard.ms.creditcardservice.api.util

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LuhnValidationTest {

    @Autowired
    private lateinit var luhnValidation: LuhnValidation

    @Test
    fun `verify credit card number`() {
        assertTrue(luhnValidation.isValid("234567", null))
    }

    @Test
    fun `verify invalid credit card number`() {
        assertFalse(luhnValidation.isValid("1234567", null))
    }

    @Test
    fun `verify non numeric credit card number`() {
        assertFalse(luhnValidation.isValid("1234567qwq", null))
    }

    @Test
    fun `verify length of credit card number, min 16 and max 19`() {
        luhnValidation.min = 16
        luhnValidation.max = 19
        assertFalse(luhnValidation.isValid("1234567", null))
    }
}