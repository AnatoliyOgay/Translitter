package kz.app.anatoliy.translitter

import kz.app.anatoliy.converter.features.FeaturesModule
import kz.app.anatoliy.converter.main.VocabularyModule
import kz.app.anatoliy.translator.TranslatorModule
import org.koin.core.module.Module

object KoinModules {
    val modules: List<Module> =
        listOf(
            VocabularyModule.create(),
            TranslatorModule.create(),
            FeaturesModule.create()
        )
}