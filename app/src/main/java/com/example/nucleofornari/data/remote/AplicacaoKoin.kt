package com.example.nucleofornari.data.remote

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AplicacaoKoin : Application() {
    override fun onCreate() {
        super.onCreate()
        // Iniciar o Koin
        startKoin {
            androidLogger()
            androidContext(this@AplicacaoKoin)
            // Módulos de injeção de dependência
            modules(moduloGeral)
        }
    }
}