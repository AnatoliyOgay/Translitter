package kz.app.anatoliy.converter.api

import io.reactivex.Observable
import kz.app.anatoliy.common.database.entities.Item
import kz.app.anatoliy.common.database.dao.ItemDao

class VocabularyApi(private val dao: ItemDao?) {

    fun getFromTable() =
        dao?.get() as Observable<List<Item>>

    fun addToTable(itemList: List<Item>) =
        dao?.insert(itemList)

    fun removeFromTable(item: Item) =
        dao?.remove(item)

    fun clearAll() =
        dao?.clear()
}
