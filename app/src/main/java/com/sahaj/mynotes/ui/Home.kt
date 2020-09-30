package com.sahaj.mynotes.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.sahaj.mynotes.R

class Home : AppCompatActivity() {

    private val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        window.setFlags(1024, 1024)

        init()

    }

    private fun init() {

        val navController = Navigation.findNavController(this, R.id.nav_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_fragment),
            null
        )
    }

}
