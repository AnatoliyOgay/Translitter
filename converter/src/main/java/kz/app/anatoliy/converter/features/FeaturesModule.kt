package kz.app.anatoliy.converter.features

import kz.app.anatoliy.common.base.InjectionModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FeaturesModule : InjectionModule {

    override fun create() = module {
        viewModel { FeaturesPresenter( get() ) }
    }
}