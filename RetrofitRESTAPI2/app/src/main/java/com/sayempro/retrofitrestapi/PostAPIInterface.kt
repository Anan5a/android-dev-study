package com.sayempro.retrofitrestapi

import retrofit2.Call
import retrofit2.http.GET

interface PostAPIInterface {
    @GET("/posts")
    fun getAllPosts(): Call<List<Post>>
}
