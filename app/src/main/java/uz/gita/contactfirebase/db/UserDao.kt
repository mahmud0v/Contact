package uz.gita.contactfirebase.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.contactfirebase.model.User

@Dao
interface UserDao {

    @Query("select * from Contact")
    fun getAllUser() : List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

}