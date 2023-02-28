package com.example.harrypotterstudents.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.harrypotterstudents.data.model.Student
import com.example.harrypotterstudents.ui.students.StudentScreen
import com.example.harrypotterstudents.ui.students.StudentsListScreen
import com.example.harrypotterstudents.ui.students.StudentsViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    studentsViewModel: StudentsViewModel
) {
    NavHost(navController, startDestination = BottomNavItems.Students.screen_route) {
        composable(BottomNavItems.Students.screen_route) {
            StudentsListScreen(navController, studentsViewModel)
        }
        composable(BottomNavItems.Actors.screen_route) {
//            Content(title = "Actors")
        }

        composable("${SHOW_DETAIL_SCREEN}/{student}",
            arguments = listOf(navArgument("student"){type= NavType.StringType})
        ) {
          StudentScreen(navController=navController,student = studentsViewModel.selectedStudent.value)  //studentsViewModel.selectedStudent.value?.let { it1 -> StudentScreen( student = it1) }
        }
    }
}
