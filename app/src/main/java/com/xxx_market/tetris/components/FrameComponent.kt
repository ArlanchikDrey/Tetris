package com.xxx_market.tetris.components

import com.xxx_market.tetris.activities.GameActivity
import com.xxx_market.tetris.modules.FrameModule
import dagger.Component

@Component(modules = [FrameModule :: class])
interface FrameComponent {
    fun inject(gameActivity: GameActivity)
}