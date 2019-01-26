package com.zwb.statusbarutil

import android.annotation.TargetApi
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

object StatusBarUtil {

    @TargetApi(19)
    @JvmStatic
    fun transparencyBar(activity: Activity) {
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }


    @TargetApi(19)
    @JvmStatic
    fun setStatusBarsColor(activity: Activity,statusBarColor:Int,navigationBarColor:Int = Color.BLACK) {
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

            // View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 设置改属性之后，
            // 如果状态栏的颜色为透明，那么navigationBar会和底部重叠（异常）
            // 如果状态栏的颜色不是透明，那么navigationBar会把底部顶起（正常）

            // View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 设置状态栏为亮背景（表现的状态栏文字颜色为黑色）
            // View.SYSTEM_UI_FLAG_LAYOUT_STABLE 设置状态栏为暗背景（表现的状态栏文字颜色为白色）
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            window.statusBarColor = statusBarColor
            window.navigationBarColor = navigationBarColor
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}