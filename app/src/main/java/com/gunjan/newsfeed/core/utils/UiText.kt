package com.gunjan.newsfeed.core.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()

    fun asString(context: Context): String = when (this) {
        is DynamicString -> value
        is StringResource -> context.getString(resId, *args)
    }
}
