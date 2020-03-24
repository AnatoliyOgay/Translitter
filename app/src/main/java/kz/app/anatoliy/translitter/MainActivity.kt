package kz.app.anatoliy.translitter

import android.os.Bundle
import android.os.Handler
import com.facebook.stetho.Stetho
import kz.app.anatoliy.common.base.BaseActivity
import kz.app.anatoliy.common.contract.AppRouterContract
import kz.app.anatoliy.converter.features.FeaturesFragment
import kz.app.anatoliy.converter.main.VocabularyFragment
import kz.app.anatoliy.translator.TranslatorFragment
import kz.app.anatoliy.translitter.ui.MainActivityContract

class MainActivity : BaseActivity(), MainActivityContract.View, AppRouterContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
        showSplash()
        Handler().postDelayed(
            {
                showTranslitter()
            },
            4000 // value in milliseconds
        )
    }

    override fun showTranslitter() {
        replaceFragment( VocabularyFragment.create(
            { showTranslator() },
            { showFeatures() }
        ))
    }

    override fun showTranslator() {
        replaceFragment( TranslatorFragment.create() )
    }

    override fun showFeatures() {
        replaceFragment( FeaturesFragment.create { showTranslitter() } )
    }

    override fun showSomeView() {}

    private fun showSplash(){
        replaceFragment(SplashFragment.create())
    }
}
