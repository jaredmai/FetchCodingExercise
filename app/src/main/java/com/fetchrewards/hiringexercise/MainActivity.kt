package com.fetchrewards.hiringexercise

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    // Create a new ListView
    lateinit var listviewDetails: ListView
    // Create a new ArrayList that holds the data from JSON
    var arraylistDetails:ArrayList<DataModel> = ArrayList()
    // Client grabbed from OkHttp dependency
    private val client = OkHttpClient()


    // When Activity is first launched
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listviewDetails = findViewById<ListView>(R.id.listView)
        run()
    }

    // URL is run
    private fun run() {
        val url = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
        // Build request
        val request = Request.Builder()
            .url(url)
            .build()

        // Response received
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //Call has failed
            }

            //Response was successful
            override fun onResponse(call: Call, response: Response) {
                //Convert the response to a string
                val strResponse = response.body!!.string()

                //Convert the string into a JSON Array
                val jsonArray = JSONArray(strResponse)
                val size:Int = jsonArray.length()
                arraylistDetails= ArrayList()

                // Iterate through all items in the array
                for (i in 0 until size) {
                    val jsonObject:JSONObject=jsonArray.getJSONObject(i)

                    // Check if name is "" or "null"
                    if (jsonObject.getString("name") != "" && jsonObject.getString("name") != "null") {
                        //Fill the Data Model with data from item
                        val model = DataModel()
                        model.id = jsonObject.getInt("id")
                        model.listId = jsonObject.getInt("listId")
                        model.name = jsonObject.getString("name")
                        arraylistDetails.add(model)
                    }
                }

                //Sort the objects first by listId, then by Name
                arraylistDetails.sortWith(compareBy<DataModel> {it.listId}.thenBy { it.name })


                // Update the UI
                runOnUiThread {
                    val objAdapter =
                        CustomAdapter(applicationContext,arraylistDetails)
                    listviewDetails.adapter=objAdapter
                }
            }
        })
    }
}