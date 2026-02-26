package com.example.gemhuntgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.gemhuntgame.R
import com.example.gemhuntgame.config.AppConfig
import com.example.gemhuntgame.model.DoorModel
import com.example.gemhuntgame.ui.task.*
import com.example.gemhuntgame.utils.RandomUtils

class DoorSelectionFragment : Fragment() {
    private lateinit var doorModels: MutableList<DoorModel>
    private lateinit var doorImages: List<ImageView>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_door_selection, container, false)

        doorModels = mutableListOf()
        val randomTasks = RandomUtils.shuffleList(AppConfig.TASK_TYPES)
        for (i in 0 until 8) {
            doorModels.add(
                DoorModel(
                    id = i+1,
                    taskType = randomTasks[i],
                    isOpened = false,
                    closeRes = AppConfig.DOOR_RESOURCES[i].close,
                    openRes = AppConfig.DOOR_RESOURCES[i].open
                )
            )
        }

        doorImages = listOf(
            view.findViewById(R.id.iv_door1),
            view.findViewById(R.id.iv_door2),
            view.findViewById(R.id.iv_door3),
            view.findViewById(R.id.iv_door4),
            view.findViewById(R.id.iv_door5),
            view.findViewById(R.id.iv_door6),
            view.findViewById(R.id.iv_door7),
            view.findViewById(R.id.iv_door8)
        )

        updateDoorsUI()

        doorImages.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                val door = doorModels[index]
                if (!door.isOpened) {
                    when (door.taskType) {
                        "MUTUAL_EXCLUSION" -> (activity as com.example.gemhuntgame.MainActivity).replaceFragment(MutualExclusionFragment(door.id))
                        "INFO_INFERENCE" -> (activity as com.example.gemhuntgame.MainActivity).replaceFragment(InfoInferenceFragment(door.id))
                        "TEMPORARY_MEANING" -> (activity as com.example.gemhuntgame.MainActivity).replaceFragment(TemporaryMeaningFragment(door.id))
                        "SPEAKER_PREFERENCE" -> (activity as com.example.gemhuntgame.MainActivity).replaceFragment(SpeakerPreferenceFragment(door.id))
                        "DISCOURSE_NOVELTY" -> (activity as com.example.gemhuntgame.MainActivity).replaceFragment(DiscourseNoveltyFragment(door.id))
                        "DISCOURSE_COHERENCE" -> (activity as com.example.gemhuntgame.MainActivity).replaceFragment(DiscourseCoherenceFragment(door.id))
                        "CARD_SORTING" -> (activity as com.example.gemhuntgame.MainActivity).replaceFragment(CardSortingFragment(door.id))
                        "SAMPLE_MATCHING" -> (activity as com.example.gemhuntgame.MainActivity).replaceFragment(SampleMatchingFragment(door.id))
                    }
                    door.isOpened = true
                    updateDoorsUI()
                }
            }
        }

        return view
    }

    private fun updateDoorsUI() {
        doorModels.forEachIndexed { index, door ->
            doorImages[index].setImageResource(if (door.isOpened) door.openRes else door.closeRes)
        }
    }
}