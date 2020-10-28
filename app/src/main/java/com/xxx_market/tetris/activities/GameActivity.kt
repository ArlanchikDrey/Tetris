package com.xxx_market.tetris.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.xxx_market.tetris.R
import com.xxx_market.tetris.components.DaggerFrameComponent
import com.xxx_market.tetris.databinding.GameMainBinding
import com.xxx_market.tetris.model.Frame
import com.xxx_market.tetris.model.ModelGameScore
import com.xxx_market.tetris.modules.FrameModule
import com.xxx_market.tetris.presenter.GamePagePresenter
import com.xxx_market.tetris.view.GamePageView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class GameActivity : MvpAppCompatActivity(), GamePageView {

    private lateinit var modelScore: ModelGameScore
    private lateinit var binding: GameMainBinding

    @Inject
    lateinit var frame: Frame

    @InjectPresenter
    lateinit var presenter: GamePagePresenter

    @ProvidePresenter
    fun provideGamePagePresenter(): GamePagePresenter {
        return GamePagePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.game_main
        )
        DaggerFrameComponent.builder().frameModule(FrameModule(10)).build().inject(this)
    }

}
