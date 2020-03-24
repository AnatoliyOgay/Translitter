package kz.app.anatoliy.translator

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kz.app.anatoliy.common.base.BasePresenter

class TranslatorPresenter(private val translatorApi: TranslatorApi) :
    BasePresenter<TranslatorContract.View>(), TranslatorContract.Presenter {

    private val dataList = mutableListOf<DataElement>()

    private val tempStrList = mutableListOf<String>()
    private val foundedDataElements = mutableListOf<DataElement>()

    private var tempStr = ""

    override fun getDataFromApi(): Disposable? = translatorApi.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe{
            dataList.addAll(it)
        }

    override fun getTranslatedText(words: String, i: Int): String{
        tempStrList.clear()
        foundedDataElements.clear()
        tempStrList.addAll( words.split(" ") )
        if (i == 2131230938)
            getElementsIfEN(tempStrList, foundedDataElements)
        if (i == 2131230939 || i == 1)
            getElementsIfRU(tempStrList, foundedDataElements)
        return getStrFromDE(foundedDataElements)
    }

    private fun getElementsIfRU(tempStrList: List<String>, elements: MutableList<DataElement>){
        tempStrList.forEach { str: String ->
            dataList.forEach { elem: DataElement ->
                if (elem.ru == str)
                    elements.add(elem)
            }
        }
    }
    private fun getElementsIfEN(tempStrList: List<String>, elements: MutableList<DataElement>){
        tempStrList.forEach { str: String ->
            dataList.forEach { elem: DataElement ->
                if (elem.en == str)
                    elements.add(elem)
            }
        }
    }

    private fun getStrFromDE(dataList: List<DataElement>): String{
        tempStr = ""
        dataList.forEach {
            tempStr += "${it.kz} "
        }
        return tempStr
    }
}
