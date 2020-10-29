package com.xxx_market.tetris.components

import com.xxx_market.tetris.activities.GameActivity
import com.xxx_market.tetris.modules.AppModule
import com.xxx_market.tetris.modules.FrameModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FrameModule::class,
        AppModule::class
    ]
)
interface GameActivityComponent {
    fun inject(gameActivity: GameActivity)
}