package rc.loveq.newmusicplayer.utils

import android.content.Context
import java.util.jar.Attributes

object ResourceHelper {

    fun getThemeColor(context: Context, attributes: Int, defaultColor: Int): Int {
        val themeColor: Int
        val packageName = context.packageName
        val packageContext = context.createPackageContext(packageName, 0)
        val applicationInfo = context.packageManager.getApplicationInfo(packageName, 0)
        packageContext.setTheme(applicationInfo.theme)
        val theme = packageContext.theme
        val typedArray = theme.obtainStyledAttributes(intArrayOf(attributes))
        themeColor = typedArray.getColor(0, defaultColor)
        typedArray.recycle()
        return themeColor

    }
}