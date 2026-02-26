package com.example.gemhuntgame.config

import com.example.gemhuntgame.R

object AppConfig {
    // 1. 问候场景
    val WELCOME_PANDA_IMAGES = listOf(
        R.drawable.panda_welcome_1,
        R.drawable.panda_welcome_2,
        R.drawable.panda_welcome_1
    )
    val WELCOME_TEXTS = listOf(
        "小朋友你好呀，我是熊猫若若～",
        "我们今天要一起玩找宝石的游戏哦！",
        "准备好了吗？点击我进入游戏吧～"
    )

    // 2. 8扇门配置
    data class DoorRes(val close: Int, val open: Int)
    val DOOR_RESOURCES = listOf(
        DoorRes(R.drawable.door_1_close, R.drawable.door_1_open),
        DoorRes(R.drawable.door_2_close, R.drawable.door_2_open),
        DoorRes(R.drawable.door_3_close, R.drawable.door_3_open),
        DoorRes(R.drawable.door_4_close, R.drawable.door_4_open),
        DoorRes(R.drawable.door_5_close, R.drawable.door_5_open),
        DoorRes(R.drawable.door_6_close, R.drawable.door_6_open),
        DoorRes(R.drawable.door_7_close, R.drawable.door_7_open),
        DoorRes(R.drawable.door_8_close, R.drawable.door_8_open)
    )
    val TASK_TYPES = listOf(
        "MUTUAL_EXCLUSION", "INFO_INFERENCE", "TEMPORARY_MEANING", "SPEAKER_PREFERENCE",
        "DISCOURSE_NOVELTY", "DISCOURSE_COHERENCE", "CARD_SORTING", "SAMPLE_MATCHING"
    )

    // 3. 互斥性任务
    object MutualExclusion {
        const val TRIAL_COUNT = 5
        val TRIAL_TEXTS = listOf(
            "你可以把若七拿给我吗？",
            "你可以把一苏拿给我吗？",
            "你可以把可棠拿给我吗？",
            "你可以把离演拿给我吗？",
            "你可以把蠪蛭拿给我吗？"
        )
        val TRIAL_IMAGES = listOf(
            Pair(R.drawable.mutual_ruoqi, R.drawable.mutual_dog),
            Pair(R.drawable.mutual_yisu, R.drawable.mutual_cat),
            Pair(R.drawable.mutual_ketang, R.drawable.mutual_bird),
            Pair(R.drawable.mutual_liyan, R.drawable.mutual_fish),
            Pair(R.drawable.mutual_longzhi, R.drawable.mutual_rabbit)
        )
        const val FEEDBACK_CORRECT = "答对啦！摸摸我的头继续～"
        const val FEEDBACK_WRONG = "选错啦～摸摸我的头再试一次吧～"
        const val HALO_IMAGE = R.drawable.halo_effect
    }

    // 4. 信息量推理任务
    object InfoInference {
        const val TRIAL_COUNT = 7
        const val TRAINING_TRIAL_COUNT = 2
        val TRAINING_IMAGES = listOf(
            Pair(R.drawable.info_train1_left, R.drawable.info_train1_right),
            Pair(R.drawable.info_train2_left, R.drawable.info_train2_right)
        )
        val FORMAL_IMAGES = listOf(
            Pair(R.drawable.info_formal1_left, R.drawable.info_formal1_right),
            Pair(R.drawable.info_formal2_left, R.drawable.info_formal2_right),
            Pair(R.drawable.info_formal3_left, R.drawable.info_formal3_right),
            Pair(R.drawable.info_formal4_left, R.drawable.info_formal4_right),
            Pair(R.drawable.info_formal5_left, R.drawable.info_formal5_right)
        )
        val TRIAL_TEXTS = listOf(
            "看这只玩偶，它的身上有琰玄，特别好看～",
            "看这只玩偶，它的身上有仑竹，特别好看～",
            "哪一只身上也有级梦呢？你可以拿给我吗？",
            "哪一只身上也有青飞呢？你可以拿给我吗？",
            "哪一只身上也有书瑶呢？你可以拿给我吗？",
            "哪一只身上也有星辞呢？你可以拿给我吗？",
            "哪一只身上也有云舒呢？你可以拿给我吗？"
        )
    }

    // 5. 临时隐含意义任务
    object TemporaryMeaning {
        const val TRIAL_COUNT = 7
        const val TRAINING_TRIAL_COUNT = 2
        val TRIAL_TEXTS = listOf(
            "我想要有苹果的盒子～",
            "我想要有香蕉的篮子～",
            "我想要有饺子的篮子～",
            "我想要有蛋糕的盒子～",
            "我想要有糖果的罐子～",
            "我想要有饼干的盘子～",
            "我想要有牛奶的杯子～"
        )
        val TRIAL_IMAGES = listOf(
            Pair(R.drawable.temp_train1_left, R.drawable.temp_train1_right),
            Pair(R.drawable.temp_train2_left, R.drawable.temp_train2_right),
            Pair(R.drawable.temp_formal1_left, R.drawable.temp_formal1_right),
            Pair(R.drawable.temp_formal2_left, R.drawable.temp_formal2_right),
            Pair(R.drawable.temp_formal3_left, R.drawable.temp_formal3_right),
            Pair(R.drawable.temp_formal4_left, R.drawable.temp_formal4_right),
            Pair(R.drawable.temp_formal5_left, R.drawable.temp_formal5_right)
        )
    }

    // 6. 说话者偏好任务
    object SpeakerPreference {
        const val TRIAL_COUNT = 5
        val TRIAL_TEXTS = listOf(
            "我非常喜欢这个～",
            "我真的不喜欢这个～",
            "你可以把稻呼拿给我吗？",
            "我非常喜欢这个～",
            "你可以把耳喵拿给我吗？"
        )
        val TRIAL_IMAGES = listOf(
            Pair(R.drawable.speaker_1_left, R.drawable.speaker_1_right),
            Pair(R.drawable.speaker_2_left, R.drawable.speaker_2_right),
            Pair(R.drawable.speaker_3_left, R.drawable.speaker_3_right),
            Pair(R.drawable.speaker_4_left, R.drawable.speaker_4_right),
            Pair(R.drawable.speaker_5_left, R.drawable.speaker_5_right)
        )
    }

    // 7. 语篇新颖性任务
    object DiscourseNovelty {
        const val TRIAL_COUNT = 5
        val TRIAL_TEXTS = listOf(
            "这张桌子上什么也没有～",
            "这张桌子上有东西～",
            "你可以选一个给我吗？",
            "这张桌子上什么也没有～",
            "你可以选一个给我吗？"
        )
        val TRIAL_IMAGES = listOf(
            Pair(R.drawable.novelty_1_empty, R.drawable.novelty_1_item),
            Pair(R.drawable.novelty_2_empty, R.drawable.novelty_2_item),
            Pair(R.drawable.novelty_3_empty, R.drawable.novelty_3_item),
            Pair(R.drawable.novelty_4_empty, R.drawable.novelty_4_item),
            Pair(R.drawable.novelty_5_empty, R.drawable.novelty_5_item)
        )
    }

    // 8. 语篇连贯性任务
    object DiscourseCoherence {
        const val ROUND_COUNT = 5
        const val EXPOSURE_TRIAL = 5
        const val TEST_TRIAL = 1
        val CATEGORY_IMAGES = mapOf(
            "VEHICLE" to listOf(R.drawable.coherence_car, R.drawable.coherence_bus, R.drawable.coherence_plane),
            "MUSIC" to listOf(R.drawable.coherence_drum, R.drawable.coherence_erhu, R.drawable.coherence_xiao),
            "FRUIT" to listOf(R.drawable.coherence_watermelon, R.drawable.coherence_lemon, R.drawable.coherence_banana)
        )
        val TRIAL_TEXTS = listOf(
            "请点击火车～",
            "好！摸摸我的头，让我们再看看别的吧～",
            "请点击公交车～",
            "请点击飞机～",
            "你点击它～"
        )
    }

    // 9. 卡片分类任务
    object CardSorting {
        const val COLOR_TRIAL = 6
        const val SHAPE_TRIAL = 6
        const val DEMO_TEXT = "这里有很多魔法卡片，刚开始我们要按颜色分类，之后按照形状分类，然后就可以找到宝石啦！我们先按颜色分类，我先做给你看一遍。"
        const val COLOR_TEXT = "就像这样，按颜色来。看明白了吗？现在摸摸我的头，然后你自己来试试吧！"
        const val SHAPE_TEXT = "接下来按照形状分类，记住，是相同形状的卡片才能放在一起哦！你明白了吗？开始吧！"
        val CARD_IMAGES = listOf(
            R.drawable.card_rabbit_pink, R.drawable.card_ship_blue,
            R.drawable.card_cat_red, R.drawable.card_car_yellow,
            R.drawable.card_dog_round, R.drawable.card_bird_square
        )
    }

    // 10. 相关样本匹配任务
    object SampleMatching {
        const val TRIAL_COUNT = 8
        const val TRAINING_TRIAL = 2
        val TRIAL_IMAGES = listOf(
            Triple(R.drawable.match_sample1, R.drawable.match_option1, R.drawable.match_option2),
            Triple(R.drawable.match_sample2, R.drawable.match_option3, R.drawable.match_option4),
            Triple(R.drawable.match_sample3, R.drawable.match_option5, R.drawable.match_option6),
            Triple(R.drawable.match_sample4, R.drawable.match_option7, R.drawable.match_option8),
            Triple(R.drawable.match_sample5, R.drawable.match_option9, R.drawable.match_option10),
            Triple(R.drawable.match_sample6, R.drawable.match_option11, R.drawable.match_option12),
            Triple(R.drawable.match_sample7, R.drawable.match_option13, R.drawable.match_option14),
            Triple(R.drawable.match_sample8, R.drawable.match_option15, R.drawable.match_option16)
        )
        const val PROMPT_TEXT = "下面两张卡片中哪一张与上面的最像呢？"
    }

    // 通用配置
    const val PANDA_HEAD_LEFT = 0.4f
    const val PANDA_HEAD_TOP = 0.1f
    const val PANDA_HEAD_RIGHT = 0.6f
    const val PANDA_HEAD_BOTTOM = 0.3f
    const val PANDA_CORRECT = R.drawable.panda_correct
    const val PANDA_WRONG = R.drawable.panda_wrong
}