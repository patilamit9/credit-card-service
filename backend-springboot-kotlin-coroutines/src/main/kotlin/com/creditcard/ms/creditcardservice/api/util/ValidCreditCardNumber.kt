package com.creditcard.ms.creditcardservice.api.util

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [LuhnValidation::class])
annotation class ValidCreditCardNumber (val message: String = "Invalid credit card",
                                        val groups: Array<KClass<out Any>> = [],
                                        val payload: Array<KClass<out Any>> = [],
                                        val min: Int = 16,
                                        val max: Int = 19)