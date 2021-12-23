package com.github

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    //создаем массив, в котором будут храниться данные
    var userArray: ArrayList<String> = arrayListOf()

    // создаем список для отображения данных из массива userArray
    var listUserView: ListView? = null

    //переменная, в которой будет хранится данные о логине пользователя

    var login: String? = null
    val baseUrl = "https://api.github.com/"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listUserView = findViewById(R.id.listUserView)

        addLogInArray()
        userRetrofit()
       // println("проверка" + login)





    }
    //создаем функцию для подключения к сайту с данными по курсам валют

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

     fun userRetrofit() {

        var client: InterfaceAPI = retrofit.create(InterfaceAPI::class.java)
        val call: Call<Users<Item>> = client.getLoginUser()//this.login ="dddd"
            //   var a: ArrayList<String> = ArrayList()
        call.enqueue(object : Callback<Users<Item>> {

            override fun onResponse(
                call: Call<Users<Item>>,
                response: Response<Users<Item>>
            ) {
                if (!response.isSuccessful) {
                    println("code: " + response.code())
                    return
                }
                val ugit: Users<Item>? = response.body()
                if (ugit != null) {

userArray.add(ugit.items.toString())   }

                println("тут выходит нормальный массив" + userArray)
            }

            override fun onFailure(call: Call<Users<Item>>, t: Throwable) {
                println(t)
            }

        }
        )
         // это не срабатывает
         println(" тут выходит пустой массив" + userArray)
    }


    //объявляем функцию для создания и отображения списка с данными из массива userArray
    fun addLogInArray() {
        println("тут должен выходить заполенные масив, а выходит пустой" + userArray)
        // создаем адаптер списка с данными массива userArray
        val adapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, userArray

        ) {}
        //присваеваем элементам списка выше созданый адаптер

        listUserView?.adapter = adapter
    }

}





