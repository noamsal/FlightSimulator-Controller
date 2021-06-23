package com.example.flightsimulatorapp.views
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.flightsimulatorapp.R

class MainActivity : AppCompatActivity() {
    /* fields */
    //lateinit var port : ?Int



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
    }
    // my addition
    //this function shall operate on a click - because SetOnClicked decided in 'onCreate'
//    private fun rollDice() {
//        //Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
//        val resultText: TextView = findViewById(R.id.result_text) // get reference to 'TextView' GUI
//        resultText.text = "Dice Rolled" //change the 'TextView' 's text field to a different string
//    }

    //this function shall operate on a click on 'connection' button - because SetOnClicked decided in 'onCreate'
    private fun submit(Connect_Button : View) {   // the param. is the connection button itself


        // Hide the keyboard (after submission)
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(Connect_Button.windowToken, 0)
    }


}