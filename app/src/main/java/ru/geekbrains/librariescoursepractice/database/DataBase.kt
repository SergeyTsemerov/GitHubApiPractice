package ru.geekbrains.librariescoursepractice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.RuntimeException

@Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class
    ],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: DataBase? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, DataBase::class.java, DB_NAME).build()
            }
        }
    }
}