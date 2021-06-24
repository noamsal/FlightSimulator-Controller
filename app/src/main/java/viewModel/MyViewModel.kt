package viewModel


import model.FGPlayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val player: FGPlayer = FGPlayer()
    val ip: String = ""
    val port: String = ""


    /* onConnect function
    * thx to data-binding that was used in MainActivity.xml,
    * this function will be revoked when USER pressed the 'connect' button*/
    fun onConnect(port: String, ip: String) {
        println("on connect!!!!!!!!!!!")
        if (!player.establishConnection(port, ip)) {
            println("Couldn't connect")
        }
    }

}