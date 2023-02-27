package com.example.harrypotterstudents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harrypotterstudents.data.model.Student
import com.example.harrypotterstudents.ui.students.StudentScreen
import com.example.harrypotterstudents.ui.students.Students
import com.example.harrypotterstudents.ui.students.StudentsViewModel
import com.example.harrypotterstudents.ui.theme.HarryPotterStudentsTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarryPotterStudentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = koinViewModel<StudentsViewModel>()
                    StudentScreen(studentsViewModel = viewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HarryPotterStudentsTheme {
        Students(
            students = arrayListOf(
                Student(
                    name = "one",
                    dateOfBirth = "120690"
                ),
                Student(
                    name = "two"
                ),
                Student(
                    name = "three"
                )
            )
        , null)
    }
}