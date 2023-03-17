package dev.beefers.vendetta.manager.ui.viewmodel.main

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.beefers.vendetta.manager.domain.manager.DownloadManager
import dev.beefers.vendetta.manager.domain.repository.RestRepository
import dev.beefers.vendetta.manager.installer.util.installApks
import dev.beefers.vendetta.manager.network.dto.Release
import dev.beefers.vendetta.manager.network.utils.dataOrNull
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(
    private val repo: RestRepository,
    private val downloadManager: DownloadManager,
    private val context: Context
) : ScreenModel {
    private val downloadDir = context.externalCacheDir
    var release by mutableStateOf<Release?>(null)
        private set

    init {
        checkForUpdate()
    }

    private fun checkForUpdate() {
        coroutineScope.launch {
            release = repo.getLatestRelease().dataOrNull
        }
    }

    fun downloadAndInstallUpdate() {
        coroutineScope.launch {
            val update = File(downloadDir, "update.apk")
            downloadManager.downloadUpdate(update)
            context.installApks(false, update)
        }
    }

}