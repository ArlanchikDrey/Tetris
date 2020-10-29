package com.xxx_market.tetris.activities

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.databinding.DataBindingUtil
import com.xxx_market.tetris.R
import com.xxx_market.tetris.components.DaggerGameActivityComponent
import com.xxx_market.tetris.customViews.TetrisView
import com.xxx_market.tetris.databinding.GameMainBinding
import com.xxx_market.tetris.model.AppPreferences
import com.xxx_market.tetris.model.Frame
import com.xxx_market.tetris.model.ModelGameScore
import com.xxx_market.tetris.modules.AppModule
import com.xxx_market.tetris.modules.FrameModule
import com.xxx_market.tetris.presenter.GamePagePresenter
import com.xxx_market.tetris.view.GamePageView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class GameActivity : MvpAppCompatActivity(), GamePageView {

    private lateinit var modelScore: ModelGameScore
    private lateinit var tetrisView: TetrisView
    private lateinit var binding: GameMainBinding

    @Inject
    lateinit var appPreferences: AppPreferences

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
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.game_main
        )
        DaggerGameActivityComponent.builder().appModule(AppModule(application))
            .frameModule(FrameModule(10)).build().inject(this)

        presenter.setPreferences(appPreferences)
        presenter.initMusic(applicationContext)
        tetrisView = binding.viewTetris
        tetrisView.setActivity(this)
        tetrisView.setModel(presenter)
        tetrisView.setOnTouchListener(this::onTetrisViewTouch)

    }

    private fun moveTetromino(motion: GamePagePresenter.Motions) {
        if (presenter.isGameActive()) {
            tetrisView.setGameCommand(motion)
        }
    }

    private fun resolveTouchDirection(view: View, event: MotionEvent): Int {
        val x = event.x / view.width
        val y = event.y / view.height
        val direction: Int
        direction = if (y > x) {
            if (x > 1 - y) 2 else 0
        } else {
            if (x > 1 - y) 3 else 1
        }
        return direction
    }

    private fun onTetrisViewTouch(view: View, event: MotionEvent): Boolean {
        if (presenter.isGameOver() || presenter.isGameAwaitingStart()) {
            presenter.startGame()
            tetrisView.setGameCommandWithDelay(GamePagePresenter.Motions.DOWN)
        } else if (presenter.isGameActive()) {
            when (resolveTouchDirection(view, event)) {
                0 -> moveTetromino(GamePagePresenter.Motions.LEFT)
                1 -> moveTetromino(GamePagePresenter.Motions.ROTATE)
                2 -> moveTetromino(GamePagePresenter.Motions.DOWN)
                3 -> moveTetromino(GamePagePresenter.Motions.RIGHT)
            }
        }
        return true
    }

}
