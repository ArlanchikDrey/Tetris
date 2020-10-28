package com.xxx_market.tetris.utils

fun array2dOfByte(sizeOuter: Int, sizeInner: Int): Array<ByteArray> =
    Array(sizeOuter) { ByteArray(sizeInner) }

enum class FieldConstants(val value: Int) { COLUMN_COUNT(10), ROW_COUNT(20); }