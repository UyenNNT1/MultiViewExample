package com.example.multiviewexample

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class MyComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest(){
        composeTestRule.setContent {

        }
    }
}