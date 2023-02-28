package com.example.harrypotterstudents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.harrypotterstudents.data.model.Student
import com.example.harrypotterstudents.ui.navigation.BottomNav
import com.example.harrypotterstudents.ui.navigation.NavGraph
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
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val viewModel = koinViewModel<StudentsViewModel>()
                    StudentsListScreen()//studentsViewModel = viewModel, )
//                }
            }
        }
    }
}

@Composable
fun StudentsListScreen() {
    val navController = rememberNavController()
    val   studentsViewModel= koinViewModel<StudentsViewModel>()
  //  val   showListingViewModel: SearchShowsViewModel = hiltViewModel()

    Scaffold(
        bottomBar = { BottomNav(navController = navController) },
        content = { padding -> Column(modifier = Modifier.padding(padding)){
            NavGraph(navController = navController,
                studentsViewModel=studentsViewModel,
              //  showListingViewModel=showListingViewModel
            )
        } },
        backgroundColor = androidx.compose.material.MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    )
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    HarryPotterStudentsTheme {
//        Students(
//            students = arrayListOf(
//                Student(
//                    name = "one",
//                    dateOfBirth = "120690"
//                ),
//                Student(
//                    name = "two"
//                ),
//                Student(
//                    name = "three"
//                )
//            )
//        , navController = null
//        )
//    }
//}