package com.example.semestralkavmaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Mena : AppCompatActivity() {
    lateinit var menoHrac1: EditText
    lateinit var menoHrac2: EditText
    lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meno)
        submit = findViewById(R.id.submitMena)
        menoHrac1 = findViewById(R.id.menoHrac1)
        menoHrac2 = findViewById(R.id.menoHrac2)

        /* tlacidlom sumbit potvrdim zadane mena, mena posle na aktivitu piskvorkyhra kde nasledne presmeruje aj hraca
        */
        submit.setOnClickListener {
            val intent = android.content.Intent(this, PiskvorkyHra::class.java)
            val player1 = menoHrac1.text.toString()
            intent.putExtra("Name1", player1)
            val player2 = menoHrac2.text.toString()
            intent.putExtra("Name2", player2)
            startActivity(intent);
        }
    }
}