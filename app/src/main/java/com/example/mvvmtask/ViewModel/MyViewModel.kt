package com.example.mvvmtask.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmtask.API.ApiHelper
import com.example.mvvmtask.Data.Data
import com.example.mvvmtask.Data.ProductsData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MyViewModel : ViewModel(){
    var error_message: MutableLiveData<String> = MutableLiveData()
    var productDataList: MutableLiveData<ArrayList<Data>> = MutableLiveData()

    fun getProduct():LiveData<ArrayList<Data>>{
        return productDataList
    }

    fun getProducts() {
        ApiHelper.getMyApis("https://android-training.appssquare.com/api/")
            .getData()
            .enqueue(object : Callback<ProductsData> {
                override fun onResponse(
                    call: Call<ProductsData>,
                    response: Response<ProductsData>
                ) {
                    when (response.code()) {
                        200 -> {
                            var data: ArrayList<Data> = response.body()?.data!!
                            data = (data+data+data) as ArrayList<Data>
                            productDataList.postValue(data)
                        }

                        else -> {
                            val errorJsonString = response?.errorBody()?.string()
                            val json: JSONObject = JSONObject(errorJsonString)
                            val msg = json.getString("message")
                            error_message.postValue(msg)
                        }
                    }

                }

                override fun onFailure(call: Call<ProductsData>, t: Throwable) {
                    error_message.postValue(t.message)
                }
            })
    }

}