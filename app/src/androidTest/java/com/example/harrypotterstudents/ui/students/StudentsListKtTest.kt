package com.example.harrypotterstudents.ui.students

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class StudentsListKtTest {


    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun studentScreen() {
        composeTestRule.setContent {
            studentScreen()
        }
        composeTestRule.onNodeWithText("")
            .assertIsDisplayed()
    }

    @Test
    fun students() {
    }

    //is cliqued
    @Test
    fun studentItem() {
    }

}