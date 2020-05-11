package com.stripe.interview

import org.junit.Assert
import org.junit.Test

class MainTest {
    @Test
    @Throws(Exception::class)
    fun testUseGuavaForSomeReason() {
        val actual = "hi;"
        Assert.assertEquals("hi;", actual)
    }
}