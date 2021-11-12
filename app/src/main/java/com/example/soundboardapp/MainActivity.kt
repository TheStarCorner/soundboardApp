package com.example.soundboardapp

import android.os.Bundle
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.view.View
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity

// soundPool is a great program to import for Android apps when it comes
// to playing short sounds like a .wav file.

// The code below is a collection of the sounds from the raw folder with the .wav
// files. Also, It uses the soundPool tool for the soundboard app.
class MainActivity : AppCompatActivity() {
    private var soundPool: SoundPool? = null
    private var blast = 0
    private var clicksound = 0
    private var complete = 0
    private var congrats = 0
    private var jumpsound = 0
    private var pause = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build()
        } else {
            SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        }
        /// This imports the .wav files from the raw folder for
        /// the soundPool program.
        blast = soundPool!!.load(this, R.raw.blast, 1)
        clicksound = soundPool!!.load(this, R.raw.clicksound, 1)
        complete = soundPool!!.load(this, R.raw.complete, 1)
        congrats = soundPool!!.load(this, R.raw.congrats, 1)
        jumpsound = soundPool!!.load(this, R.raw.jumpsound, 1)
        pause = soundPool!!.load(this, R.raw.pause, 1)

        // For buttons 1-6, the process creates a val variable that
        // makes each button playable and listenable in the app.

        // The loop is set to 0 to prevent it from playing nonstop.

        val button1 = findViewById<Button>(R.id.button_sound1)
        button1.setOnClickListener{
            soundPool!!.play(blast, 1f, 1f, 0, 0, 1f)

        }
        val button2 = findViewById<Button>(R.id.button_sound2)
        button2.setOnClickListener{
            soundPool!!.play( clicksound, 1f, 1f, 0, 0, 1f)
        }
        val button3 = findViewById<Button>(R.id.button_sound3)
        button3.setOnClickListener{
            soundPool!!.play( complete, 1f, 1f, 0, 0, 1f)
        }
        val button4 = findViewById<Button>(R.id.button_sound4)
        button4.setOnClickListener{
            soundPool!!.play( congrats, 1f, 1f, 0, 0, 1f)
        }
        val button5 = findViewById<Button>(R.id.button_sound5)
        button5.setOnClickListener{
            soundPool!!.play( jumpsound, 1f, 1f, 0, 0, 1f)
        }
        val button6 = findViewById<Button>(R.id.button_sound6)
        button6.setOnClickListener{
            soundPool!!.play( pause, 1f, 1f, 0, 0, 1f)
        }
    }

    // This piece of code helps the soundPool sound effects stop playing after
    // the user presses the button once.

    // In other words, once the user presses the button, the sound plays, then
    // it goes back to silence.
    override fun onDestroy() {
        super.onDestroy()
        soundPool!!.release()
        soundPool = null
    }
}