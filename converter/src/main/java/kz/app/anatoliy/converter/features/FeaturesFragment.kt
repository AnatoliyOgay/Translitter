package kz.app.anatoliy.converter.features

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_features.*
import kz.app.anatoliy.common.database.entities.Item
import kz.app.anatoliy.common.base.BaseFragment
import kz.app.anatoliy.converter.R
import kz.app.anatoliy.converter.VocabularyAdapter
import kz.app.anatoliy.converter.contract.VocabularyContract
import kz.app.anatoliy.converter.dialog.Dialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeaturesFragment(private val operation: () -> Unit) :
    BaseFragment<VocabularyContract.View, VocabularyContract.Presenter>(), VocabularyContract.View {

    companion object {
        fun create(operation: () -> Unit) = FeaturesFragment(operation)
    }

    private var adapterForFeatures: VocabularyAdapter? = null

    private val dialog = Dialog

    private val presenterImpl: FeaturesPresenter by viewModel()
    override val presenter: VocabularyContract.Presenter
        get() = presenterImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_features, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFeaturesList()
        setButtonClearAll()
        mainDisplayImageButton.setOnClickListener { operation() }
    }

    private fun setButtonClearAll(){
        button_clear_all_for_features.setOnClickListener {
            dialog.setDialog(context as Context) {
                presenter.clearAllFromTable()
            }
        }
    }

    private fun setFeaturesList(){
        adapterForFeatures =
            VocabularyAdapter(object : VocabularyAdapter.Callback {
                    override fun onClick(item: Item) {
                        presenter.removeFromTable(item)
                    }
                }
            )
        val manager = LinearLayoutManager(context)
        rv_features.apply {
            layoutManager = manager
            adapter = adapterForFeatures
        }
        presenter.getDataFromTable()
    }

    override fun add(itemList: List<Item>) { adapterForFeatures?.addList(itemList) }
    override fun remove(item: Item) { adapterForFeatures?.remove(item) }
    override fun clear() { adapterForFeatures?.clear() }
}