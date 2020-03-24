package kz.app.anatoliy.common.base

interface MvpPresenter<V : MvpView> {

    fun attach(view: V)

    fun detach()
}