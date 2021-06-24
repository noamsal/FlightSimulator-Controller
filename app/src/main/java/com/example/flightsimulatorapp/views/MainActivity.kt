package com.example.flightsimulatorapp.views
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.flightsimulatorapp.R
//import com.example.flightsimulatorapp.databinding.ActivityMainBinding
import viewModel.MyViewModel

class MainActivity : AppCompatActivity() {
    /* fields */
    private val viewmodel: MyViewModel = MyViewModel()
    //private lateinit var binding: ActivityMainBinding


    /* methods */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val rollButton: Button = findViewById(R.id.roll_button)

        //rollButton is of type 'Button'. now we're actually referring to a UI object!
        //now we're using rollButton to connect between him and the operating method for clicking him
        //p.s. - we're overriding this method (i guess)

        //rollButton.setOnClickListener { rollDice() }

        /*register 'Submit' func. as a Handler for our connection button*/
        findViewById<Button>(R.id.Connect_Button).setOnClickListener { submit(it) } // 'it' refers to the submit button

        // bind throttle seekbar
        val throttleBar : SeekBar = findViewById(R.id.throttle)
        throttleBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                viewmodel.onThrottlechange(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                return
            }
        })

        //bind rudder seekbar
        val rudderBar : SeekBar = findViewById(R.id.rudder)
        rudderBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                viewmodel.onRudderchange(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                return
            }
        })
    }

    /**
     * Click handler for the connect button
     */
    private fun submit(Connect_Button : View) {   // the param. is the connection button itself

        // Hide the keyboard (after submission)
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(Connect_Button.windowToken, 0)


        println("In the submit Method")

        // get the entered data

        val port = findViewById<EditText>(R.id.Port_number).text.toString()

        val ip: String = findViewById<EditText>(R.id.IP_number).text.toString()

        println("The port is: $port")
        println("The IP is: $ip")

        viewmodel.onConnect(port, ip)
        // TODO check if the data is not empty
    }



}