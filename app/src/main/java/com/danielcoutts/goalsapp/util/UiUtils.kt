package com.danielcoutts.goalsapp.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.bottomappbar.BottomAppBar

fun Toolbar.setNavigationIconWithTint(context: Context, @DrawableRes icon: Int, @ColorRes tint: Int) {
    navigationIcon = context.getTintedDrawable(icon, tint)
}

fun BottomAppBar.setNavigationIconWithTint(context: Context, @DrawableRes icon: Int, @ColorRes tint: Int) {
    navigationIcon = context.getTintedDrawable(icon, tint)
}

private fun Context.getTintedDrawable(@DrawableRes icon: Int, @ColorRes tint: Int): Drawable? {
    val baseDrawable = AppCompatResources.getDrawable(this, icon) ?: return null

    val tintColor = ContextCompat.getColor(this, tint)

    return DrawableCompat.wrap(baseDrawable).apply {
        setTint(tintColor)
    }
}

fun Resources.isNightTheme() = configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK