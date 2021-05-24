package com.example.semestralkavmaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class VitazKarty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitaz_karty)
    }

    /* presmeruje hraca aby si na activitu MenoHrac aby som si mohol zadat meno a hrat novu hru
     */
    fun dalsiahra(view: View) {
        val intent = android.content.Intent(this, MenoHrac::class.java)
        startActivity(intent);
    }

    /* Button home presmeruje hraca na domovsku obrazovku aplikacie
     */
    fun domovK(view: View) {
        val intent = android.content.Intent(this, MainActivity::class.java)
        startActivity(intent);
    }
}