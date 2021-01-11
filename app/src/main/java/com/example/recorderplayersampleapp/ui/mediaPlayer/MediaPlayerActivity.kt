package com.example.recorderplayersampleapp.ui.mediaPlayer

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.Toast
import com.example.recorderplayersampleapp.R
import com.example.recorderplayersampleapp.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_media_player.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class MediaPlayerActivity : BaseActivity(), SeekBar.OnSeekBarChangeListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private val mediaPlayer = MediaPlayer()
    private var runnable: Runnable = Runnable { }
    private var handler = Handler(Looper.getMainLooper())
    private var audioPath: String = ""
    private val seekForwardTime = 5000 // 5000 milliseconds
    private val seekBackwardTime = 5000 // 5000 milliseconds

    private var currentAudioIndex = 0
    private var isShuffle = false
    private var isRepeat = false
    private var audiosList: ArrayList<String> = ArrayList()

    companion object {
        const val EXTRA_PATH = "EXTRA_PATH"
        fun getIntent(mContext: Context?, audioPath: String): Intent {
            val intent = Intent(mContext, MediaPlayerActivity::class.java)
            intent.putExtra(EXTRA_PATH, audioPath)
            return intent
        }

        const val SECOND = 1000
    }

    override fun getLayout(): Int {
        return R.layout.activity_media_player;
    }

    override fun initView() {
        super.initView()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        if (intent != null && intent.hasExtra(EXTRA_PATH)) {
            audioPath = intent.getStringExtra(EXTRA_PATH)!!
        }

        mediaPlayer.setOnPreparedListener(this)
        mediaPlayer.apply {
            setDataSource(audioPath)
            prepareAsync()
        }
        seek_bar.setOnSeekBarChangeListener(this)
        btnPlay.isEnabled = false

        btnPlay.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                btnPlay.setImageResource(android.R.drawable.ic_media_play)
            } else {
                mediaPlayer.start()
                btnPlay.setImageResource(android.R.drawable.ic_media_pause)
            }
        }

    }

    // Converting seconds to mm:ss format to display on screen
    private fun timeInString(seconds: Int): String {
        return String.format(
                "%02d:%02d",
                (seconds / 3600 * 60 + ((seconds % 3600) / 60)),
                (seconds % 60)
        )
    }

    // Initialize seekBar
    private fun initializeSeekBar() {
        seek_bar.max = mediaPlayer.seconds
        text_progress.text = getString(R.string.default_value)
        text_total_time.text = timeInString(mediaPlayer.seconds)
        progress_bar.visibility = View.GONE
        btnPlay.isEnabled = true
    }

    // Update seek bar after every 1 second
    private fun updateSeekBar() {
        runnable = Runnable {
            text_progress.text = timeInString(mediaPlayer.currentSeconds)
            seek_bar.progress = mediaPlayer.currentSeconds
            handler.postDelayed(runnable, SECOND.toLong())
        }
        handler.postDelayed(runnable, SECOND.toLong())
    }


    // This function gets called when the media player gets ready
    override fun onPrepared(mediaPlayer: MediaPlayer?) {
        initializeSeekBar()
        updateSeekBar()
    }

    /**
     * Forward button click event
     * Forwards audio specified seconds
     * */
    private fun forward() {
        val currentPosition: Int = mediaPlayer.getCurrentPosition()
        // check if seekForward time is lesser than audio duration
        if (currentPosition + seekForwardTime <= mediaPlayer.getDuration()) {
            // forward audio
            mediaPlayer.seekTo(currentPosition + seekForwardTime)
        } else {
            // forward to end position
            mediaPlayer.seekTo(mediaPlayer.getDuration())
        }
    }

    /**
     * Backward button click event
     * Backward audio to specified seconds
     * */
    private fun backward() {
        // get current audio position
        val currentPosition: Int = mediaPlayer.getCurrentPosition()
        // check if seekBackward time is greater than 0 sec
        if (currentPosition - seekBackwardTime >= 0) {
            // forward audio
            mediaPlayer.seekTo(currentPosition - seekBackwardTime)
        } else {
            // backward to starting position
            mediaPlayer.seekTo(0)
        }
    }

    /**
     * Next button click event
     * Plays next audio by taking currentAudioIndex + 1
     * */
    /**
     * Next button click event
     * Plays next audio by taking currentAudioIndex + 1
     */
    private fun next() {
        // check if next audio is there or not
        if (currentAudioIndex < audiosList.size - 1) {
            playAudio(currentAudioIndex + 1)
            currentAudioIndex = currentAudioIndex + 1
        } else {
            // play first audio
            playAudio(0)
            currentAudioIndex = 0
        }
    }
    /**
     * Back button click event
     * Plays previous audio by currentAudioIndex - 1
     * */
    /**
     * Back button click event
     * Plays previous audio by currentAudioIndex - 1
     */
    private fun previous() {
        if (currentAudioIndex > 0) {
            playAudio(currentAudioIndex - 1)
            currentAudioIndex = currentAudioIndex - 1
        } else {
            // play last audio
            playAudio(audiosList.size - 1)
            currentAudioIndex = audiosList.size - 1
        }
    }

    /**
     * Button Click event for Repeat button
     * Enables repeat flag to true
     * */
    /**
     * Button Click event for Repeat button
     * Enables repeat flag to true
     */
    private fun repeat() {
        if (isRepeat) {
            isRepeat = false
            Toast.makeText(applicationContext, "Repeat is OFF", Toast.LENGTH_SHORT).show()
        } else {
            // make repeat to true
            isRepeat = true
            Toast.makeText(applicationContext, "Repeat is ON", Toast.LENGTH_SHORT).show()
            // make shuffle to false
            isShuffle = false
        }
    }

    /**
     * Button Click event for Shuffle button
     * Enables shuffle flag to true
     * */
    /**
     * Button Click event for Shuffle button
     * Enables shuffle flag to true
     */
    private fun shuffle() {
        if (isShuffle) {
            isShuffle = false
            Toast.makeText(applicationContext, "Shuffle is OFF", Toast.LENGTH_SHORT).show()
        } else {
            // make repeat to true
            isShuffle = true
            Toast.makeText(applicationContext, "Shuffle is ON", Toast.LENGTH_SHORT).show()
            // make shuffle to false
            isRepeat = false
        }
    }

    /**
     * On audio Playing completed
     * if repeat is ON play same audio again
     * if shuffle is ON play random audio
     * */
    override fun onCompletion(p0: MediaPlayer?) {
        if (isRepeat) {
            // repeat is on play same audio again
            playAudio(currentAudioIndex)
        } else if (isShuffle) {
            // shuffle is on - play a random audio
            val rand = Random()
            currentAudioIndex = rand.nextInt(audiosList.size - 1 - 0 + 1) + 0
            playAudio(currentAudioIndex)
        } else {
            // no repeat or shuffle ON - play next audio
            if (currentAudioIndex < audiosList.size - 1) {
                playAudio(currentAudioIndex + 1)
                currentAudioIndex = currentAudioIndex + 1
            } else {
                // play first audio
                playAudio(0)
                currentAudioIndex = 0
            }
        }
    }

    // Update media player when user changes seekBar
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser)
            mediaPlayer.seekTo(progress * SECOND)
    }

    // Ignore
    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    // Ignore
    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }


    /**
     * Function to play a audio
     * @param audioIndex - index of audio
     */
    fun playAudio(audioIndex: Int) {
        // Play audio
        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(audiosList.get(audioIndex))
            mediaPlayer.prepare()
            mediaPlayer.start()
            // Displaying Audio title

            // Changing Button Image to pause image
            btnPlay.setImageResource(android.R.drawable.ic_media_pause)

        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Release the media player resources when activity gets destroyed
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        mediaPlayer.release()
    }


    // Creating an extension properties to get the media player total time and current duration in seconds
    private val MediaPlayer.seconds: Int
        get() {
            return this.duration / SECOND
        }

    private val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / SECOND
        }


}
