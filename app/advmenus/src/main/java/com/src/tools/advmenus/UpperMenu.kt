package com.src.tools.advmenus

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout


class UpperMenu @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    parentLayout: Int,
    seeToolbar: Boolean? = false
)
    : Toolbar(context, attrs, defStyleAttr) {

    private var activity: Activity = context as Activity
    private var view: View
    private var icons = ArrayList<Int>()
    private var fromColors = ArrayList<Int>()
    private var toColors = ArrayList<Int>()
    private var toText = ArrayList<String>()
    private var boxCard = ArrayList<CardView>()
    private var imageBox = ArrayList<ImageView>()
    private var boxText = ArrayList<TextView>()
    private var mainUp: RelativeLayout
    private var runningFunctions = ArrayList<(() -> Unit)?>()
    private val toolSee: Toolbar
    private var backToolbar: Int = 0


    init {

        val rootLayout = activity.window.decorView.rootView
        val parent: ConstraintLayout = rootLayout.findViewById(parentLayout)
        view =
            LayoutInflater.from(activity).inflate(R.layout.upper_menu, parent, false)
        if (seeToolbar == true)
            addToolbar()
        boxCard = arrayListOf(
            view.findViewById(R.id.ivUp1),
            view.findViewById(R.id.ivUp2),
            view.findViewById(R.id.ivUp3),
            view.findViewById(R.id.ivUp4)
        )
        boxText = arrayListOf(
            view.findViewById(R.id.tvUp1),
            view.findViewById(R.id.tvUp2),
            view.findViewById(R.id.tvUp3),
            view.findViewById(R.id.tvUp4)
        )
        //val toobar = view.findViewById<ConstraintLayout>(R.id.cm)
        toolSee = view.findViewById(R.id.toolbar)
        mainUp = view.findViewById(R.id.mainUp)
        toolSee.setContentInsetsAbsolute(0, 0)

        parent.apply { addView(view) }
//        toolSee.updateLayoutParams<ConstraintLayout.LayoutParams> {
//           // endToStart = parentLayout
//            topToTop = parentLayout
//            bottomMargin = -250
//        }

    }


    fun setItems(
        backToolbar: Int,
        icons: ArrayList<Int>,
        fromColors: ArrayList<Int>,
        toColors: ArrayList<Int>,
        text: ArrayList<String>,
        runningFunctions: ArrayList<(() -> Unit)?>
    ) {
        this.icons = icons
        this.fromColors = fromColors
        this.toColors = toColors
        this.toText = text
        this.backToolbar = backToolbar
        this.runningFunctions = runningFunctions
        for ((ico, note) in imageBox.withIndex()) {
            note.setImageResource(icons[ico])
        }
        for ((txt, txtbox) in boxText.withIndex()) {
            //  val f=activity.resources.getDrawable( icons[txt],null)
            txtbox.setCompoundDrawablesWithIntrinsicBounds(
                AppCompatResources.getDrawable(activity, icons[txt]),
                null, null, null
            ) //
        }
        for ((dt, doIt) in boxCard.withIndex()) {
            doIt.setCardBackgroundColor(fromColors[dt])
            doIt.setOnClickListener {
                runningFunctions[dt]?.invoke()
                menuUpper(dt)
            }
        }
        mainUp.setBackgroundColor(backToolbar)

    }


    private fun menuUpper(id: Int) {
        var c = true
        if (c) {
            boxText[id].text = toText[id]
            boxCard[id].setCardBackgroundColor(toColors[id])
            colorChanging(mainUp, fromColors[id], toColors[id])
            colorChanging(toolSee, fromColors[id], toColors[id])
            c = false
        }
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            boxText[id].text = ""
            boxCard[id].setCardBackgroundColor(fromColors[id])
            colorChanging(mainUp, toColors[id], backToolbar)
            colorChanging(toolSee, toColors[id], backToolbar)
            c = true
        }, 1000)
    }

    // Change Colors
    private fun colorChanging(frameLayout: View, background_from: Int, background_to: Int) {
        val objectAnimator = ObjectAnimator.ofObject(
            frameLayout,
            "backgroundColor",
            ArgbEvaluator(),
            background_from,
            background_to
        )

        objectAnimator.duration = 800
        objectAnimator.start()
    }


    private fun addToolbar() {
        activity.setTheme(androidx.appcompat.R.style.Theme_AppCompat_DayNight_NoActionBar)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        // logoToolbar()
//       toolbar?.title = "Android"
//       toolbar?.subtitle = "Sub"
//       toolbar?.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_menu_black_24dp)
//       toolbar?.setNavigationOnClickListener { Toast.makeText(applicationContext,"Navigation icon was clicked",Toast.LENGTH_SHORT).show() }

    }

    fun logoToolbar(logo: Int, backColor: Int? = null, click: (() -> Unit)?) {
        // background , add logo, click
        // toolSee.setLogo(logo)//androidx.appcompat.R.drawable.abc_btn_check_material
        toolSee.setNavigationIcon(logo)
        if (backColor == null) toolSee.setBackgroundColor(backToolbar) else toolSee.setBackgroundColor(
            backColor
        )
        toolSee.setNavigationOnClickListener { click?.invoke() }

    }


}