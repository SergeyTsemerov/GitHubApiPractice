package ru.geekbrains.librariescoursepractice.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RoomGithubUser")
data class RoomGithubUser(
    @PrimaryKey var id: String,
    var login: String,
    var avatarUrl: String,
    var reposUrl: String
)

