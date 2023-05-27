package com.example.mycomposeapplication.data

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mycomposeapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel  @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
    ) : ViewModel() {

/*     val m_users = MutableLiveData<Result<List<UserEntity>>>()
//    private val _users = MutableLiveData<List<User>>()
    val users: MutableLiveData<Result<List<UserEntity>>>
        get() = m_users*/
    val users = userRepositoryImpl.getUsers()
    .stateIn(
        viewModelScope ,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 5000
        ) ,
        initialValue = Resource.Loading(emptyList<UserDto>())
    )


   /* fun getUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
            val response = userRepositoryImpl.getUsers()
            m_users.postValue(response)
        } catch (_: Exception){
        }
        }
    }*/

    suspend fun getUserById(userId: Int): UserEntity{
        return userRepositoryImpl.getUserById(userId)
    }
}



//fun getUsers() {
//    viewModelScope.launch(Dispatchers.IO) {
//        val users = userRepository.getUsers()
//        userLiveData.postValue(users)
//    }
//}






//private val _users = MutableLiveData<List<User>>()
//val users: LiveData<List<User>> = _users
//
//init {
//    viewModelScope.launch(Dispatchers.IO) {
//        val userList = userRepository.getUsers()
//        emit(userList)
//    }
//}
