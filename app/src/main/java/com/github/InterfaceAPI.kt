package com.github

//import android.telecom.Call
//import retrofit2.Call
import retrofit2.Call
import retrofit2.http.GET
import okhttp3.ResponseBody
import retrofit2.http.Query


//Создаем интерфейс для Ретрофита
interface InterfaceAPI {


  @GET("search/users?per_page=30")

  fun getLoginUser(
    @Query("q") userSearch: String, @Query("page") pageNumber: Int
  ): Call<Users<Item>>



  @GET("/friends")
  fun friends(@Query("group") group: String?): Call<ResponseBody?>?


//  @GET("products/list?sort=desc")
//  fun productList(@Query("category") categoryId: Int): Call<List<Product?>?>?

  //fun getLoginUser(): Call<Users<Item>>

  // fun getKursUSD()(@Query("items") items: String): Call<KursValut?>?

 // fun  getKursUSD(): retrofit2.Call<KursValut>

//    @GET("convert?q=EUR_RUB&compact=ultra&apiKey=251cfe6d05e69f7f3a6e")
//    fun  getKursEUR(): retrofit2.Call<KursValut>




}
