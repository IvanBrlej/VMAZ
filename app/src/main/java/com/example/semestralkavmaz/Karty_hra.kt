package com.example.semestralkavmaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class Karty_hra : AppCompatActivity() {

    lateinit var obrazokKarta1I: ImageView
    lateinit var obrazokKarta2I: ImageView
    lateinit var karta3I: ImageView
    lateinit var obrazokKartaPC1I: ImageView
    lateinit var obrazokKartaPC2I: ImageView
    lateinit var bodyPC: TextView
    lateinit var bodyHraca: TextView
    lateinit var hrajKartyB: Button
    lateinit var hitKartuB: Button
    lateinit var button3: Button

    /* Vytvaram array kariet pomocou drawable
    */
    val karty: IntArray = intArrayOf(
        R.drawable.srdcovadvoja,
        R.drawable.srdcovatrojka,
        R.drawable.srdcovastvorka,
        R.drawable.srdcovapatka,
        R.drawable.srdcovasestka,
        R.drawable.srdcovasedmicka,
        R.drawable.srdcovaosmicka,
        R.drawable.srdcovadeviatka,
        R.drawable.srdcovadesiatka,
        R.drawable.srdcovyjack,
        R.drawable.srdcovakralovna,
        R.drawable.srdcovykral,
        R.drawable.srdcoveeso
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_karty_hra)

        /* zada pouzivatelovy meno z activity MenoHrac
        */
        val player = intent.getStringExtra("Name")
        val menoHraca = findViewById<TextView>(R.id.menoHracaKarty).apply {
            text = player
        }

        obrazokKarta1I = findViewById(R.id.karta1);
        obrazokKarta2I = findViewById(R.id.karta2);
        obrazokKartaPC1I = findViewById(R.id.kartaPC);
        obrazokKartaPC2I = findViewById(R.id.kartaPC1);

        var clicked = false

        hrajKartyB = findViewById(R.id.hrajKartyB)
        val random = Random()

        /* po stlaceni tlacidla play da hracov aj pocitacu pomocou random 2 karty z arrayu
        * vypise ich skore
        * hrac nasledne moze kliknut na button hit ktora mu prida este jednu random kartu z arrayu
        * nasledne ked klikne na tlacidlo stand zavola funkciu vitazHit
        */
        hrajKartyB.setOnClickListener {
            val karta1 = random.nextInt(karty.size)
            setCardV(karta1, obrazokKarta1I)
            val karta2 = random.nextInt(karty.size)
            setCardV(karta2, obrazokKarta2I)
            val kartaPC = random.nextInt(karty.size)
            setCardV(kartaPC, obrazokKartaPC1I)
            val kartaPC1 = random.nextInt(karty.size)
            setCardV(kartaPC1, obrazokKartaPC2I)
            bodyPC = findViewById(R.id.bodyKartyPC)
            val bodyPC = ((kartaPC + kartaPC1) + 4)
            this.bodyPC.setText(" " + bodyPC)
            hrajKartyB.setEnabled(false)
            bodyHraca = findViewById(R.id.bodyHracaKarty)
            val body = ((karta1 + karta2) + 4)
            bodyHraca.setText(" " + body)
            karta3I = findViewById(R.id.karta3);
            hitKartuB = findViewById(R.id.hitKartuB)
            val random = Random()
            hitKartuB.setOnClickListener {
                val karta3 = random.nextInt(karty.size)
                setCardV(karta3, karta3I)
                hitKartuB.setEnabled(false)
                val body3karty = ((karta1 + karta2 + karta3) + 6)
                bodyHraca.setText(" " + body3karty)
                clicked = true
                vitazHit(body3karty, bodyPC)
            }
            if (clicked == false) {
                vitazHit(body, bodyPC)
            }
        }
    }

    /* funkcia sluzi na pridelenie hodnoty karty a obrazku
    */
    fun setCardV(cislo: Int, image: ImageView) {
        image.setImageResource(karty[cislo])
    }

    /* Funkcia na urcenie vitaza BlackJacku
      * spocita body hraca a pc
      * ak ma hrac viac bodov ako PC a menej ako 22 vyhral
      * PC vyhral pri rovnakom pocte bodov pri pocte ak maju obidvaja viac ako 21
      * a ak ma viac bodov ako hrac a menej ako 22
     */
    fun vitazHit(bodyHraca: Int, bodyPC: Int) {
        button3 = findViewById(R.id.standKartyB)
        var p = 22
        button3.setOnClickListener {
            if (bodyHraca > bodyPC) {
                if (bodyHraca < p) {
                    val intent = android.content.Intent(this, VitazKarty::class.java)
                    Toast.makeText(this, "Player won the game", Toast.LENGTH_LONG).show()
                    startActivity(intent);
                } else if (bodyHraca > p) {
                    val intent = android.content.Intent(this, VitazKarty::class.java)
                    Toast.makeText(this, "PC won the game", Toast.LENGTH_LONG).show()
                    startActivity(intent);
                }
            } else if (bodyHraca < bodyPC) {
                if (bodyPC < p) {
                    val intent = android.content.Intent(this, VitazKarty::class.java)
                    Toast.makeText(this, "PC won the game", Toast.LENGTH_LONG).show()
                    startActivity(intent);
                } else if (bodyPC > p) {
                    val intent = android.content.Intent(this, VitazKarty::class.java)
                    Toast.makeText(this, "Hrac won the game", Toast.LENGTH_LONG).show()
                    startActivity(intent);
                }
            } else if (bodyHraca == bodyPC) {
                val intent = android.content.Intent(this, VitazKarty::class.java)
                Toast.makeText(this, "PC won the game", Toast.LENGTH_LONG).show()
                startActivity(intent);
            } else {
                val intent = android.content.Intent(this, VitazKarty::class.java)
                Toast.makeText(this, "PC won the game", Toast.LENGTH_LONG).show()
                startActivity(intent);
            }
        }
    }
}


