package com.santimattius.marvel.composable

import android.app.Application
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.santimattius.marvel.client.MarvelClientKoin
import com.santimattius.marvel.composable.di.appModules
import com.santimattius.marvel.composable.di.domainModules
import com.santimattius.marvel.composable.di.infrastructureModules
import com.santimattius.marvel.composable.theme.MarvelComposableTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelApplication)
            modules(
                listOf(
                    MarvelClientKoin.modules,
                    appModules,
                    domainModules,
                    infrastructureModules
                )
            )
        }
    }
}

@Composable
fun MarvelApplication(content: @Composable () -> Unit) {
    MarvelComposableTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
