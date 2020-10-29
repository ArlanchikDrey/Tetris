package com.xxx_market.tetris.activities

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.SpannableString
import androidx.databinding.DataBindingUtil
import com.xxx_market.tetris.R
import com.xxx_market.tetris.components.DaggerMainActivityComponent
import com.xxx_market.tetris.databinding.ActivityMainBinding
import com.xxx_market.tetris.model.AppPreferences
import com.xxx_market.tetris.model.ModelScore
import com.xxx_market.tetris.modules.AppModule
import com.xxx_market.tetris.presenter.MainPagePresenter
import com.xxx_market.tetris.view.MainPageView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainPageView {

    private lateinit var modelScore: ModelScore
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    @Inject
    lateinit var appPreferences: AppPreferences

    @InjectPresenter
    lateinit var presenter: MainPagePresenter

    @ProvidePresenter
    fun provideMainPagePresenter(): MainPagePresenter {
        return MainPagePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        DaggerMainActivityComponent.builder().appModule(AppModule(application)).build().inject(this)
        presenter.drawTitle(resources.getString(R.string.app_name))
        presenter.initMusic()
        presenter.getScore(appPreferences)

        binding.btnNewGame.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.initMusic()
    }

    override fun onPause() {
        super.onPause()
        presenter.pauseMusic()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
    }

    override fun drawTitle(spans: SpannableString) {
        binding.mainActivityTitle0.text = spans
    }

    override fun getScore(score: Int) {
        modelScore = ModelScore(score = "${resources.getString(R.string.bill)} $score")
        binding.score = modelScore
    }

    override fun startMusic() {
        if (presenter.getPosition() != null) {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.galaxy)
                mediaPlayer?.seekTo(presenter.getPosition()!!)
                mediaPlayer?.start()
                mediaPlayer?.isLooping = true
            }
        } else {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.galaxy)
                mediaPlayer?.start()
                mediaPlayer?.isLooping = true
            }
        }
    }

    override fun pauseMusic() {
        if (mediaPlayer != null) {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer?.stop()
                presenter.setPosition(position = mediaPlayer?.currentPosition)
                mediaPlayer = null
            }
        }
    }

    override fun resetMusic() {
        mediaPlayer?.reset()
    }

}
