package com.example.gemhuntgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gemhuntgame.R
import com.example.gemhuntgame.config.AppConfig

class WelcomeFragment : Fragment() {
    private var currentStep = 0
    private lateinit var ivPanda: ImageView
    private lateinit var tvText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        ivPanda = view.findViewById(R.id.iv_panda)
        tvText = view.findViewById(R.id.tv_welcome_text)

        updateUI()

        ivPanda.setOnClickListener {
            currentStep++
            if (currentStep >= AppConfig.WELCOME_TEXTS.size) {
                (activity as com.example.gemhuntgame.MainActivity).replaceFragment(DoorSelectionFragment())
            } else {
                updateUI()
            }
        }
        return view
    }

    private fun updateUI() {
        ivPanda.setImageResource(AppConfig.WELCOME_PANDA_IMAGES[currentStep])
        tvText.text = AppConfig.WELCOME_TEXTS[currentStep]
    }
}