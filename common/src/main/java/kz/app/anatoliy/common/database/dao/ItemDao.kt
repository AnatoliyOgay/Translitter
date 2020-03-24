package kz.app.anatoliy.common.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import kz.app.anatoliy.common.database.entities.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<Item>): Completable

    @Delete
    fun remove(item: Item): Completable

    @Query("DELETE FROM Item")
    fun clear(): Completable

    @Query("SELECT * FROM Item")
    fun get(): Observable<List<Item>>
}