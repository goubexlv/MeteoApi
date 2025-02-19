package com.daccvo.di

import com.daccvo.repository.MeteoRepository
import com.daccvo.repository.MeteoRepositoryImpl

import org.koin.dsl.module

val KoinModule = module {
    single <MeteoRepository> {
      MeteoRepositoryImpl()
    }

}