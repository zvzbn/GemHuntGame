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

class CardSortingFragment(private val doorId: Int) : Fragment() {
    private var isColorMode = true
    private var currentTrial = 0
    private lateinit var ivPanda: ImageView
    private lateinit var tvPrompt: TextView
    private lateinit var ivCard1: ImageView
    private lateinit var ivCard2: ImageView
    private lateinit var ivHalo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_card_sorting, container, false)

        ivPanda = view.findViewById(R.id.iv_panda)
        tvPrompt = view.findViewById(R.id.tv_prompt)
        ivCard1 = view.findViewById(R.id.iv_card1)
        ivCard2 = view.findViewById(R.id.iv_card2)
        ivHalo = view.findViewById(R.id.iv_halo)

        tvPrompt.text = AppConfig.CardSorting.DEMO_TEXT
        initCards()

        ivCard1.setOnClickListener { checkSort(it) }
        ivCard2.setOnClickListener { checkSort(it) }

        ivPanda.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val screenWidth = resources.displayMetrics.widthPixels
                val screenHeight = resources.displayMetrics.heightPixels
                val headLeft = screenWidth * AppConfig.PANDA_HEAD_LEFT
                val headTop = screenHeight * AppConfig.PANDA_HEAD_TOP
                val headRight = screenWidth * AppConfig.PANDA_HEAD_RIGHT
                val headBottom = screenHeight * AppConfig.PANDA_HEAD_BOTTOM

                if (event.x in headLeft..headRight && event.y in headTop..headBottom) {
                    if (tvPrompt.text == AppConfig.CardSorting.DEMO_TEXT) {
                        tvPrompt.text = AppConfig.CardSorting.COLOR_TEXT
                    } else if (tvPrompt.text == AppConfig.CardSorting.COLOR_TEXT) {
                        startColorTrial()
                    } else {
                        currentTrial++
                        if (isColorMode && currentTrial >= AppConfig.CardSorting.COLOR_TRIAL) {
                            isColorMode = false
                            currentTrial = 0
                            tvPrompt.text = AppConfig.CardSorting.SHAPE_TEXT
                        } else if (!isColorMode && currentTrial >= AppConfig.CardSorting.SHAPE_TRIAL) {
                            (activity as com.example.gemhuntgame.MainActivity).replaceFragment(
                                com.example.gemhuntgame.ui.DoorSelectionFragment()
                            )
                        } else {
                            if (isColorMode) startColorTrial() else startShapeTrial()
                        }
                    }
                }
            }
            true
        }

        return view
    }

    private fun initCards() {
        ivCard1.setImageResource(AppConfig.CardSorting.CARD_IMAGES[0])
        ivCard2.setImageResource(AppConfig.CardSorting.CARD_IMAGES[1])
    }

    private fun startColorTrial() {
        ivHalo.visibility = View.GONE
        tvPrompt.text = "按颜色分类，点击正确的卡片～"
        val random1 = RandomUtils.getRandomInt(2, 3)
        val random2 = if (random1 == 2) 3 else 2
        ivCard1.setImageResource(AppConfig.CardSorting.CARD_IMAGES[random1])
        ivCard2.setImageResource(AppConfig.CardSorting.CARD_IMAGES[random2])
    }

    private fun startShapeTrial() {
        ivHalo.visibility = View.GONE
        tvPrompt.text = "按形状分类，点击正确的卡片～"
        val random1 = RandomUtils.getRandomInt(4, 5)
        val random2 = if (random1 == 4) 5 else 4
        ivCard1.setImageResource(AppConfig.CardSorting.CARD_IMAGES[random1])
        ivCard2.setImageResource(AppConfig.CardSorting.CARD_IMAGES[random2])
    }

    private fun checkSort(view: View) {
        ivHalo.visibility = View.VISIBLE
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        ivHalo.x = location[0].toFloat()
        ivHalo.y = location[1].toFloat()

        tvPrompt.text = if (isColorMode) {
            "颜色分类正确！摸摸头继续～"
        } else {
            "形状分类正确！摸摸头继续～"
        }
    }
}