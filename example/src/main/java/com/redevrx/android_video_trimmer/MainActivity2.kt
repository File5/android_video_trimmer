package com.redevrx.android_video_trimmer

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.transformer.ExportResult
import androidx.media3.transformer.Composition
import com.redevrx.android_video_trimmer.databinding.ActivityMain2Binding
import com.redevrx.video_trimmer.event.OnVideoEditedEvent


class MainActivity2 : AppCompatActivity(), OnVideoEditedEvent {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        val uri = intent.extras?.get("image") as Uri?
        val path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        binding.videoTrimmer.apply {
            setOnTrimVideoListener(this@MainActivity2)
            setVideoURI(uri!!)
            setResultFileName("video_test.mp4")
            setDestinationPath(path.absolutePath)
            setVideoInformationVisibility(true)
            setMaxDuration(30)
            setMinDuration(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main2_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_export -> {
            Toast.makeText(this, "Video export started", Toast.LENGTH_SHORT).show()
            binding.videoTrimmer.saveVideo()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onVideoSaveResult(
        uri: Uri,
        composition: Composition,
        exportResult: ExportResult
    ) {
        println("Save video success")
        println(uri.path)
        Toast.makeText(this, "Video export finished", Toast.LENGTH_SHORT).show()
    }

    override fun onVideoSaveError(
        uri: Uri?,
        composition: Composition?,
        exportResult: ExportResult?,
        exception: Exception
    ) {
        println("Save video error :${exception.localizedMessage ?: exception.message ?: exception.toString()}")
        Toast.makeText(this, "Video export finished (error occurred)", Toast.LENGTH_SHORT).show()
    }

}