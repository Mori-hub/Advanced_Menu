package com.src.tools.advmenus

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import com.google.android.material.card.MaterialCardView

class FallDownMenu @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    parentLayout: Int
) : View(context, attrs, defStyleAttr) {


    private var activity: Activity = context as Activity
    private var view: View
    private var icons = ArrayList<Int>()

    private var boxImage = ArrayList<CardView>()
    private var boxImageIn = ArrayList<ImageView>()
    private var boxText = ArrayList<TextView>()

    private var mainFallDown: MaterialCardView


    private var runningFunctions = ArrayList<(() -> Unit)?>()
    private var backColors = ArrayList<Int>()
    private var textIcons = ArrayList<String>()


    init {
        val rootLayout = activity.window.decorView.rootView
        val parent: ConstraintLayout = rootLayout.findViewById(parentLayout)
        view =
            LayoutInflater.from(activity).inflate(R.layout.fall_down_menu, parent, false)

        mainFallDown = view.findViewById(R.id.mainfalldown)

        boxText = arrayListOf(
            view.findViewById(R.id.tvM),
            view.findViewById(R.id.tvM1),
            view.findViewById(R.id.tvM2),
            view.findViewById(R.id.tvM3),
            view.findViewById(R.id.tvM4),
            view.findViewById(R.id.tvM5)

        )
        boxImage = arrayListOf(
            view.findViewById(R.id.cvM),
            view.findViewById(R.id.cvM1),
            view.findViewById(R.id.cvM2),
            view.findViewById(R.id.cvM3),
            view.findViewById(R.id.cvM4),
            view.findViewById(R.id.cvM5)

        )
        boxImageIn = arrayListOf(
            view.findViewById(R.id.ivM),
            view.findViewById(R.id.ivM1),
            view.findViewById(R.id.ivM2),
            view.findViewById(R.id.ivM3),
            view.findViewById(R.id.ivM4),
            view.findViewById(R.id.ivM5)

        )

        parent.apply { addView(view) }

        for (click in boxImage){
            click.setOnClickListener { clickOnIt() }
        }
    }

    fun setItems(
        backToolbar: Int? = null,
        icons: ArrayList<Int>,
        textIcons: ArrayList<String>,
        backColors: ArrayList<Int>,
        runningFunctions: ArrayList<(() -> Unit)?>
    ) {
        this.icons = icons
        this.backColors = backColors
        this.textIcons = textIcons
        this.runningFunctions = runningFunctions

        backToolbar?.let { mainFallDown.setCardBackgroundColor(it) }
        for ((dt, doIt) in boxImage.withIndex()) {
            doIt.setOnClickListener {
                runningFunctions[dt]?.invoke()
                clickOnIt()
            }
        }
        for ((i, box) in boxImageIn.withIndex()) {
            box.setImageResource(icons[i])
            box.setBackgroundColor(backColors[i])
            boxText[i].text = textIcons[i]
        }
    }

    private fun menuFallDown() {
        //Reset
        mainFallDown.visibility = VISIBLE
        mainFallDown.alpha = 1f
        //Move
        var startPoint = 0f
        val endPoint = 0f
        var durationChange: Long = 500
        for (i in 0 until boxImage.size) {
            ValueAnimator.ofFloat(startPoint, endPoint).apply {
                repeatMode = ValueAnimator.REVERSE
                repeatCount = 1
                interpolator = AccelerateDecelerateInterpolator()
                duration = durationChange
                addUpdateListener { animation ->
                    val progress = animation.animatedValue as Float
                    boxText[i].translationY = progress
                    boxImage[i].translationY = progress
                    //  boxImage[2].translationY=progress
                    doOnEnd {

                    }
                }
            }.start()
            startPoint += 200
            durationChange += 200
        }

    }

    private fun clickOnIt() {
        var progress: Float
        // Go click
        ValueAnimator.ofFloat(0f, 250f).apply {
            // repeatMode = REVERSE
            repeatCount = 0
            interpolator = AccelerateDecelerateInterpolator()
            duration = 500
            addUpdateListener { animation ->
                progress = animation.animatedValue as Float
                boxText[2].translationX = progress * 2
                boxImage[2].translationX = progress

            }
        }.start()
        // Collect
        ValueAnimator.ofFloat(10f, 80f).apply {
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 0
            interpolator = AccelerateDecelerateInterpolator()
            duration = 900

            addUpdateListener { animation ->
                progress = animation.animatedValue as Float

                boxImage[5].rotationX = progress
                boxImage[4].rotationX = progress
                boxImage[3].rotationX = progress
                boxImage[1].rotationX = progress
                boxImage[0].rotationX = progress
                boxText[5].rotation = -30f
                boxText[4].rotation = -30f
                boxText[3].rotation = -30f
                boxText[1].rotation = 30f
                boxText[0].rotation = 30f
                animation.doOnEnd {
                    var startPoint = 0f
                    var endPoint = 5f
                    var durationChange: Long = 600
                    val fas = 3
                    for (image in boxImage) {
                        if (image == boxImage[2]) {
                            startPoint += 400
                            endPoint += 10
                        } else {
                            ValueAnimator.ofFloat(startPoint, 300f + endPoint * fas).apply {
                                repeatMode = ValueAnimator.REVERSE
                                repeatCount = 0
                                interpolator = AccelerateDecelerateInterpolator()
                                duration = durationChange
                                addUpdateListener { animation ->
                                    progress = animation.animatedValue as Float
                                    image.translationY = progress
                                    doOnEnd {
                                        if (image == boxImage[5]) {
                                            for (j in 0 until boxImage.size) {
                                                if (j != 2)
                                                    ValueAnimator.ofFloat(0f, 260f).apply {
                                                        repeatMode = ValueAnimator.REVERSE
                                                        repeatCount = 0
                                                        interpolator = LinearInterpolator()
                                                        duration = 1000


                                                        addUpdateListener { animation ->
                                                            progress =
                                                                animation.animatedValue as Float
                                                            boxText[j].translationX = progress * 2
                                                            boxImage[j].translationX = progress
                                                            mainFallDown.alpha =
                                                                (260 - progress) / 260
                                                            if (mainFallDown.alpha == 0f) {
                                                                for (i in 0 until boxImage.size) {
                                                                    boxImage[i].animate()
                                                                        .translationX(0f)
                                                                        .translationY(0f)
                                                                        .rotationX(0f)
                                                                    boxText[i].animate()
                                                                        .translationX(0f)
                                                                        .translationY(0f)
                                                                        .rotation(0f)

                                                                }
                                                            }

                                                            animation.doOnEnd {
                                                                mainFallDown.visibility = GONE

                                                            }
                                                        }
                                                    }.start()

                                            }
                                        }
                                    }
                                }
                            }.start()
                            startPoint += 200
                            endPoint += 10
                            durationChange += 100
                        }


                    }
                }
            }
        }.start()

    }

    fun show() {
        menuFallDown()
    }
}