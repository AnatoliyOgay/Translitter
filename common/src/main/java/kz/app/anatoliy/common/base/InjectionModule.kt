package kz.app.anatoliy.common.base

import org.koin.core.module.Module

interface InjectionModule {
    fun create(): Module
}