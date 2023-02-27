package com.example.harrypotterstudents.ui.students

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterstudents.data.model.Student
import com.example.harrypotterstudents.data.model.Students
import com.example.harrypotterstudents.data.repository.StudentsRepository
import com.example.harrypotterstudents.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StudentsViewModel(
    private val studentsRepository: StudentsRepository
) : ViewModel() {
    init {
        getStudents()
    }

    private var _students: MutableLiveData<NetworkResult<Students>> =
        MutableLiveData(NetworkResult.Loading)
    val students: LiveData<NetworkResult<Students>> get() = _students

    private var _selected = MutableLiveData<Student>()
    val selected :LiveData<Student> get() = _selected

    private fun getStudents() {
        viewModelScope.launch {
            studentsRepository.getStudents().collect() {
                _students.postValue(it)
            }
        }
    }

    fun setSelected(student: Student){
        _selected.postValue(student)
    }

}