package bv.dev.dynamicsidemenu

import android.app.Application
import bv.dev.dynamicsidemenu.models.menu.ServerApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LOG_TAG = "log_t"

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(ServerApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        serverApi = retrofit.create<ServerApi>(
            ServerApi::class.java)
    }

    companion object {
        private var serverApi: ServerApi? = null
        fun getServerApi(): ServerApi? {
            return serverApi
        }
    }
}