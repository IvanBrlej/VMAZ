package com.example.semestralkavmaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MenoHrac : AppCompatActivity() {

    lateinit var menoHraca: EditText
    lateinit var submitMeno: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meno_jeden_hrac)

        submitMeno = findViewById(R.id.submitMenoB)
        menoHraca = findViewById(R.id.menoHrac)

        /* tlacidlom sumbit potvrdim zadane meno, meno posle na aktivitu karty hra kde nasledne presmeruje aj hraca
        */
        submitMeno.setOnClickListener() {
            val intent = android.content.Intent(this, Karty_hra::class.java)
            val player = menoHraca.text.toString()
            intent.putExtra("Name", player)
            startActivity(intent);
        }
    }
}