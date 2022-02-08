package com.jw.androidday10

import android.media.AsyncPlayer
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import com.jw.androidday10.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), Runnable {
    private val FAll_DURATION: Long = 2000
    private lateinit var binding: ActivityMainBinding
    private var squareDegree: Float = 0.0f
    private var squareColor = 0
    private var ballColor = 0
    private var isRunning = false
    private var score = 0
    private val handler = Handler()
    private var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLeft.setOnClickListener {
            rotateSquareToLeft()
        }
        binding.btnRight.setOnClickListener {
            rotateSquareToRight()
        }
        binding.imgStart.setOnClickListener {
            startGame()
        }
        Thread(this).start()

    }

    private fun playAudio(audio: Int) {
        mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(this, audio)
        mediaPlayer.start()
    }

    private fun startGame() {
        ballColor = 0
        score = 0
        squareColor = 0
        binding.imgSquare.clearAnimation()
        binding.tvScore.text = "0"
        binding.imgStart.visibility = View.INVISIBLE
        isRunning = true
    }

    private fun ballFall() {
        ballColor = java.util.Random().nextInt(4)
        binding.imgBall.setImageResource(R.drawable.bo_0 + ballColor)
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 0.9f
        ).apply {
            duration = FAll_DURATION
        }
        binding.imgBall.startAnimation(animation)
    }

    private fun rotateSquareToLeft() {
        val rotateAnimation = RotateAnimation(
            squareDegree, squareDegree - 90,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 250
            fillAfter = true
        }
        squareDegree -= 90
        squareColor++
        if (squareColor > 3) {
            squareColor = 0
        }
        binding.imgSquare.startAnimation(rotateAnimation)
    }

    private fun rotateSquareToRight() {
        val rotateAnimation = RotateAnimation(
            squareDegree, squareDegree + 90,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 250
            fillAfter = true
        }
        squareDegree += 90
        squareColor--
        if (squareColor < 0) {
            squareColor = 3
        }
        binding.imgSquare.startAnimation(rotateAnimation)
    }

    override fun run() {
        while (true) {
            if (isRunning) {
                ballFall()
                Thread.sleep(FAll_DURATION)
                if (squareColor == ballColor) {
                    score++
                    handler.post {
                        binding.tvScore.text = score.toString()
                    }
                    playAudio(R.raw.point)
                } else {
                    isRunning = false
                    handler.post {
                        binding.imgStart.visibility = View.VISIBLE
                    }
                    playAudio(R.raw.gameover)
                }
            }
        }
    }

}