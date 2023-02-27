package com.example.harrypotterstudents.ui.students

import android.util.Log
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotterstudents.data.model.Student
import com.example.harrypotterstudents.util.NetworkResult

private const val TAG = "StudentsList"

@Composable
fun StudentScreen(
    studentsViewModel: StudentsViewModel,
   // selected: Student?,
) {
    val state = studentsViewModel.students.observeAsState().value
    val selected = studentsViewModel.selected.observeAsState().value
    when (state) {
        is NetworkResult.Loading -> {}
        is NetworkResult.Success -> {
            if (selected == null) {
                Students(state.response) {}
            } else {
                Student( selected)
            }
        }
        is NetworkResult.Error -> {}
        else -> {}
    }
}

@Composable
fun Students(
    students: ArrayList<Student>,
    onSelectionChange: ((Student) -> Unit)?
) {
    LazyColumn() {
        itemsIndexed(items = students) { index, student ->
            StudentItem(
                student,
                onSelectionChange
            )
        }
    }
}

@Composable
fun StudentItem(student: Student, onSelectionChange: ((Student) -> Unit)?) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                Log.d(TAG, "StudentItem: $student")
                onSelectionChange?.invoke(student)
            }) {
            AsyncImage(
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
                    .size(58.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = student.name ?: "Invalid name")
                Text(text = student.dateOfBirth.toString() ?: "Invalid date of birth")
                Text(text = if (student.alive == true) "Alive" else "Passed away")
            }
        }
    }
}
