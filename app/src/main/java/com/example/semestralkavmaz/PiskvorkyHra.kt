package com.example.semestralkavmaz

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.collections.ArrayList

class PiskvorkyHra : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piskvorky_hra)

        /* volam funkciu aby mi vytvorila notifikaciu
        */
        vytvorNotifikaciuPiskvorky()

        /* Vypisujem hracom mena z activity Mena
        */
        val player1 = intent.getStringExtra("Name1")

        val menoHrac1P = findViewById<TextView>(R.id.menoHrac1P).apply {
            text = player1
        }

        val player2 = intent.getStringExtra("Name2")

        val menoHrac2P = findViewById<TextView>(R.id.menoHrac2P).apply {
            text = player2
        }
    }

    /* urcujem potrebne channel_id a notification_id pre notifikaciu
        */
    val CHANNEL_ID = "channel_id1"
    val notificationId = 1


    fun click(view: View) {

        /* Priradujem buttonom ich idcka pre vyberanie hracom
        */
        val b_vybrany = view as Button
        var click_b_id = 0
        when (b_vybrany.id) {
            R.id.BP1 -> click_b_id = 1
            R.id.BP2 -> click_b_id = 2
            R.id.BP3 -> click_b_id = 3
            R.id.BP4 -> click_b_id = 4
            R.id.BP5 -> click_b_id = 5
            R.id.BP6 -> click_b_id = 6
            R.id.BP7 -> click_b_id = 7
            R.id.BP8 -> click_b_id = 8
            R.id.BP9 -> click_b_id = 9
        }
        hraj(click_b_id, b_vybrany)
    }

    /* vytvorim arraylisty pre hracov do ktorych budem nasledne vkladat tlacidla na ktore kliknu
     */
    var aktivnyHrac = 1;
    var hrac1 = ArrayList<Int>()
    var hrac2 = ArrayList<Int>()

    /* Funkcia urci ktory hrac je na tahu a po jeho kliknuti mu priradi dane tlacidlo a zmeni mu farbu
     */
    fun hraj(click_b_id: Int, b_vybrany: Button) {
        if (aktivnyHrac == 1) {
            b_vybrany.text = "X"
            b_vybrany.setBackgroundResource(R.color.red)
            hrac1.add(click_b_id)
            aktivnyHrac = 2
        } else {
            b_vybrany.text = "O"
            b_vybrany.setBackgroundResource(R.color.yellow)
            hrac2.add(click_b_id)
            aktivnyHrac = 1
        }
        b_vybrany.isEnabled = false
        vitazstvo()
    }

    /* Funkcia, ktora porzie obsahy arrayov hracov a urci vitaza popripade remizu
    */
    fun vitazstvo() {
        var vitaz = -1
        /* Kontrolujem ci neobsahuje prvy alebo druhy hrac vsetky buttony v prvom riadku
        * ak ano vyhral
        */
        if (hrac1.contains(1) && hrac1.contains(2) && hrac1.contains(3)) {
            vitaz = 1
        }
        if (hrac2.contains(1) && hrac2.contains(2) && hrac2.contains(3)) {
            vitaz = 2
        }
        /* Kontrolujem ci neobsahuje prvy alebo druhy hrac vsetky buttony v druhom riadku
        * ak ano vyhral
        */
        if (hrac1.contains(4) && hrac1.contains(5) && hrac1.contains(6)) {
            vitaz = 1
        }
        if (hrac2.contains(4) && hrac2.contains(5) && hrac2.contains(6)) {
            vitaz = 2
        }
        /* Kontrolujem ci neobsahuje prvy alebo druhy hrac vsetky buttony v tretiom riadku
        * ak ano vyhral
        */
        if (hrac1.contains(7) && hrac1.contains(8) && hrac1.contains(9)) {
            vitaz = 1
        }
        if (hrac2.contains(7) && hrac2.contains(8) && hrac2.contains(9)) {
            vitaz = 2
        }
        /* Kontrolujem ci neobsahuje prvy alebo druhy hrac vsetky buttony v prvom stlpci
        * ak ano vyhral
        */
        if (hrac1.contains(1) && hrac1.contains(4) && hrac1.contains(7)) {
            vitaz = 1
        }
        if (hrac2.contains(1) && hrac2.contains(4) && hrac2.contains(7)) {
            vitaz = 2
        }
        /* Kontrolujem ci neobsahuje prvy alebo druhy hrac vsetky buttony v druhom stlpci
        * ak ano vyhral
        */
        if (hrac1.contains(2) && hrac1.contains(5) && hrac1.contains(8)) {
            vitaz = 1
        }
        if (hrac2.contains(2) && hrac2.contains(5) && hrac2.contains(8)) {
            vitaz = 2
        }
        /* Kontrolujem ci neobsahuje prvy alebo druhy hrac vsetky buttony v tretom stlpci
        * ak ano vyhral
        */
        if (hrac1.contains(3) && hrac1.contains(6) && hrac1.contains(9)) {
            vitaz = 1
        }
        if (hrac2.contains(3) && hrac2.contains(6) && hrac2.contains(9)) {
            vitaz = 2
        }
        /* Kontrolujem ci neobsahuje prvy alebo druhy hrac vsetky buttony nakriz hracieho pola
        * ak ano vyhral
        */
        if (hrac1.contains(1) && hrac1.contains(5) && hrac1.contains(9)) {
            vitaz = 1
        }
        if (hrac2.contains(1) && hrac2.contains(5) && hrac2.contains(9)) {
            vitaz = 2
        }
        /* Kontrolujem ci neobsahuje prvy alebo druhy hrac vsetky buttony nakriz hracieho pola
        * ak ano vyhral
        */
        if (hrac1.contains(3) && hrac1.contains(5) && hrac1.contains(7)) {
            vitaz = 1
        }
        if (hrac2.contains(3) && hrac2.contains(5) && hrac2.contains(7)) {
            vitaz = 2
        }
        /* REMIZA
        * urcim ze je remiza ak su vsetky buttony pouzite a ani jeden hrac nema 3 buttony vela seba
        */
        if (hrac1.size >= 5 && vitaz != 1 && vitaz != 2) {
            val intent = android.content.Intent(this, VitazPiskvorky::class.java)
            startActivity(intent);
            posliNotifikaciuPiskvorky()
            Toast.makeText(this, "DRAW!", Toast.LENGTH_LONG).show()
        }
        /* Hrac1 vyhral hru presmeruje to hracov na vitaznu activitu
        */
        if (vitaz == 1) {
            val intent = android.content.Intent(this, VitazPiskvorky::class.java)
            startActivity(intent);
            Toast.makeText(this, "Player1 won the game", Toast.LENGTH_LONG).show()
        }
        /* Hrac2 vyhral hru presmeruje to hracov na vitaznu activitu
        */
        else if (vitaz == 2) {
            Toast.makeText(this, "Player2 won the game", Toast.LENGTH_LONG).show()
            val intent = android.content.Intent(this, VitazPiskvorky::class.java)
            startActivity(intent);
        }
    }

    /* vytvorim notifikaciu s telom textu
     */
    fun vytvorNotifikaciuPiskvorky() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "DRAW!"
            val descriptionText = "DRAW in TicTacToe. Click for next game."
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

    /* pokial hrac klikne na notifikaciu presmeruje ho to na volby mien a nasledne na novu hru
     */
    fun posliNotifikaciuPiskvorky() {
        val intent: Intent = Intent(this, Mena::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        /* musim obrazok decodovat do bitmapy pretoze notifikacie nevedia robit s drawable
        */
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.gameover)

        /* builder vytvori notifikaciu s textom pozadim a nadpisom
        */
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("DRAW!")
            .setLargeIcon(bitmap)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText("DRAW in TicTacToe. Click for next game.")
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }
}



