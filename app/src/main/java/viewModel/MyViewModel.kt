package viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val ip : Int = 0
    val port : Int = 0


    /* onConnect function
    * thx to data-binding that was used in MainActivity.xml,
    * this function will be revoked when USER pressed the 'connect' button*/
    fun onConnect() {

    }
}