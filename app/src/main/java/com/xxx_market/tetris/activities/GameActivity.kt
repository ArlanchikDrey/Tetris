package com.xxx_market.tetris.activities

import android.os.Bundle
import android.text.SpannableString
import androidx.databinding.DataBindingUtil
import com.xxx_market.tetris.R
import com.xxx_market.tetris.components.DaggerMainActivityComponent
import com.xxx_market.tetris.databinding.ActivityMainBinding
import com.xxx_market.tetris.databinding.GameMainBinding
import com.xxx_market.tetris.model.AppPreferences
import com.xxx_market.tetris.model.ModelGameScore
import com.xxx_market.tetris.model.ModelScore
import com.xxx_market.tetris.modules.AppModule
import com.xxx_market.tetris.presenter.GamePagePresenter
import com.xxx_market.tetris.presenter.MainPagePresenter
import com.xxx_market.tetris.view.GamePageView
import com.xxx_market.tetris.view.MainPageView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class GameActivity : MvpAppCompatActivity(), GamePageView {

    private lateinit var modelScore: ModelGameScore
    private lateinit var binding: GameMainBinding

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
    }

}
