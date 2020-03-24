package kz.app.anatoliy.translator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_translator.*
import kz.app.anatoliy.common.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslatorFragment : BaseFragment<TranslatorContract.View, TranslatorContract.Presenter>(),
TranslatorContract.View {

    companion object {
        fun create() = TranslatorFragment()
    }

    private var selectedLanguage = 1

    private var entered = ""

    private val presenterImpl: TranslatorPresenter by viewModel()
    override val presenter: TranslatorContract.Presenter
        get() = presenterImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_translator, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getDataFromApi()
        setButtonTranslate()
        setEnteredLanguage()
        et_entered.setOnClickListener {
            et_entered.text?.clear()
            tv_translated.text = ""
        }
    }

    private fun setButtonTranslate(){
        button_translate.setOnClickListener {
            entered = et_entered.text.toString()
            tv_translated.text = presenter.getTranslatedText(entered, selectedLanguage)
        }
    }

    private fun setEnteredLanguage(){
        rg_entered.setOnCheckedChangeListener { _, checkedId ->
            selectedLanguage = checkedId
        }
    }
}
