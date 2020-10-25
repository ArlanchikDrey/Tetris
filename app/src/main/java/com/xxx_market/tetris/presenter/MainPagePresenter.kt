package com.xxx_market.tetris.presenter

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.xxx_market.tetris.R
import com.xxx_market.tetris.view.MainPageView
import moxy.MvpPresenter
import java.lang.Exception

class MainPagePresenter: MvpPresenter<MainPageView>() {
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