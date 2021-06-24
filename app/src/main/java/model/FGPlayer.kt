package model

import java.io.IOException
import java.net.Socket

class FGPlayer {

    // properties
    //val IPaddress: String
    //val port: Int
    private lateinit var client: Socket

    constructor(portNumber: Int , IPAddress: String){
        val port = portNumber
        val IPaddress = IPAddress

        // establish connection
        try{
            client = Socket(IPaddress, port)
            println("Connection established")

        }
        catch(e: IOException)
        {
            println(e);
        }
    }

    fun setAileron(newValue: Double){
        var message = "set /controls/flight/aileron "
        message += newValue.toString()
        message += "\r\n"

        sendData(message)

        //set /controls/flight/aileron (-1…1)
    }

    fun setElevator(newValue: Double){
        var message = "set /controls/flight/elevator "
        message += newValue.toString()
        message += "\r\n"

        sendData(message)

        //set /controls/flight/elevator (-1…1)
    }

    fun setRudder(newValue: Double){
        var message = "set /controls/flight/rudder "
        message += newValue.toString()
        message += "\r\n"

        sendData(message)

        //set /controls/flight/rudder (-1…1)
    }

    fun setThrottle(newValue: Double){
        var message = "set /controls/flight/current-engine/throttle "
        message += newValue.toString()
        message += "\r\n"

       sendData(message)

        //set /controls/flight/current-engine/throttle (0…1)
    }

    // Send the data to fg
    fun sendData(data: String){
        try {
            println("going to send: " + data)
            client.getOutputStream().write(data.toByteArray())
            client.getOutputStream().flush()
        }
        catch(e: IOException){
            println(e)
        }
    }

}