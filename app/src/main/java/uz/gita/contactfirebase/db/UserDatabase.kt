package uz.gita.contactfirebase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1
)

abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        var instance: UserDatabase? = null

        fun initDB(context: Context) : UserDatabase {
            instance = Room.databaseBuilder(context, UserDatabase::class.java, "Contact.db")
                .allowMainThreadQueries()
                .build()

            return instance!!
        }

    }


}