package uz.gita.contactfirebase.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.gita.contactfirebase.db.UserEntity
import uz.gita.contactfirebase.model.User
import uz.gita.contactfirebase.repository.UserRepository

class HomeScreenViewModel : ViewModel() {

    private var _liveData = MutableLiveData<List<UserEntity>>()
    private val liveData:LiveData<List<UserEntity>> = _liveData
    fun getUserList(context: Context) :LiveData<List<UserEntity>> {
        val repository = UserRepository(context)
        val userList = repository.getAllNews()
        _liveData.postValue(userList)
        return liveData
    }




}