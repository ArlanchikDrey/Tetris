package com.xxx_market.tetris.modules

import com.xxx_market.tetris.model.Frame
import dagger.Module
import dagger.Provides

@Module
class FrameModule(private val width: Int) {
    @Provides
    fun providesFrame(): Frame {
        return Frame(width)
    }
}