package com.sayempro.retrofitrestapi

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sayempro.retrofitrestapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    val baseUrl = "https://jsonplaceholder.typicode.com/"
    lateinit var mainBinding: ActivityMainBinding
    var posts = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        setContentView(view)
        showPosts()
    }

    fun showPosts() {
        val retrofit =
            Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()
        val retrofitApi: PostAPIInterface = retrofit.create(PostAPIInterface::class.java)
        val call: Call<List<Post>> = retrofitApi.getAllPosts()
        call.enqueue(
            object : Callback<List<Post>> {
                override fun onResponse(
                    call: Call<List<Post>>,
                    response: Response<List<Post>>,
                ) {
                    Log.d("RETROFIT_API_RESPONSE", response.body().toString())
                    if (response.isSuccessful) {
                        posts = response.body() as ArrayList<Post>
                    }
                    val adapter = PostsAdapter(posts)
                    mainBinding.recyclerView.adapter = adapter
                    mainBinding.progressBar.visibility = View.INVISIBLE
                }

                override fun onFailure(
                    call: Call<List<Post>>,
                    t: Throwable,
                ) {
                    Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            },
        )
    }
}
