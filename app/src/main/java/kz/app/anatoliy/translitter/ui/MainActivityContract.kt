package kz.app.anatoliy.translitter.ui

import kz.app.anatoliy.common.base.MvpView

interface MainActivityContract {

    interface View : MvpView {
        fun showSomeView()
    }
}