package com.example.gemhuntgame.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gemhuntgame.R
import com.example.gemhuntgame.config.AppConfig
import com.example.gemhuntgame.utils.RandomUtils

class SpeakerPreferenceFragment(private val doorId: Int) : Fragment() {
    private var currentTrial = 0
    private lateinit var ivPanda: ImageView
    private lateinit var tvPrompt: TextView
    private lateinit var ivLeft: ImageView
    private lateinit var ivRight: ImageView
    private lateinit var ivHalo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_speaker_preference, container, false)

        ivPanda = view.findViewById(R.id.iv_panda)
        tvPrompt = view.findViewById(R.id.tv_prompt)
        ivLeft = view.findViewById(R.id.iv_left)
        ivRight = view.findViewById(R.id.iv_right)
        ivHalo = view.findViewById(R.id.iv_halo)

        startTrial()

        ivLeft.setOnClickListener { checkAnswer(it, true) }
        ivRight.setOnClickListener { checkAnswer(it, false) }

        ivPanda.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val screenWidth = resources.displayMetrics.widthPixels
                val screenHeight = resources.displayMetrics.heightPixels
                val headLeft = screenWidth * AppConfig.PANDA_HEAD_LEFT
                val headTop = screenHeight * AppConfig.PANDA_HEAD_TOP
                val headRight = screenWidth * AppConfig.PANDA_HEAD_RIGHT
                val headBottom = screenHeight * AppConfig.PANDA_HEAD_BOTTOM

                if (event.x in headLeft..headRight && event.y in headTop..headBottom) {
                    currentTrial++
                    if (currentTrial >= AppConfig.SpeakerPreference.TRIAL_COUNT) {
                        (activity as com.example.gemhuntgame.MainActivity).replaceFragment(
                            com.example.gemhuntgame.ui.DoorSelectionFragment()
                        )
                    } else {
                        startTrial()
                    }
                }
            }
            true
        }

        return view
    }

    private fun startTrial() {
        ivHalo.visibility = View.GONE
        tvPrompt.text = AppConfig.SpeakerPreference.TRIAL_TEXTS[currentTrial]

        val images = AppConfig.SpeakerPreference.TRIAL_IMAGES[currentTrial]
        if (RandomUtils.getRandomBoolean()) {
            ivLeft.setImageResource(images.first)
            ivRight.setImageResource(images.second)
        } else {
            ivLeft.setImageResource(images.second)
            ivRight.setImageResource(images.first)
        }
    }

    private fun checkAnswer(view: View, isCorrect: Boolean) {
        ivHalo.visibility = View.VISIBLE
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        ivHalo.x = location[0].toFloat()
        ivHalo.y = location[1].toFloat()

        tvPrompt.text = if (isCorrect) {
            AppConfig.MutualExclusion.FEEDBACK_CORRECT
        } else {
            AppConfig.MutualExclusion.FEEDBACK_WRONG
        }
    }
}