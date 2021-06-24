package viewModel


import model.FGPlayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val player: FGPlayer = FGPlayer()
    val ip: String = ""
    val port: String = ""
    var rudder: Double = 0.0
        private set(value) {
            field = value
            player.setRudder(value)
        }
    var throttle: Double = 0.5
        private set(value) {
            field = value
            player.setThrottle(value)
        }
    var aileron: Double = 0.0
        private set(value) {
            field = value
            player.setAileron(value)
        }
    var elevator: Double = 0.0
        private set(value) {
            field = value
            player.setElevator(value)
        }


    /* onConnect function
    * thx to data-binding that was used in MainActivity.xml,
    * this function will be revoked when USER pressed the 'connect' button*/
    fun onConnect(port: String, ip: String) {
        println("on connect!!!!!!!!!!!")
        player.establishConnection(port, ip)
    }

    fun onThrottlechange(progress: Int) {
        // 0< new val <1
        val new_value = progress.toDouble() / 100
        throttle = new_value
    }

    fun onRudderchange(progress: Int) {
        // -1<new_val <1
        val new_value = ((progress.toDouble() - 50) / 100) * 2
        rudder = new_value
    }
    fun onElevatorchange(progress: Int) {
        val new_value = ((progress.toDouble() - 50) / 100) * 2
        elevator = new_value
    }
    fun onAileronchange(progress: Int) {
        val new_value = ((progress.toDouble() - 50) / 100) * 2
        aileron = new_value
    }
}