package kz.app.anatoliy.converter.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.*
import kz.app.anatoliy.common.database.entities.Item
import kz.app.anatoliy.common.base.BaseFragment
import kz.app.anatoliy.converter.R
import kz.app.anatoliy.converter.contract.VocabularyContract
import org.koin.androidx.viewmodel.ext.android.viewModel


class VocabularyFragment(
    private val openTranslator: () -> Unit,
    private val openFeatures: () -> Unit
) : BaseFragment<VocabularyContract.View, VocabularyContract.Presenter>(),
    VocabularyContract.View {

    companion object {
        fun create(openTranslator: () -> Unit, openFeatures: () -> Unit) =
            VocabularyFragment(openTranslator, openFeatures)

        val cyrillicLets: MutableList<String> = mutableListOf(
            "а","ә","б","в","г","ғ","д","е","ж","з","и","й","к","қ","л","м","н","ң","о","ө","п",
            "р","с","т","у","ұ","ү","ф","х","һ","ц","ч","ш","щ","ъ","ы","і","ь","э","ю","я"
        )
        val latinicLets: MutableList<String> = mutableListOf(
            "a","á","b","v","g","ǵ","d","e","j","z","i","i","k","q","l","m","n","ń","o","ó","p",
            "r","s","t","ý","u","ú","f","h","h","c","ch","sh","sh'","","y","i","'","e","yu","ya"
        )
    }

    private var item: Item? = null
    private var list = mutableListOf<Item>()

    private val presenterImpl: VocabularyPresenter by viewModel()
    override val presenter: VocabularyContract.Presenter
        get() = presenterImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtons()
    }

    private fun setButtons(){
        et_input_cyrillic.setOnClickListener {
            et_input_cyrillic.text?.clear()
            tv_output_translate.text = ""
            tv_output_frequency.text = ""
        }
        setButtonConvert()
        setButtonAddToF()

        button_open_translator.setOnClickListener{ openTranslator() }

        featuresDisplayImageButton.setOnClickListener{ openFeatures() }
    }
    private fun setButtonConvert(){
        button_convert.setOnClickListener {
            if (et_input_cyrillic.text.toString() !=""){
                tv_output_translate.text =
                    presenter.getConvertedText(et_input_cyrillic.text.toString())
                tv_output_frequency.text =
                    presenter.frequency(et_input_cyrillic.text.toString())
                item = Item(
                    entered = et_input_cyrillic.text.toString(),
                    translated = tv_output_translate.text.toString()
                )
                button_add_toF.visibility = View.VISIBLE
            }
        }
    }
    private fun setButtonAddToF(){
        button_add_toF.setOnClickListener {
            list.clear()
            list.addAll( presenter.getItemsWhichSplitted(item!!) )
            presenter.addToFeatures(list)
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
            button_add_toF.visibility = View.GONE
        }
    }

    //...
    override fun add(itemList: List<Item>) {
        TODO("Not yet implemented")
    }
    override fun remove(item: Item) {
        TODO("Not yet implemented")
    }
    override fun clear() {
        TODO("Not yet implemented")
    }

}