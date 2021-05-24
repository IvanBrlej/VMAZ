package com.example.semestralkavmaz


import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Database : AppCompatActivity() {
    lateinit var databaseNextB: Button
    lateinit var databasePreviousB: Button
    lateinit var databaseInsertB: Button
    lateinit var menoDatabET: EditText
    lateinit var krajinaDatabET: EditText
    lateinit var vypisMenaT: TextView
    lateinit var vypisKrajinyT: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        databaseNextB = findViewById(R.id.databNext)
        databasePreviousB = findViewById(R.id.databPrevious)
        databaseInsertB = findViewById(R.id.databInsert)
        menoDatabET = findViewById(R.id.menoDatab)
        krajinaDatabET = findViewById(R.id.countryDatab)
        vypisKrajinyT = findViewById(R.id.vypisKrajiny)
        vypisMenaT = findViewById(R.id.vypisMena)

        /* na vkladanie dat
        */
        var rs = contentResolver.query(
            Provider.CONTENT_URL, arrayOf(Provider._ID, Provider.NAME, Provider.COUNTRY),
            null, null, null
        )
        /* po klikani na tlacidlo previous vypisuje nasledujucich ludi v databaze
        */
        databaseNextB.setOnClickListener {
            if (rs?.moveToNext()!!) {
                vypisMenaT.setText(rs.getString(1))
                vypisKrajinyT.setText(rs.getString(2))
            }
        }

        /* po kliknuti na tlacidlo insert prida data do databazy
        */
        databaseInsertB.setOnClickListener {
            var cv = ContentValues()
            cv.put(Provider.NAME, menoDatabET.text.toString())
            cv.put(Provider.COUNTRY, krajinaDatabET.text.toString())
            contentResolver.insert(Provider.CONTENT_URL, cv)
            rs?.requery()
            databaseInsertB.setEnabled(false)
        }

        /* po klikani na tlacidlo previous vypisuje predchadzajucich ludi v databaze
        */
        databasePreviousB.setOnClickListener {
            if (rs?.moveToPrevious()!!) {
                vypisMenaT.setText(rs.getString(1))
                vypisKrajinyT.setText(rs.getString(2))
            }
        }
    }

    /* po kliknuti na tlacidlo hraca presmeruje na MainActivity
     */
    fun domov(view: View) {
        val intent = android.content.Intent(this, MainActivity::class.java)
        startActivity(intent);
    }
}