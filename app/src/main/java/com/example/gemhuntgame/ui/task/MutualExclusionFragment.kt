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

class MutualExclusionFragment(private val doorId: Int) : Fragment() {
    private var currentTrial = 0
    private lateinit var ivPanda: ImageView
    private lateinit var tvPrompt: TextView
    private lateinit var ivItem1: ImageView
    private lateinit var ivItem2: ImageView
    private lateinit var ivHalo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mutual_exclusion, container, false)

        ivPanda = view.findViewById(R.id.iv_panda)
        tvPrompt = view.findViewById(R.id.tv_prompt)
        ivItem1 = view.findViewById(R.id.iv_item1)
        ivItem2 = view.findViewById(R.id.iv_item2)
        ivHalo = view.findViewById(R.id.iv_halo)

        startTrial()

        ivItem1.setOnClickListener { showHalo(it) }
        ivItem2.setOnClickListener { showHalo(it) }

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
                    if (currentTrial >= AppConfig.MutualExclusion.TRIAL_COUNT) {
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
        tvPrompt.text = AppConfig.MutualExclusion.TRIAL_TEXTS[currentTrial]

        val (novelItem, familiarItem) = AppConfig.MutualExclusion.TRIAL_IMAGES[currentTrial]
        if (RandomUtils.getRandomBoolean()) {
            ivItem1.setImageResource(novelItem)
            ivItem2.setImageResource(familiarItem)
        } else {
            ivItem1.setImageResource(familiarItem)
            ivItem2.setImageResource(novelItem)
        }
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