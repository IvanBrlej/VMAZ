package com.example.semestralkavmaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /* button presmeruje hraca na activitu mena
    */
    fun piskvorky(view: View) {
        val intent = android.content.Intent(this, Mena::class.java)
        startActivity(intent);
    }

    /* button presmeruje hraca na activitu MenoHrac
    */
    fun kartovahra(view: View) {
        val intent = android.content.Intent(this, MenoHrac::class.java)
        startActivity(intent);
    }

    /* button presmeruje hraca na activitu Math
    */
    fun matematickahra(view: View) {
        val intent = android.content.Intent(this, Math::class.java)
        startActivity(intent);
    }
}