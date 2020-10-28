package com.xxx_market.tetris

import com.xxx_market.tetris.components.DaggerFrameComponent
import com.xxx_market.tetris.model.AppPreferences
import com.xxx_market.tetris.model.Frame
import com.xxx_market.tetris.presenter.MainPagePresenter
import org.junit.Test
import org.junit.Assert.*
import java.lang.Exception
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testFrame() {
        val frame = Frame(4)
        val byte: Byte = 1
        assertEquals(byte, frame.addRow("1111").data[0][0])
    }

    @Test
    fun getColor_isCorrect() {
        val color = getColors(100)
        assertEquals(null, color)
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
