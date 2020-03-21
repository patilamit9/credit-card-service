package com.creditcard.ms.creditcardservice.api.util

import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class LuhnValidation : ConstraintValidator<ValidCreditCardNumber, String>{

    var min: Int = Int.MIN_VALUE
    var max: Int = Int.MAX_VALUE

    override fun initialize(constraintAnnotation: ValidCreditCardNumber) {
        min = constraintAnnotation.min
        max = constraintAnnotation.max
    }

    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        if(value.length <= min && value.length >= max)
            return false

        var second = false
        var isValid = true
        var total = 0
        val chars = value.toCharArray()
        for (i in chars.size - 1 downTo 0){
            var d = chars.get(i) - '0'

            if (d > 9) {
                isValid = false
                break
            }
            if (second) {
                d *= 2

                var final = d / 10
                final += d % 10
                d = final
            }
            total += d
            second = !second
        }

        return total % 10 == 0 && isValid
    }
}