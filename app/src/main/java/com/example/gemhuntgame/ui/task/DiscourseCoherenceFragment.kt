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

class DiscourseCoherenceFragment(private val doorId: Int) : Fragment() {
    private var currentRound = 0
    private var currentTrial = 0
    private lateinit var ivPanda: ImageView
    private lateinit var tvPrompt: TextView
    private lateinit var ivItem1: ImageView
    private lateinit var ivItem2: ImageView
    private lateinit var ivItem3: ImageView
    private lateinit var ivHalo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_discourse_coherence, container, false)

        ivPanda = view.findViewById(R.id.iv_panda)
        tvPrompt = view.findViewById(R.id.tv_prompt)
        ivItem1 = view.findViewById(R.id.iv_item1)
        ivItem2 = view.findViewById(R.id.iv_item2)
        ivItem3 = view.findViewById(R.id.iv_item3)
        ivHalo = view.findViewById(R.id.iv_halo)

        startRound()

        ivItem1.setOnClickListener { showHalo(it) }
        ivItem2.setOnClickListener { showHalo(it) }
        ivItem3.setOnClickListener { showHalo(it) }

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
                    if (currentTrial >= AppConfig.DiscourseCoherence.EXPOSURE_TRIAL + AppConfig.DiscourseCoherence.TEST_TRIAL) {
                        currentRound++
                        if (currentRound >= AppConfig.DiscourseCoherence.ROUND_COUNT) {
                            (activity as com.example.gemhuntgame.MainActivity).replaceFragment(
                                com.example.gemhuntgame.ui.DoorSelectionFragment()
                            )
                        } else {
                            startRound()
                        }
                    } else {
                        startTrial()
                    }
                }
            }
            true
        }

        return view
    }

    private fun startRound() {
        currentTrial = 0
        startTrial()
    }

    private fun startTrial() {
        ivHalo.visibility = View.GONE
        tvPrompt.text = AppConfig.DiscourseCoherence.TRIAL_TEXTS[currentTrial % AppConfig.DiscourseCoherence.TRIAL_TEXTS.size]

        val vehicleImages = AppConfig.DiscourseCoherence.CATEGORY_IMAGES["VEHICLE"]!!
        val musicImages = AppConfig.DiscourseCoherence.CATEGORY_IMAGES["MUSIC"]!!
        val fruitImages = AppConfig.DiscourseCoherence.CATEGORY_IMAGES["FRUIT"]!!

        val randomV = RandomUtils.getRandomInt(0, vehicleImages.size-1)
        val randomM = RandomUtils.getRandomInt(0, musicImages.size-1)
        val randomF = RandomUtils.getRandomInt(0, fruitImages.size-1)

        val images = listOf(vehicleImages[randomV], musicImages[randomM], fruitImages[randomF]).shuffled()
        ivItem1.setImageResource(images[0])
        ivItem2.setImageResource(images[1])
        ivItem3.setImageResource(images[2])
    }

    private fun showHalo(view: View) {
        ivHalo.visibility = View.VISIBLE
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        ivHalo.x = location[0].toFloat()
        ivHalo.y = location[1].toFloat()
        tvPrompt.text = AppConfig.MutualExclusion.FEEDBACK_CORRECT
    }
}