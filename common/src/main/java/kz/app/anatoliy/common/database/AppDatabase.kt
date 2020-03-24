package kz.app.anatoliy.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.app.anatoliy.common.database.dao.ItemDao
import kz.app.anatoliy.common.database.entities.Item

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

}