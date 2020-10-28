package com.xxx_market.tetris.activities

import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.xxx_market.tetris.R
import com.xxx_market.tetris.components.DaggerFrameComponent
import com.xxx_market.tetris.components.DaggerMainActivityComponent
import com.xxx_market.tetris.databinding.ActivityMainBinding
import com.xxx_market.tetris.model.AppPreferences
import com.xxx_market.tetris.model.Frame
import com.xxx_market.tetris.model.ModelScore
import com.xxx_market.tetris.modules.AppModule
import com.xxx_market.tetris.presenter.MainPagePresenter
import com.xxx_market.tetris.view.MainPageView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainPageView {

    private lateinit var modelScore: ModelScore
    private lateinit var binding: ActivityMainBinding
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
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        DaggerMainActivityComponent.builder().appModule(AppModule(application)).build().inject(this)
        presenter.drawTitle(applicationContext)
        presenter.initMusic(applicationContext)
        presenter.getScore(appPreferences)
    }

    override fun onPause() {
        super.onPause()
        presenter.pauseMusic()
    }

    override fun onStop() {
        super.onStop()
        presenter.stopMusic()
    }

    override fun drawTitle(spans: SpannableString) {
        mainActivity_title_0.text = spans
    }

    override fun getScore(score: Int) {
        modelScore = ModelScore(score = "${resources.getString(R.string.bill)} $score")
        binding.score = modelScore
    }

}
