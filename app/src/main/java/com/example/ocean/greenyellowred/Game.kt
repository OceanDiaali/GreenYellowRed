package com.example.ocean.greenyellowred

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class Game : AppCompatActivity() {

    var scoreCount: Int = 0
    var hiScore: Int = 0

    var imageArray = ArrayList<ImageView>()

    var handler: Handler = Handler()
    var runnable: Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        imageArray = arrayListOf(green1, green2, green3, yellow1, yellow2, yellow3, red1, red2, red3)

        countDownTimer()
        workImages()
        scorePoints()
    } // end of on create

    fun countDownTimer() {
        object: CountDownTimer(21000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timer_view.text = "${millisUntilFinished / 1000}"
            }
            override fun onFinish() {
                timer_view.text = "End!"
                if (scoreCount > hiScore) {
                    hiScore = scoreCount
                    hi_score_view.text = "HiScore:$hiScore"
                }
                // freeze game
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
            }
        }.start()
    } // end of timer func

    fun workImages() {

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = (0..8).random()
                imageArray[random].visibility = View.VISIBLE

                handler.postDelayed(runnable, 1000)
            }
        }
                handler.post(runnable)

    } //end of work images

    fun scorePoints() {
        for (image in imageArray) {
            image.setOnClickListener {
                //scoreCount++
                //score_view.text = "score:$scoreCount"
                when(image) {
                    green1 -> {
                        scoreCount = scoreCount + 2
                        score_view.text = "score:$scoreCount"
                    }

                    green2 -> {
                        scoreCount = scoreCount + 2
                        score_view.text = "score:$scoreCount"
                    }

                    green3 -> {
                        scoreCount = scoreCount + 2
                        score_view.text = "score:$scoreCount"
                    }

                    // ++++++++++++++++++++++++++++++++++++

                    yellow1 -> {
                        scoreCount++
                        score_view.text = "score:$scoreCount"
                    }

                    yellow2 -> {
                        scoreCount++
                        score_view.text = "score:$scoreCount"
                    }

                    yellow3 -> {
                        scoreCount++
                        score_view.text = "score:$scoreCount"
                    }

                    // +++++++++++++++++++++++++++++++++++++

                    red1 -> {
                        scoreCount--
                        score_view.text = "score:$scoreCount"
                    }

                    red2 -> {
                        scoreCount--
                        score_view.text = "score:$scoreCount"
                    }

                    red3 -> {
                        scoreCount--
                        score_view.text = "score:$scoreCount"
                    }

                }
            }
        }
    } //end of score points

} // end of class
