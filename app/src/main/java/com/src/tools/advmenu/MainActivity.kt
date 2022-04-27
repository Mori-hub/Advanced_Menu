package com.src.tools.advmenu

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.src.tools.advmenus.CubeMenu
import com.src.tools.advmenus.FallDownMenu
import com.src.tools.advmenus.UpperMenu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTheme(androidx.appcompat.R.style.Theme_AppCompat_DayNight_NoActionBar)
        setContentView(R.layout.activity_main)

        val fallDownMenu = FallDownMenu(this, null, 0, R.id.cubeIn)
        fallDownMenu.setItems(Color.parseColor("gray"),
            arrayListOf(
                android.R.drawable.ic_dialog_map,
                android.R.drawable.ic_delete,
                android.R.drawable.ic_dialog_dialer,
                android.R.drawable.btn_star_big_on,
                android.R.drawable.ic_lock_idle_lock,
                android.R.drawable.ic_media_play
            ), arrayListOf("Recording", "Delete", "Add", "Play", "Do", "See it"), arrayListOf(
                Color.parseColor("blue"),
                Color.parseColor("white"),
                Color.parseColor("#55D8C1"),
                Color.parseColor("#FF6FB5"),
                Color.parseColor("#5508C1"),
                Color.parseColor("#97D0AE")
            ), arrayListOf(
                { println("Hi there! Mail") },
                { Toast.makeText(applicationContext, "Hi Play", Toast.LENGTH_SHORT).show() },
                { println("Hi there! Info") },
                { println("Hi there! Ring") },
                { println("Hi there! Ring") },
                { println("Hi there! Star") }
            ))
        // fallDownMenu.show()


        val cubeMenu = CubeMenu(this, null, 0, R.id.cubeIn)
        cubeMenu.setItems(
            Color.parseColor("#10D8C1"), arrayListOf(
                android.R.drawable.ic_dialog_map,
                android.R.drawable.ic_delete,
                android.R.drawable.ic_dialog_email,
                android.R.drawable.btn_star_big_on,
                android.R.drawable.ic_media_play
            ),
            Color.parseColor("#F7C0AC"),
            Color.parseColor("#5B7DB1"),
            arrayListOf(
                { println("Hi there! Mail") },
                { Toast.makeText(applicationContext, "Hi Play", Toast.LENGTH_SHORT).show() },
                { println("Hi there! Info") },
                { println("Hi there! Ring") },
                { println("Hi there! Star") }
            )
        )

        // cubeMenu.show()


        val upper = UpperMenu(this, null, 0, R.id.mainParent, true)

        upper.setItems(
            Color.parseColor("#FCF69C"),
            arrayListOf(
                android.R.drawable.ic_btn_speak_now,
                android.R.drawable.ic_delete, android.R.drawable.ic_input_add,
                android.R.drawable.ic_media_play
            ),
            arrayListOf(
                Color.parseColor("white"),
                Color.parseColor("#55D8C1"),
                Color.parseColor("#FF6FB5"),
                Color.parseColor("#97D0AE")
            ),
            arrayListOf(
                Color.parseColor("gray"),
                Color.parseColor("#F55353"),
                Color.parseColor("#FEB139"),
                Color.parseColor("#F56D91")
            ),
            arrayListOf("Recording", "Delete", "Add", "Play"), arrayListOf(
                null,
                {
                    Toast.makeText(applicationContext, "Hi", Toast.LENGTH_SHORT).show()
                    println("Hi there!")
                },
                { fallDownMenu.show() },
                { cubeMenu.show() }
            )
        )
//        upper.logoToolbar(android.R.drawable.sym_contact_card,null) {
//            Toast.makeText(applicationContext, "Hi", Toast.LENGTH_SHORT).show()
//        }

    }
}