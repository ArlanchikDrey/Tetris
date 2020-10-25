package com.xxx_market.tetris.view

import android.text.SpannableString
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainPageView: MvpView {
    fun drawTitle(spans: SpannableString)

    fun updateScore()
}