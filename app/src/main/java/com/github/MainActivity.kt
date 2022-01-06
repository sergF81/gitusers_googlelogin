package com.github

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList
import android.widget.AdapterView

import android.widget.AdapterView.OnItemClickListener
import androidx.core.view.isVisible
import java.net.URI
import java.net.URL


class MainActivity : AppCompatActivity() {
    //создаем массив, в котором будут храниться данные
    var userArray: ArrayList<String> = arrayListOf()
    var userIdArray: ArrayList<String> = arrayListOf()
    var userAvatarArray: ArrayList<String> = arrayListOf()
    var totalCount: Int = 0
    var id: String = ""
    var login: String = ""
    lateinit var avatar: String


    var pageNumber: Int = 1
    var totalPageCount: Int = 0

    var userSearch: String = ""

    // создаем список для отображения данных из массива userArray
    var listUserView: ListView? = null

    //переменная, в которой будет хранится данные о логине пользователя

    val baseUrl = "https://api.github.com/"
    lateinit var buttonSearch: Button
    lateinit var editSearch: TextView
    lateinit var buttonNext: Button
    lateinit var buttonPreview: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listUserView = findViewById(R.id.listUserView)
        buttonSearch = findViewById(R.id.buttonSearch)
        editSearch = findViewById(R.id.editSearch)
        buttonNext = findViewById(R.id.buttonNext)
        buttonPreview = findViewById(R.id.buttonPreview)

        //обработка нажатия на выбранный элемент ListView
        listUserView?.setOnItemClickListener { parent, view, position, id ->
            //создание интента для новой активности
            val intent = Intent(this@MainActivity, InfoActivity::class.java)
            //передача данных в другую активность
            intent.putExtra("userSearch", userSearch)
            intent.putExtra("login", userArray[id.toInt()])
            intent.putExtra("id", userIdArray[id.toInt()])
            intent.putExtra("avatar", userAvatarArray[id.toInt()])

            //запуск новой активности
            startActivity(intent)
        }

    }
    //создаем функцию для подключения к сайту github.com

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun userRetrofit() {
        var client: InterfaceAPI = retrofit.create(InterfaceAPI::class.java)
        val call: Call<Users<Item>> = client.getLoginUser(userSearch, pageNumber)
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
                for (i in 0 until (ugit?.items?.size!!)) {
                    userArray.add(ugit.items[i].loginUser)
                    userIdArray.add(ugit.items[i].id)
                    userAvatarArray.add(ugit.items[i].avatarUrl)

                }
                if (ugit?.items?.size!! < 30) {
                    buttonNext.setVisibility(View.GONE)
                }

                addLogInArray()

            }

            override fun onFailure(call: Call<Users<Item>>, t: Throwable) {
                println(t)
            }

        }

        )
    }


    //объявляем функцию для создания и отображения списка логинов с данными из массива userArray
    fun addLogInArray() {
        // создаем адаптер списка логинов с данными массива userArray
        val adapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, userArray
        ) {

        }
        //присваеваем элементам списка выше созданый адаптер
        listUserView?.adapter = adapter

    }

    //объявляем функцию обработки нажатия на кнопку buttonSearch - поиск для введеного логина на сервере github
    fun onClickSearch(view: View) {
        userSearch = editSearch.text.toString()
        userArray.clear()
        userIdArray.clear()
        userAvatarArray.clear()
        buttonNext.isVisible = true
        println(pageNumber)

        //вызываем функцию для работы с github через Retofit
        userRetrofit()
    }

    fun onClickNext(view: View) {

        userSearch = editSearch.text.toString()
        if (pageNumber > 0) buttonPreview.setVisibility(View.VISIBLE)
        pageNumber++
        userArray.clear()
        userIdArray.clear()
        userAvatarArray.clear()
        userRetrofit()
        println(pageNumber)

    }


    fun onClickPreview(view: View) {
        userSearch = editSearch.text.toString()
        if (pageNumber > 1) {
            pageNumber--
            buttonNext.setVisibility(View.VISIBLE)
        }

        if (pageNumber == 1) buttonPreview.setVisibility(View.GONE)
        userArray.clear()
        userIdArray.clear()
        userAvatarArray.clear()
        userRetrofit()
        println(pageNumber)
    }

}






