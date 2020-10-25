package com.xxx_market.tetris

import android.os.Bundle
import android.text.SpannableString
import androidx.databinding.DataBindingUtil
import com.xxx_market.tetris.databinding.ActivityMainBinding
import com.xxx_market.tetris.model.ModelScore
import com.xxx_market.tetris.presenter.MainPagePresenter
import com.xxx_market.tetris.view.MainPageView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class MainActivity : MvpAppCompatActivity(), MainPageView {

    private lateinit var modelScore: ModelScore
    private lateinit var binding: ActivityMainBinding
    private var score = 0

    @InjectPresenter
    lateinit var presenter: MainPagePresenter

    @ProvidePresenter
    fun provideMainPagePresenter(): MainPagePresenter{
        return  MainPagePresenter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        modelScore = ModelScore(score = "${resources.getString(R.string.bill)} $score")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.score = modelScore

        presenter.drawTitle(applicationContext)

    }

    override fun drawTitle(spans: SpannableString) {
        mainActivity_title_0.text = spans
    }

    override fun updateScore() {
    }
}
