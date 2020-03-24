package kz.app.anatoliy.converter.features

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kz.app.anatoliy.common.database.entities.Item
import kz.app.anatoliy.common.base.BasePresenter
import kz.app.anatoliy.converter.api.VocabularyApi
import kz.app.anatoliy.converter.contract.VocabularyContract

class FeaturesPresenter(private val vocabularyApi: VocabularyApi) :
    BasePresenter<VocabularyContract.View>(), VocabularyContract.Presenter {

    override fun getDataFromTable(): Disposable? =
        vocabularyApi.getFromTable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ view?.add(it) }

    override fun addToFeatures(itemList: List<Item>): Disposable? =
        vocabularyApi.addToTable(itemList)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe{ view?.add(itemList) }

    override fun removeFromTable(item: Item) =
        vocabularyApi.removeFromTable(item)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe{ view?.remove(item) }

    override fun clearAllFromTable(): Disposable? =
        vocabularyApi.clearAll()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe{ view?.clear() }

    //...
    override fun getItemsWhichSplitted(item: Item): List<Item> {
        TODO("Not yet implemented")
    }
    override fun getConvertedText(editableText: String): String {
        TODO("Not yet implemented")
    }
    override fun frequency(entered: String): String {
        TODO("Not yet implemented")
    }
}
