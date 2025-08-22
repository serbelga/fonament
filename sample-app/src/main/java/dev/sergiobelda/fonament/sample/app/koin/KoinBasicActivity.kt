package dev.sergiobelda.fonament.sample.app.koin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.sergiobelda.fonament.di.koin.fonamentViewModel
import dev.sergiobelda.fonament.di.koin.koinFonamentViewModel
import dev.sergiobelda.fonament.sample.app.basic.BasicScreen
import dev.sergiobelda.fonament.sample.app.basic.BasicViewModel
import dev.sergiobelda.fonament.sample.app.basic.BasicViewModelV2
import org.koin.compose.KoinApplication
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

class KoinBasicActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinApplication(
                application = {
                    modules(
                        module {
                            fonamentViewModel { BasicViewModel() }
                            fonamentViewModel(qualifier("V2")) { BasicViewModelV2() }
                        },
                    )
                },
            ) {
                BasicScreen.Content(
                    viewModel = koinFonamentViewModel(),
                    // viewModel = koinFonamentViewModel(named("V2")),
                )
            }
        }
    }
}
