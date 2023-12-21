package com.redevrx.video_trimmer.event

import android.net.Uri
import androidx.media3.transformer.Composition
import androidx.media3.transformer.ExportResult

interface OnVideoEditedEvent {
    fun onVideoSaveResult(uri: Uri, composition: Composition, exportResult: ExportResult)
    fun onVideoSaveError(uri: Uri?, composition: Composition?, exportResult: ExportResult?, exception: Exception)
}