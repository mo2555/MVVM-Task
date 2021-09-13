package com.example.mvvmtask.API

import com.example.mvvmtask.Data.ProductsData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterfaces {

    @GET("products/")
    fun getData(): Call<ProductsData>
}