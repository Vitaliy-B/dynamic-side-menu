package bv.dev.dynamicsidemenu.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bv.dev.dynamicsidemenu.App
import bv.dev.dynamicsidemenu.LOG_TAG
import bv.dev.dynamicsidemenu.models.menu.Menu
import bv.dev.dynamicsidemenu.models.menu.MenuModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val mLiveData = MutableLiveData<List<Menu>?>()
    var index = 0

    init {
        Log.d(LOG_TAG, "${MainViewModel::class.java.simpleName} init ")

        App.getServerApi()?.menus?.enqueue(object : Callback<MenuModel?> {
            override fun onFailure(call: Call<MenuModel?>, t: Throwable) {
                Log.w(LOG_TAG, "Error when making network request: ${call.request()}", t)
                mLiveData.value = null
            }

            override fun onResponse(call: Call<MenuModel?>, response: Response<MenuModel?>) {
                val responseMenu = response.body()
                if ((!response.isSuccessful) || responseMenu == null) {
                    Log.w(LOG_TAG, "Error on response: $response / ${call.request()}")
                    return
                }

                val menuList = responseMenu.menuList
                Log.d(LOG_TAG, "Menu list downloaded: $menuList")
                mLiveData.value = menuList
            }
        })
    }

    fun getLiveData(): LiveData<List<Menu>?> = mLiveData
}
