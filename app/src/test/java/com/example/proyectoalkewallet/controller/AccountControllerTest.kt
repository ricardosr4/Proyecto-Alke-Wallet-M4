package com.example.proyectoalkewallet.controller

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AccountControllerTest {

    @Test
    fun depositAndWithdrawPreserveAccountRules() {
        val controller = AccountController()
        val initialBalance = controller.getBalance()

        assertFalse(controller.deposit(null))
        assertFalse(controller.deposit(" "))
        assertFalse(controller.deposit("invalid"))
        assertFalse(controller.deposit("-10"))
        assertEquals(initialBalance, controller.getBalance(), 0.0)

        assertTrue(controller.deposit("10,50"))
        assertEquals(initialBalance + 10.50, controller.getBalance(), 0.0)

        assertTrue(controller.withdraw("5.25"))
        assertEquals(initialBalance + 5.25, controller.getBalance(), 0.0)

        assertFalse(controller.withdraw((controller.getBalance() + 1).toString()))
        assertEquals(initialBalance + 5.25, controller.getBalance(), 0.0)
    }
}
