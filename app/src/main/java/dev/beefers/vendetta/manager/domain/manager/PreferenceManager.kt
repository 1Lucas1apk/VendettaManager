package dev.beefers.vendetta.manager.domain.manager

import android.content.Context
import android.os.Build
import androidx.annotation.StringRes
import dev.beefers.vendetta.manager.R
import dev.beefers.vendetta.manager.domain.manager.base.BasePreferenceManager
import dev.beefers.vendetta.manager.utils.DiscordVersion
import java.io.File

class PreferenceManager(private val context: Context) :
    BasePreferenceManager(context.getSharedPreferences("prefs", Context.MODE_PRIVATE)) {

    val DEFAULT_MODULE_LOCATION = (context.externalCacheDir ?: context.cacheDir).resolve("vendetta.apk")

    var packageName by stringPreference("package_name", "dev.beefers.vendetta")

    var appName by stringPreference("app_name", "Vendetta")

    var discordVersion by stringPreference("discord_version", "")

    var moduleVersion by stringPreference("module_version", "")

    var patchIcon by booleanPreference("patch_icon", true)

    var debuggable by booleanPreference("debuggable", false)

    var monet by booleanPreference("monet", Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)

    var isDeveloper by booleanPreference("is_developer", false)

    var autoClearCache by booleanPreference("auto_clear_cache", true)

    var theme by enumPreference("theme", Theme.SYSTEM)

    var channel by enumPreference("channel", DiscordVersion.Type.STABLE)

    var moduleLocation by filePreference("module_location", DEFAULT_MODULE_LOCATION)

}

enum class Theme(@StringRes val labelRes: Int) {
    SYSTEM(R.string.theme_system),
    LIGHT(R.string.theme_light),
    DARK(R.string.theme_dark)
}