package com.example.mycomposeapplication.data

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mycomposeapplication.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserViewModel  constructor(val userRepository: UserRepository) : ViewModel() {

    companion object {

        fun provideViewModelFactory() : ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    val application = checkNotNull(
                        extras[APPLICATION_KEY]
                    )
                    return UserViewModel(
                        (application as MyApplication).repository
                    ) as T
                }
            }
    }


     val _users = MutableLiveData<Result<List<User>>>()
//    private val _users = MutableLiveData<List<User>>()
    val users: MutableLiveData<Result<List<User>>>
        get() = _users


    fun getUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
            val response = userRepository.getUsers()
            _users.postValue(response)
        } catch (_: Exception){
        }
        }
    }

    suspend fun getUserById(userId: Int): User{
        return userRepository.getUserById(userId)
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
