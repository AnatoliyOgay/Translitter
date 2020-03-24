package kz.app.anatoliy.converter.main

import androidx.room.Room
import kz.app.anatoliy.common.base.InjectionModule
import kz.app.anatoliy.common.database.AppDatabase
import kz.app.anatoliy.converter.api.VocabularyApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object VocabularyModule : InjectionModule {

    override fun create() = module {
        single { Room.databaseBuilder(get(), AppDatabase::class.java, "myDataBase").build() }
        single { get<AppDatabase>().itemDao() }
        single { VocabularyApi( get() ) }
        viewModel { VocabularyPresenter( get() ) }
    }
}