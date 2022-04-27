package com.src.tools.advmenus

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.ShapeAppearanceModel

class CubeMenu @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    parentLayout: Int
) : View(context, attrs, defStyleAttr) {

    private var activity: Activity = context as Activity
    private var view: View
    private var icons = ArrayList<Int>()
    private var slideModView = ArrayList<View>()
    private var boxImage = ArrayList<ImageView>()
    private var bottomCard: MaterialCardView
    private var topCard: MaterialCardView
    private var mainSlide: LinearLayout
    private var runningFunctions = ArrayList<(() -> Unit)?>()

    // private var backToolbar: Int = 0
    private var fromOneColor: Int = 0
    private var toOneColor: Int = 0


    init {
        val rootLayout = activity.window.decorView.rootView
        val parent: ConstraintLayout = rootLayout.findViewById(parentLayout)
        view =
            LayoutInflater.from(activity).inflate(R.layout.cube_menu, parent, false)

        bottomCard = view.findViewById(R.id.bottomCard)
        topCard = view.findViewById(R.id.topCard)
        mainSlide = view.findViewById(R.id.mainSlide)
        slideModView = arrayListOf(
            topCard,
            view.findViewById(R.id.ivSL1),
            view.findViewById(R.id.ivSL2),
            view.findViewById(R.id.ivSL3),
            view.findViewById(R.id.ivSL4),
            view.findViewById(R.id.ivReturn),
            bottomCard
        )
        boxImage = arrayListOf(
            view.findViewById(R.id.ivSL),
            slideModView[1] as ImageView,
            slideModView[2] as ImageView,
            slideModView[3] as ImageView,
            slideModView[4] as ImageView,

            )
        for (a in slideModView) {
            if (a is ImageView && a.id != R.id.ivReturn)
                a.setBackgroundColor(Color.parseColor("#220055"))
            a.setOnClickListener {
                colorChanging(
                    a,
                    Color.parseColor("#E91E63"),
                    Color.parseColor("#220055")
                )
            }
        }
        fromOneColor = Color.parseColor("#E91E63")
         toOneColor = Color.parseColor("#220055")
            parent.apply { addView(view) }
    }

    fun setItems(
        backToolbar: Int,
        icons: ArrayList<Int>,
        fromOneColor: Int,
        toOneColor: Int,
        runningFunctions: ArrayList<(() -> Unit)?>
    ) {
        this.icons = icons
        this.fromOneColor = fromOneColor
        this.toOneColor = toOneColor
        this.runningFunctions = runningFunctions

        for (note in slideModView) {

            if (note is ImageView && note.id != R.id.ivReturn) {

                note.setBackgroundColor(backToolbar)

            }
            if (note is MaterialCardView && note.id != R.id.ivReturn)
                note.setCardBackgroundColor(backToolbar)
            if (note.id != R.id.ivReturn)
                colorChanging(
                    note,
                    fromOneColor,
                    toOneColor
                )
        }
        for ((dt, doIt) in slideModView.withIndex()) {

            doIt.setOnClickListener {
                runningFunctions[dt]?.invoke()
                if (doIt.id != R.id.ivReturn)
                    colorChanging(
                        doIt,
                        fromOneColor,
                        toOneColor
                    )
            }
        }
        for ((i, box) in boxImage.withIndex())
            box.setImageResource(icons[i])
    }

    private fun menuCube() {

        cardRoundAgain()

        slideModView[5].setOnClickListener {
            menuCubeBack()
        }
        var isClick = true
        mainSlide.visibility = VISIBLE
        (slideModView[2] as ImageView).setImageResource(android.R.drawable.ic_dialog_dialer)



        ValueAnimator.ofFloat(90f, 0f).apply {
            repeatCount = 0
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000 / 2
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float

                slideModView[0].y = slideModView[2].y - 130
                slideModView[0].rotationX = 70f
                slideModView[0].translationX = (progress * -2.5).toFloat()

                slideModView[1].y = slideModView[2].y - 130
                slideModView[1].rotationX = 70f
                slideModView[1].translationX = (progress * -2.5).toFloat()

                //Key!
                slideModView[2].rotationY = progress
                slideModView[2].translationX = (progress * -1.5).toFloat() - 40

                slideModView[3].y = slideModView[2].y
                slideModView[3].rotationY = 70f
                slideModView[3].translationX = (progress * -2.5).toFloat() + 90

                slideModView[4].y = slideModView[2].y + 130
                slideModView[4].rotationX = -70f
                slideModView[4].translationX = (progress * -2.5).toFloat()

                slideModView[5].y = slideModView[2].y + 130
                slideModView[5].rotationX = -70f
                slideModView[5].translationX = (progress * -2.5).toFloat()

                slideModView[6].y = slideModView[2].y + 130
                slideModView[6].rotationX = -70f
                slideModView[6].translationX = (progress * -2.5).toFloat()
                animation.doOnEnd {

                    // Chick to Open
                    slideModView[2].setOnClickListener {
                        if (icons.size > 0)
                        boxImage[2].setImageResource(icons[2])
                        colorChanging(
                            slideModView[2],
                            fromOneColor,
                            toOneColor
                        )


                        if (isClick) {
                            ValueAnimator.ofFloat(70f, 0f).apply {
                                repeatCount = 0
                                interpolator = AccelerateDecelerateInterpolator()
                                duration = 500
                                addUpdateListener { animation ->
                                    val progressTo = animation.animatedValue as Float
                                    slideModView[0].y = slideModView[1].y - 150

                                    slideModView[1].rotationX = progressTo
                                    slideModView[1].translationY = progressTo
                                    // slideModView[1].visibility=View.INVISIBLE

                                    slideModView[3].rotationY = progressTo * -1
                                    slideModView[3].translationX = progressTo
                                    // slideModView[3].visibility=View.INVISIBLE
                                    slideModView[2].translationX = progressTo * -1 * 5 / 6
                                    slideModView[4].rotationX = progressTo * -1
                                    slideModView[4].translationY = progressTo * -1 - 210
                                    // slideModView[4].visibility=View.INVISIBLE
                                    slideModView[5].y = slideModView[4].y + 215
                                    slideModView[6].y = slideModView[4].y + 180
                                    animation.doOnEnd {
                                        ValueAnimator.ofFloat(70f, 0f).apply {
                                            // startDelay=300
                                            repeatCount = 0
                                            interpolator = AccelerateDecelerateInterpolator()
                                            duration = 500
                                            addUpdateListener { animation ->
                                                val progressGo = animation.animatedValue as Float


                                                slideModView[0].rotationX = progressGo
                                                slideModView[0].translationY = progressGo

                                                slideModView[5].rotationX = progressGo
                                                slideModView[6].rotationX = progressGo

                                                slideModView[3].translationY = progressGo * -1
                                                slideModView[4].translationY = progressGo * -1
                                                slideModView[5].translationY = progressGo * -1
                                                slideModView[6].translationY = progressGo * -1
                                                doOnEnd {
//                                                slideModView[6].setOnClickListener {
//                                                    menuCubeBack()
//
//                                                }
                                                }
                                            }
                                        }.start()

                                    }

                                }
                            }.start()
                            isClick = false
                        } else {
                            if (runningFunctions.size >0)
                            runningFunctions[2]?.invoke()
                        }
                    }
                }
            }
        }.start()

    }

    private fun cardRoundAgain() {
        val shapeAppearanceModel: ShapeAppearanceModel.Builder = ShapeAppearanceModel().toBuilder()
        shapeAppearanceModel.setBottomRightCorner(
            CornerFamily.ROUNDED,
            CornerSize { return@CornerSize 90F })

        bottomCard.shapeAppearanceModel = shapeAppearanceModel.build()
        val shapeAM: ShapeAppearanceModel.Builder = ShapeAppearanceModel().toBuilder()
        shapeAM.setTopRightCorner(
            CornerFamily.ROUNDED,
            CornerSize { return@CornerSize 90F })

        topCard.shapeAppearanceModel = shapeAM.build()
    }

    private fun menuCubeBack() {
        ValueAnimator.ofFloat(0f, 70f).apply {
            // startDelay=300
            repeatCount = 0
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000
            addUpdateListener { animation ->
                val progressGo = animation.animatedValue as Float


                slideModView[0].rotationX = progressGo
                slideModView[0].translationY = progressGo + 215

                slideModView[5].rotationX = progressGo
                slideModView[6].rotationX = progressGo
                slideModView[1].translationY = progressGo + 200
                // slideModView[2].translationY = progressGo * -1-200
                slideModView[3].translationY = progressGo * -3
                slideModView[4].translationY = progressGo * -6

                // slideModView[5].translationY = progressGo * -1-450
                slideModView[6].translationY = progressGo * -6
                //  slideModView[6].rotationX = progressGo+20

                doOnEnd {


                    ValueAnimator.ofFloat(0f, -250f).apply {
                        // startDelay=300
                        repeatCount = 0
                        interpolator = AccelerateDecelerateInterpolator()
                        duration = 1000
                        addUpdateListener { animation ->
                            val progressQo = animation.animatedValue as Float
                            for (a in slideModView) {
                                //   a.rotationY = progressQo
                                slideModView[0].translationX = progressQo - 20
                                a.translationX = progressQo
                            }
                            doOnEnd {
                                for (a in slideModView)
                                    a.animate().rotationY(0f).rotationX(0f).translationY(0f)
                                        .translationX(0f)
                                mainSlide.visibility = GONE

                            }
                        }
                    }.start()


                }
            }
        }.start()
    }

    // Change Colors
    private fun colorChanging(frameLayout: View, background_from: Int, background_to: Int) {
        var objectAnimator = ObjectAnimator.ofObject(
            frameLayout,
            "backgroundColor",
            ArgbEvaluator(),
            background_from,
            background_to
        )
        if (frameLayout is MaterialCardView)
            objectAnimator = ObjectAnimator.ofObject(
                (frameLayout as MaterialCardView),
                "cardBackgroundColor",
                ArgbEvaluator(),
                background_from,
                background_to
            )

        objectAnimator.duration = 800
        objectAnimator.start()

    }

    fun show() {
        menuCube()
    }
}