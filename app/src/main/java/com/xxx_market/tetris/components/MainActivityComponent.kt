package com.xxx_market.tetris.components
import com.xxx_market.tetris.MainActivity
import com.xxx_market.tetris.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}