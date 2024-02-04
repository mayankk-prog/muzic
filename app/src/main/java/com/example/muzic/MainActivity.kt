package com.example.muzic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import okhttp3.Call
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView= findViewById(R.id.recyclerView)

        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData= retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : retrofit2.Callback<MyData?> {
            override fun onResponse(
                call: retrofit2.Call<MyData?>,
                response: retrofit2.Response<MyData?>
            ) {
                val dataList= response.body()?.data!!
    //                val textView= findViewById<TextView>(R.id.helloText)
    //                textView.text= dataList.toString()

                myAdapter= MyAdapter(this@MainActivity, dataList)
                myRecyclerView.adapter= myAdapter
                myRecyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
                Log.d("TAG: onResponse", "onResponse" + response.body())
            }

            override fun onFailure(call: retrofit2.Call<MyData?>, t: Throwable) {
                Log.d("TAG: onFailure", "onFailure" + t.message)

            }
        })


    }
}
