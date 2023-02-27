package com.example.harrypotterstudents.ui.students

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotterstudents.R
import com.example.harrypotterstudents.data.model.Student

@Composable
fun Student(student: Student) {
    Column() {
        AsyncImage(
            model = student.image,
//            ImageRequest.Builder(LocalContext.current)
//                .data(student.image)
//                .crossfade(true)
//                .build(),//
            contentDescription = student.wand?.core ?: "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_loading),
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .weight(0.6f)
                .fillMaxWidth()
        )
        Row() {
            Text(text = "Actor Name : ")
            Text(text = student.actor.toString())
        }
        Row() {
            Text(text = "Name : ")
            Text(text = student.name.toString())
        }
        Row() {
            Text(text = "DOB : ")
            Text(text = student.dateOfBirth.toString())
        }
        Row() {
            Text(text = "Eye Color : ")
            Text(text = student.eyeColour.toString())
        }
        Row() {
            Text(text = "Hair Color : ")
            Text(text = student.hairColour.toString() ?: "unknown")
        }
    }
}

@Preview
@Composable
fun StudentPreview() {
    val student = Student(
        name = "Harry Potter",
        actor = "Daniel Radcliffe",
        dateOfBirth = "31-07-1980",
        eyeColour = "green",
        hairColour = "black",
        image = "https://ik.imagekit.io/hpapi/harry.jpg"
    )
    Student(student = student)
}