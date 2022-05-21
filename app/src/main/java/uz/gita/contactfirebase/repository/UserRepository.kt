package uz.gita.contactfirebase.repository

import android.content.Context
import uz.gita.contactfirebase.db.UserDatabase
import uz.gita.contactfirebase.db.UserEntity
import uz.gita.contactfirebase.model.User

class UserRepository(context: Context){

    private val database= UserDatabase.initDB(context)
    private val userDao = UserDatabase.instance!!.getUserDao()

    fun getAllNews() = userDao.getAllUser()

    fun insert(user:UserEntity) = userDao.insertUser(user)

}