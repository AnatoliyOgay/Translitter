package kz.app.anatoliy.converter.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kz.app.anatoliy.common.database.entities.Item
import kz.app.anatoliy.common.base.BasePresenter
import kz.app.anatoliy.converter.api.VocabularyApi
import kz.app.anatoliy.converter.contract.VocabularyContract
import kz.app.anatoliy.converter.main.VocabularyFragment.Companion.cyrillicLets
import kz.app.anatoliy.converter.main.VocabularyFragment.Companion.latinicLets
import java.text.DecimalFormat
import java.util.*

class VocabularyPresenter(private val vocabularyApi: VocabularyApi) :
    BasePresenter<VocabularyContract.View>(), VocabularyContract.Presenter {

    override fun addToFeatures(itemList: List<Item>) =
        vocabularyApi.addToTable(itemList)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe()

    override fun getItemsWhichSplitted(item: Item): List<Item>{
        val cyrillWords = item.entered.split(" ")
        val latinWords = item.translated.split(" ")
        val items = mutableListOf<Item>()
        latinWords.forEachIndexed{i: Int, s: String ->
            items.add(
                Item(
                    entered = cyrillWords[i],
                    translated = s
                )
            )
        }
        return items
    }

    override fun getConvertedText(editableText: String): String{
        var newText = ""
        var founded: Boolean
        editableText.forEach {
            founded = false
            cyrillicLets.forEachIndexed { index: Int, s: String ->
                if (!founded) {
                    if (it.toString() == s) {
                        newText += latinicLets[index]
                        founded = true
                    } else if (it.isUpperCase()) {
                        newText += ifUpperCase( it.toString() )
                        founded = true
                    }
                }
            }
            if (!founded)
                newText += it
        }
        return newText
    }
    private fun ifUpperCase(let: String): String{
        var thisLet = let.toLowerCase(Locale.ROOT)
        var isOK = true
        cyrillicLets.forEachIndexed { i: Int, s: String ->
            if (isOK) {
                if (thisLet == s) {
                    thisLet = latinicLets[i].toUpperCase(Locale.ROOT)
                    isOK = false
                }
            }
        }
        return thisLet
    }

    private fun setFrequencyArray(
        text: String,
        lets: MutableList<String>,
        frequency: MutableList<Int>) {
        var counter: Int
        text.forEach {
            counter = 0
            lets.forEachIndexed { i: Int, s: String ->
                if (it.toString().toLowerCase(Locale.ROOT) == s) {
                    counter++
                    frequency[i]++
                }
            }
            if (counter == 0){
                lets.add(it.toString().toLowerCase(Locale.ROOT))
                frequency.add(1)
            }
        }
    }
    private fun percentOf(frequency: MutableList<Int>, i: Int): String{
        var sum = 0.0
        frequency.forEach {
            sum += it
        }
        return DecimalFormat("#0.0").format(frequency[i] / sum * 100)
    }
    override fun frequency(entered: String): String {
        val lets = mutableListOf<String>()
        val frequency = mutableListOf<Int>()
        setFrequencyArray(entered, lets, frequency)

        var temp: String
        var str = ""
        var freq: Int
        lets.forEachIndexed { i: Int, s: String ->
            freq = frequency[i]
            temp = "'$s' - $freq р., ${percentOf(frequency, i)}%\n"
            str += temp
        }
        return str
    }


    //...
    override fun getDataFromTable(): Disposable? {
        TODO("Not yet implemented")
    }
    override fun removeFromTable(item: Item): Disposable? {
        TODO("Not yet implemented")
    }
    override fun clearAllFromTable(): Disposable? {
        TODO("Not yet implemented")
    }
}
