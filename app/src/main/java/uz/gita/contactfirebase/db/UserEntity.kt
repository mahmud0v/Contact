package uz.gita.contactfirebase.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
data class UserEntity(
    @PrimaryKey
    var userName:String,
    var phone:String,
    var email:String
)