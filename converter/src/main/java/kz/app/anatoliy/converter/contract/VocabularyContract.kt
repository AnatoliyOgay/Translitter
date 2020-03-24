package kz.app.anatoliy.converter.contract

import io.reactivex.disposables.Disposable
import kz.app.anatoliy.common.database.entities.Item
import kz.app.anatoliy.common.base.MvpPresenter
import kz.app.anatoliy.common.base.MvpView

interface VocabularyContract {

    interface View : MvpView{
        fun add(itemList: List<Item>)
        fun remove(item: Item)
        fun clear()
    }

    interface Presenter : MvpPresenter<View> {

        fun getItemsWhichSplitted(item: Item): List<Item>
        fun getConvertedText(editableText: String): String
        fun frequency(entered: String): String

        fun getDataFromTable(): Disposable?
        fun addToFeatures(itemList: List<Item>): Disposable?
        fun removeFromTable(item: Item): Disposable?
        fun clearAllFromTable(): Disposable?
    }
}
