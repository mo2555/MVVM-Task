package com.example.mvvmtask.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmtask.API.ApiHelper
import com.example.mvvmtask.Data.ProductsData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MyViewModel : ViewModel(){
    var error_message: MutableLiveData<String> = MutableLiveData()
    var productDataList: MutableLiveData<ArrayList<ProductsData>> = MutableLiveData()

    fun getProduct():LiveData<ArrayList<ProductsData>>{
        return productDataList
    }

    fun getProducts() {
        ApiHelper.getMyApis("https://api.punkapi.com/v2/")
            .getData()
            .enqueue(object : Callback<ArrayList<ProductsData>> {
                override fun onResponse(
                    call: Call<ArrayList<ProductsData>>,
                    response: Response<ArrayList<ProductsData>>
                ) {
                    when (response.code()) {
                        200 -> {
                            var data: ArrayList<ProductsData> = response.body()!!
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

                override fun onFailure(call: Call<ArrayList<ProductsData>>, t: Throwable) {
                    error_message.postValue(t.message)
                }
            })
    }

}