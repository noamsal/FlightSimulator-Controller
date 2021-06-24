package model

import java.io.IOException
import java.net.Socket
import java.net.SocketException

class FGPlayer {

    // properties
    //val IPaddress: String
    //val port: Int
    var connected: Boolean = false
    private lateinit var client: Socket


    /*
    NO need for constructor
    constructor(portNumber: String , IPAddress: String){
        val port = portNumber.toInt()
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
    }*/

    /**
     * Establishes connection in TCP with given port and ip
     * Returns True is connection was established, false otherwise
     */
    fun establishConnection(portNumber: String , IPAddress: String): Boolean{
        println("in establish")

        try{
            client = Socket(IPAddress, portNumber.toInt())
            println("Connection established")
            connected = true
        }
        catch(e: SocketException)
        {
            println(e)
            return false
        }
        return true
    }

    /**
     * Send the new value to fg
     */
    fun setAileron(newValue: Double): Boolean{
        var message = "set /controls/flight/aileron "
        message += newValue.toString()
        message += "\r\n"

        return sendData(message)

        //set /controls/flight/aileron (-1…1)
    }

    /**
     * Send the new value to fg
     */
    fun setElevator(newValue: Double): Boolean{
        var message = "set /controls/flight/elevator "
        message += newValue.toString()
        message += "\r\n"

        return sendData(message)

        //set /controls/flight/elevator (-1…1)
    }

    /**
     * Send the new value to fg
     */
    fun setRudder(newValue: Double): Boolean{
        var message = "set /controls/flight/rudder "
        message += newValue.toString()
        message += "\r\n"

        return sendData(message)

        //set /controls/flight/rudder (-1…1)
    }

    /**
     * Send the new value to fg
     */
    fun setThrottle(newValue: Double): Boolean{
        var message = "set /controls/flight/current-engine/throttle "
        message += newValue.toString()
        message += "\r\n"

       return sendData(message)

        //set /controls/flight/current-engine/throttle (0…1)
    }

    /**
     * Sends the data over socket and returns true if it went through
     */
    private fun sendData(data: String): Boolean{
        try {
            println("going to send: " + data)
            client.getOutputStream().write(data.toByteArray())
            client.getOutputStream().flush()
        }
        catch(e: IOException){
            println(e)
            return false
        }
        return true
    }

}