package com.xxx_market.tetris.presenter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.xxx_market.tetris.model.AppPreferences
import com.xxx_market.tetris.view.MainPageView
import moxy.MvpPresenter
import java.lang.Exception

class MainPagePresenter: MvpPresenter<MainPageView>() {

    private var position:Int? = 0

    fun initMusic(){
       viewState.startMusic()
    }

    fun getScore(appPreferences: AppPreferences){
        viewState.getScore(appPreferences.getHighScore())
    }

    fun pauseMusic(){
        viewState.pauseMusic()
    }

    fun resetMusic(){
        viewState.resetMusic()
    }

    fun drawTitle(text: String){
        viewState.drawTitle(setTitleColor(text))
    }

    fun setPosition(position: Int?){
        this.position = position
    }

    fun getPosition():Int? = position

    private fun setTitleColor(text: String) : SpannableString {
        val spans = SpannableString(text)

        for (i in text.indices) {
            spans.setSpan(
                ForegroundColorSpan(Color.parseColor(getColors(i) ?: "#FFFFFF")),
                i,
                i + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return spans
    }

    private fun getColors(i: Int): String? {
        return try {
            val colors = arrayListOf("#9347FF", "#6FF607", "#F6F607", "#076EFF", "#F82A31", "#FA881E")

            colors[i]
        } catch (e: Exception) {
            null
        }
    }
}