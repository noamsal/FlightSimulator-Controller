package com.example.flightsimulatorapp.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.RED
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.min

class Joystick @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius: Float = 100f  //current radius
    private var center: PointF = PointF()   //current center
    private val paint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
        color = RED
    }
    /**
     * func for update location.
     * implementation in MainActivity.
     * responsible for doing something with the data. in this app : move it via com.example.flightsimulatorapp.viewModel
     */
    //
    lateinit var sender: (Float, Float) -> Unit

    /**
     * this function calculate Joystick's size (only called once)
     * */
    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {

        center = PointF(width/2.0f, height/2.0f)
    }
    /**
     * this function draws the joystick
     * */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(center.x, center.y, radius, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return true
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchMove(event.x, event.y)
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)  //update myself
        }
        sender(event.x, event.y)    // update com.example.flightsimulatorapp.viewModel
        return true
    }


    /***
     * function for update MYSELF!
     */


    private fun touchMove(x: Float, y: Float){
        center.x = x
        center.y = y
// will render again the screen.
        invalidate()
    }

}