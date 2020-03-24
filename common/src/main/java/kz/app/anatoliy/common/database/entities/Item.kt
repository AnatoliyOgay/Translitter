package kz.app.anatoliy.common.database.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int? = null,

    val entered: String,
    val translated: String
)