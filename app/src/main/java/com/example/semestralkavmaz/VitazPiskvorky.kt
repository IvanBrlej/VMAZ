package com.example.semestralkavmaz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class VitazPiskvorky : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitaz_piskvorky)

    }

    /* Button next presmeruje hracov na activitu kde si zvolia mena a nasledne sa spusti dalsia hra
     */
    fun btnnext(view: View) {
        val intent = Intent(this, Mena::class.java)
        startActivity(intent);
    }

    /* Button domov presmeruje hracov na domovsku obrazovku aplikacie
     */
    fun domovP(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent);
    }
}