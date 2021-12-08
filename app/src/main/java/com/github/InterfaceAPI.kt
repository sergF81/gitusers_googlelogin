package com.github

//import android.telecom.Call
//import retrofit2.Call
import retrofit2.Call
import retrofit2.http.GET

//Создаем интерфейс для Ретрофита
interface InterfaceAPI {

  //Вариант 1
   // @GET("users/SergF81")
 // @GET("search/users?q=sergF;page=4;per_page=30")
  @GET("search/users?q=sergf;page=2;per_page=1")

 // @GET("products/cats?category=5&sort=desc")
  fun getKursUSD(): Call<Items<KursValut>>
  // fun getKursUSD()(@Query("items") items: String): Call<KursValut?>?

 // fun  getKursUSD(): retrofit2.Call<KursValut>

//    @GET("convert?q=EUR_RUB&compact=ultra&apiKey=251cfe6d05e69f7f3a6e")
//    fun  getKursEUR(): retrofit2.Call<KursValut>




}