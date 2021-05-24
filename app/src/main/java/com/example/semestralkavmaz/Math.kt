package com.example.semestralkavmaz


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class Math : AppCompatActivity() {

    lateinit var submitMB: Button
    lateinit var nextMB: Button
    lateinit var prikladMathI: ImageView
    lateinit var skoreMathT: TextView
    lateinit var textTimer: TextView

    /* vytvorim arry prikladov z drawable
     */
    val priklady: IntArray = intArrayOf(
        R.drawable.plus1,
        R.drawable.plus2,
        R.drawable.plus3,
        R.drawable.minus1,
        R.drawable.minus2,
        R.drawable.minus3,
        R.drawable.nasobenie1,
        R.drawable.nasobenie2,
        R.drawable.nasobenie3,
        R.drawable.delenie1,
        R.drawable.delenie2,
        R.drawable.delenie3,
        R.drawable.odmocnina1,
        R.drawable.odmocnina2,
        R.drawable.odmocnina3,
        R.drawable.faktorial1,
        R.drawable.faktorial2,
        R.drawable.faktorial3,
        R.drawable.derivacia1,
        R.drawable.derivacia2,
        R.drawable.derivacia3,
        R.drawable.log1,
        R.drawable.log2,
        R.drawable.log3
    )

    /* vytvorim array vysledkov k prikladom
     */
    val vysledky: IntArray = intArrayOf(
        18880,
        22530,
        18595,
        49,
        58,
        88,
        24,
        49,
        69,
        5,
        9,
        7,
        8,
        9,
        11,
        24,
        120,
        720,
        0,
        0,
        0,
        6,
        4,
        4
    )

    var skore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_solo)

        vytvorNotifikaciuMath()

        prikladMathI = findViewById(R.id.prikladMathI)
        submitMB = findViewById(R.id.submitMathB)
        nextMB = findViewById(R.id.nextMathB)


        textTimer = findViewById(R.id.textTimer)

        /* nastavim countdowntimer na 60sekund bude sa znizovat o 1 sekundu cas sa bude vypisovat do textview
        * ak pocas tohto casu hrac odpovie spravne na 5 otazok vyhral ak vyprsi cas prehral
        */
        var beziCas = false
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                textTimer.text = " " + millisUntilFinished / 1000
                beziCas = true
            }

            override fun onFinish() {
                if (skore == 5 && beziCas == true) {
                    vyhralSi()
                } else {
                    textTimer.text = "END"
                    beziCas = false
                    koniecHry(beziCas)
                }
            }
        }.start()

        /* na zaciatku sa da next button ako false aby hrac musel odpovedat na danu plusovu otazku
         * zacina sa random otazkou z plusovych prikladov nasledne sa porovna vysledok a next button
         * sa moze pouzit
        */
        nextMB.isEnabled = false
        val plus = (0..2).random()
        setP(plus, prikladMathI)
        submitMB.setOnClickListener {
            val vysledok = findViewById<EditText>(R.id.mathVysledokET).text.toString().toInt()
            vysledok(vysledky.get(plus), vysledok)
            nextMB.isEnabled = true
        }

        /* next button nam da dalsi priklad z arrayu teraz uz random vsetkych ostatnych okrem plusovych
        * sumbit button zavola funkciu vysledok, a  pripadne aj funkciu vyhral si
        */
        nextMB.setOnClickListener {
            val priklad = (3..23).random()
            setP(priklad, prikladMathI)
            nextMB.isEnabled = false
            submitMB.setOnClickListener {
                val vysledok = findViewById<EditText>(R.id.mathVysledokET).text.toString().toInt()
                vysledok(vysledky.get(priklad), vysledok)
                nextMB.isEnabled = true
                skoreMathT = findViewById(R.id.skoreMathT)
                skoreMathT.setText("Score:" + " " + skore)
                vyhralSi()
            }
        }
    }

    /* priradim channel_id a notification_id pre notifikaciu
    */
    val CHANNEL_ID = "channel_id2"
    val notificationId = 2

    /* funkcia sluzi na pridelenie hodnoty prikladu a obrazku
    */
    fun setP(cislo: Int, image: ImageView) {
        image.setImageResource(priklady[cislo])
    }

    /* Urci ci zadana odpoved je spravna alebo nie, ci cislo ktore hrac zadal sedi vo vysledkoch
     * v arrayliste ak ano pripocita skore ak nie koniec hry
     */
    fun vysledok(cislo1: Int, cislo2: Int) {
        if (cislo1 == cislo2) {
            Toast.makeText(this, "Correct answer!", Toast.LENGTH_LONG).show()
            skore++
        } else {
            Toast.makeText(this, "Bad answer!", Toast.LENGTH_LONG).show()
            val intent = android.content.Intent(this, PrehraMath::class.java)
            startActivity(intent);
        }
    }

    /* funkcia ktora ukonci hru a presmeruje hraca na dalsiu activitu na zaklade konca casu
    * zavola notifikaciu
     */
    fun koniecHry(koniecCasu: Boolean) {
        if (koniecCasu == false) {
            Toast.makeText(this, "End of time", Toast.LENGTH_LONG).show()
            val intent = android.content.Intent(this, PrehraMath::class.java)
            startActivity(intent);
            posliNotifikaciuMath()
        }
    }

    /* vytvorim notifikaciu s telom textu
     */
    fun vytvorNotifikaciuMath() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "You Lost The Game"
            val descriptionText = "You failed to reach score 7 in time! Try Again."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            /* registrujem channel so systemom
            */
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /* builder vytvori notifikaciu s textom  a nadpisom
        */
    fun posliNotifikaciuMath() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("You Lost The Game")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(" You failed to reach score 7 in time! Try Again.")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    /* urci hraca ako vitaza ak dosiahne v casovom limite 5 bodov a presmeruje ho do databazy
     */
    fun vyhralSi() {
        if (skore == 5) {
            Toast.makeText(this, "You won the game", Toast.LENGTH_LONG).show()
            val intent = android.content.Intent(this, Database::class.java)
            startActivity(intent);
        }
    }
}