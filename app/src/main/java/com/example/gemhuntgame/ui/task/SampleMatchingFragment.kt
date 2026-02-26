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

class SampleMatchingFragment(private val doorId: Int) : Fragment() {
    private var currentTrial = 0
    private lateinit var ivPanda: ImageView
    private lateinit var tvPrompt: TextView
    private lateinit var ivSample: ImageView
    private lateinit var ivOption1: ImageView
    private lateinit var ivOption2: ImageView
    private lateinit var ivHalo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sample_matching, container, false)

        ivPanda = view.findViewById(R.id.iv_panda)
        tvPrompt = view.findViewById(R.id.tv_prompt)
        ivSample = view.findViewById(R.id.iv_sample)
        ivOption1 = view.findViewById(R.id.iv_option1)
        ivOption2 = view.findViewById(R.id.iv_option2)
        ivHalo = view.findViewById(R.id.iv_halo)

        startTrial()

        ivOption1.setOnClickListener { checkAnswer(it, true) }
        ivOption2.setOnClickListener { checkAnswer(it, false) }

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
                    if (currentTrial >= AppConfig.SampleMatching.TRIAL_COUNT) {
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
        tvPrompt.text = AppConfig.SampleMatching.PROMPT_TEXT

        val (sample, opt1, opt2) = AppConfig.SampleMatching.TRIAL_IMAGES[currentTrial]
        ivSample.setImageResource(sample)

        if (RandomUtils.getRandomBoolean()) {
            ivOption1.setImageResource(opt1)
            ivOption2.setImageResource(opt2)
        } else {
            ivOption1.setImageResource(opt2)
            ivOption2.setImageResource(opt1)
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