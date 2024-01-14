package com.example.example_response

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.example_response.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface Api {
    @GET("long_response_api/{time}")
    suspend fun waitResponse(@Path("time") time: String): Response<String>
}

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS) // 읽기 Timeout 설정 (초 단위)
            .connectTimeout(60, TimeUnit.SECONDS) // 연결 Timeout 설정 (초 단위)
            .build()

        val api: Api = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(Api::class.java)

        binding.button.setOnClickListener {
            Log.d("Okane", "버튼이 눌림")
            GlobalScope.launch(Dispatchers.IO) {
                var t = binding.editText.text.toString()

                Log.d("Okane", t)
                val response = api.waitResponse(t)
//                if(response.isSuccessful) {
//                    Log.d("Okane", response.body()!!)
//                }
                Log.d("Okane", "여긴 response를 받아오고 수행됨")
            }
            Log.d("Okane", "여긴 reponse를 받기전에 수행됨")
        }
    }


}