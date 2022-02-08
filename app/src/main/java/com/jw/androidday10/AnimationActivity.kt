package com.jw.androidday10

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jw.androidday10.databinding.ActivityAnimationBinding

public class AnimationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgRotate.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
            binding.imgRotate.startAnimation(animation)
        }

        binding.imgScale.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale)
            binding.imgScale.startAnimation(animation)
        }

        binding.imgTranslate.setOnClickListener {
            Toast.makeText(this, "I am flying", Toast.LENGTH_SHORT).show()
            val animation = AnimationUtils.loadAnimation(this, R.anim.ainm_mb)
            binding.imgTranslate.startAnimation(animation)
        }

        binding.tvTranslate.setOnClickListener {
//            val animation: ValueAnimator = ValueAnimator.ofFloat(0f, 700f).apply {
//                duration = 1000
//                repeatCount = ValueAnimator.INFINITE
//                repeatMode = ValueAnimator.REVERSE
//            }
//            animation.addUpdateListener { anim ->
//                val current = anim.animatedValue as Float
//                binding.tvTranslate.x = current
//            }
//            animation.start()

            val animation = ObjectAnimator.ofFloat(binding.tvTranslate, View.TRANSLATION_X, 700f).apply {
                    duration = 1000
                    repeatMode = ObjectAnimator.REVERSE
                    repeatCount = ObjectAnimator.INFINITE
                }
            animation.start()
        }

        binding.tvAlpha.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha)
            animation.duration = 2000
            binding.tvAlpha.startAnimation(animation)
        }
    }
}