package com.example.harrypotterstudents.ui.students

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import com.example.harrypotterstudents.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.example.harrypotterstudents.data.model.Student
import com.example.harrypotterstudents.ui.navigation.SHOW_DETAIL_SCREEN
import com.example.harrypotterstudents.util.NetworkResult

private const val TAG = "StudentsList"

@Composable
fun StudentsListScreen(
    navController: NavController,
    studentsViewModel: StudentsViewModel,
    // selected: Student?,
) {
    val state = studentsViewModel.students.observeAsState().value

    when (state) {
        is NetworkResult.Loading -> {}
        is NetworkResult.Success -> {
            Students(navController, studentsViewModel, state.response) {}
        }
        is NetworkResult.Error -> {}
        else -> {}
    }
}

@Composable
fun Students(
    navController: NavController,
    studentsViewModel: StudentsViewModel,
    students: ArrayList<Student>,
    onSelectionChange: ((Student) -> Unit)?
) {
    LazyColumn() {
        itemsIndexed(items = students) { index, student ->
            StudentItem(
                navController,
                studentsViewModel,
                student
            )
        }
    }
}

@Composable
fun StudentItem(
    navController: NavController,
    studentsViewModel: StudentsViewModel,
    student: Student
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    Log.d(TAG, "StudentItem: $student")
                    studentsViewModel.setSelected(student)
//                   showMessage(LocalContext.current, "")
                    navController.navigate(route = "$SHOW_DETAIL_SCREEN/${student}")
                }) {

            AsyncImage(
                imageLoader = ImageLoader.Builder(LocalContext.current)
                    .components {
                        add(SvgDecoder.Factory())
                    }
                    .build(),
                model = student.image,
//               ImageRequest.Builder(LocalContext.current)
//                   .data(student.image)
//                   .crossfade(true)
//                   .build(),//
                contentDescription = student.wand?.core ?: "",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_loading),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .size(100.dp)

            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Actor : " + (student.name ?: "Invalid name"))
                Text(text = "DOB :" + (student.dateOfBirth ?: "Invalid DOB"))
                Text(text = if (student.alive == true) "Alive" else "Passed away")
            }
        }
    }
}

fun showMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}