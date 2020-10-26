package com.xxx_market.tetris.presenter

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.xxx_market.tetris.R
import com.xxx_market.tetris.model.AppPreferences
import com.xxx_market.tetris.view.MainPageView
import moxy.MvpPresenter
import java.lang.Exception

class MainPagePresenter: MvpPresenter<MainPageView>() {
    private var mediaPlayer: MediaPlayer? = null

    fun initMusic(context: Context){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(context, R.raw.galaxy)
        }
        mediaPlayer?.start()
        mediaPlayer?.isLooping = true
    }

    fun getScore(appPreferences: AppPreferences){
        viewState.getScore(appPreferences.getHighScore())
    }

    fun pauseMusic(){
        mediaPlayer?.pause()
    }

    fun stopMusic(){
        mediaPlayer?.stop()
    }

    fun resetMusic(){
        mediaPlayer?.reset()
    }

    fun drawTitle(context: Context){
        viewState.drawTitle(setTitleColor(context))
    }

    private fun setTitleColor(context: Context) : SpannableString {
        val text = context.resources.getString(R.string.app_name)
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