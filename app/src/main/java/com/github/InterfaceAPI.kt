package com.github

//import android.telecom.Call
//import retrofit2.Call
import retrofit2.Call
import retrofit2.http.GET
import okhttp3.ResponseBody
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Body

import retrofit2.http.POST


//Создаем интерфейс для Ретрофита
interface InterfaceAPI {

  @GET("search/users?per_page=30")
  fun getLoginUser(
    @Query("q") userSearch: String,
    @Query("page") pageNumber: Int,
    //@Header("Authorization") token: String
  ): Call<Users<Item>>

}
