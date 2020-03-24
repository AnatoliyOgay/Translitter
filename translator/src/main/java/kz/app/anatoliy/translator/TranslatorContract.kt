package kz.app.anatoliy.translator

import io.reactivex.disposables.Disposable
import kz.app.anatoliy.common.base.MvpPresenter
import kz.app.anatoliy.common.base.MvpView

interface TranslatorContract {

    interface View : MvpView

    interface Presenter : MvpPresenter<View> {
        fun getDataFromApi(): Disposable?
        fun getTranslatedText(words: String, i: Int): String
    }
}
