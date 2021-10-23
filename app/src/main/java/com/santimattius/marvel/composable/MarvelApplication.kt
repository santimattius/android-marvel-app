package com.santimattius.marvel.composable

import android.app.Application
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.santimattius.marvel.client.MarvelClientKoin
import com.santimattius.marvel.composable.di.*
import com.santimattius.marvel.composable.theme.MarvelComposableTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelApplication)
            modules(
                MarvelClientKoin.modules +
                        homeModules +
                        detailModules
            )
        }
    }
}

@Composable
fun MarvelApplication(content: @Composable () -> Unit) {
    MarvelComposableTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
