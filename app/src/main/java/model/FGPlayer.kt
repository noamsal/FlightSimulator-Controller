package model

import java.io.IOException
import java.net.Socket
import java.net.SocketException
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class FGPlayer {

    // properties
    //val IPaddress: String
    //val port: Int
    var connected: Boolean = false
    private lateinit var client: Socket

    var dispatchQueue : BlockingQueue<Runnable> = LinkedBlockingQueue<Runnable>()


    constructor(){
        // implement active object
        Thread(Runnable()  {
            run() {
                while (true){
                    try{
                        dispatchQueue.take().run();
                    }
                    catch (e : InterruptedException) {}
                }
            }
        }).start();
    }

    /**
     * Establishes connection in TCP with given port and ip
     * Returns True is connection was established, false otherwise
     */
    fun establishConnection(portNumber: String , IPAddress: String){
        println("In the connection going to queue")
        dispatchQueue.put(Runnable(){
            run(){
                try {
                    client = Socket(IPAddress, portNumber.toInt())
                }
                catch(e: Exception){
                    println(e)

                }
            }
        } );
    }

    /**
     * Send the new value to fg
     */
    fun setAileron(newValue: Double){
        var message = "set /controls/flight/aileron "
        message += newValue.toString()
        message += "\r\n"

        sendData(message)

        //set /controls/flight/aileron (-1…1)
    }

    /**
     * Send the new value to fg
     */
    fun setElevator(newValue: Double){
        var message = "set /controls/flight/elevator "
        message += newValue.toString()
        message += "\r\n"

        sendData(message)

        //set /controls/flight/elevator (-1…1)
    }

    /**
     * Send the new value to fg
     */
    fun setRudder(newValue: Double){
        var message = "set /controls/flight/rudder "
        message += newValue.toString()
        message += "\r\n"

        sendData(message)

        //set /controls/flight/rudder (-1…1)
    }

    /**
     * Send the new value to fg
     */
    fun setThrottle(newValue: Double){
        var message = "set /controls/flight/current-engine/throttle "
        message += newValue.toString()
        message += "\r\n"

       sendData(message)

        //set /controls/flight/current-engine/throttle (0…1)
    }

    /**
     * Sends the data over socket and returns true if it went through
     */
    @Throws(InterruptedException::class)
    private fun sendData(data: String){
        dispatchQueue.put(Runnable(){
            run(){
                try {
                    println("going to send: $data")
                    client.getOutputStream().write(data.toByteArray())
                    client.getOutputStream().flush()
                }
                catch(e: IOException){
                    println(e)

                }
            }
        } );
    }

}