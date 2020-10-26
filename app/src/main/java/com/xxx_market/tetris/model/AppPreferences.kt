package com.xxx_market.tetris.model

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(context: Context) {
    private val sharedPreferences: SharedPreferences
            by lazy {
                context.getSharedPreferences("${context.packageName} pref", Context.MODE_PRIVATE)
            }

    fun saveHighScore(highScore: Int) {
        sharedPreferences.edit().putInt("HIGH_SCORE", highScore).apply()
    }

    fun getHighScore(): Int {
        return sharedPreferences.getInt("HIGH_SCORE", 0)
    }

    fun clearHighScore() {
        sharedPreferences.edit().putInt("HIGH_SCORE", 0).apply()
    }
}