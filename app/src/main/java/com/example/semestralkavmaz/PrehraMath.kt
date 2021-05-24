package com.example.semestralkavmaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PrehraMath : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prehra_math)
    }

    /* Button home presmeruje hracov na domovsku obrazovku applikacie
     */
    fun domovMath(view: View) {
        val intent = android.content.Intent(this, MainActivity::class.java)
        startActivity(intent);
    }

    /* Button nextGame presmeruje hraca na novu hru
     */
    fun nextGameMath(view: View) {
        val intent = android.content.Intent(this, Math::class.java)
        startActivity(intent);
    }
}