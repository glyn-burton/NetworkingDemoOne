package com.example.networkingdemoone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.networkingdemoone.datasource.remote.httpurlconnection.HttpURLConnectionHelper
import com.example.networkingdemoone.model.User.UserResponse
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when(view.id){

            R.id.btnExecuteHttpURLConnCall -> executeHttpURLConnCall()

        }



    }

    fun executeHttpURLConnCall(){

        val randomUserURL = "https://randomuser.me/api/?results=10"
        val httpURLConnectionHelper = HttpURLConnectionHelper()
        var jsonString = ""
        Thread(
            Runnable
        {
            jsonString = httpURLConnectionHelper.getResponse(randomUserURL)
            Log.d("TAG",jsonString)

            if (jsonString.isNotBlank()){
                val userResponse = Gson().fromJson<UserResponse>(jsonString, UserResponse::class.java)
                Log.d("TAG","FIRST RESPONSES FIRST NAME = ${userResponse.results[0].name.first}")

            }
        }

        ).start()




    }
}
