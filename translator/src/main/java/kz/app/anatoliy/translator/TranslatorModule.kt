package kz.app.anatoliy.translator

import kz.app.anatoliy.common.base.InjectionModule
import org.koin.dsl.module

object TranslatorModule : InjectionModule {

    override fun create() = module {
        single { TranslatorApi.create() }
        single { TranslatorPresenter(get())  }
    }
}