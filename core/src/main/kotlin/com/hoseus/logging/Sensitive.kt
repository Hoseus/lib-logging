package com.hoseus.logging

/**
 * Marks a field in a bean as sensitive, and when logged it will be masked.
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Sensitive
