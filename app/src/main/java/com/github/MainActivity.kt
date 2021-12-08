package com.github

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.KursValut
//import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {


    // создаем список для отображения данных из массива logList
    private var listUserView: ListView? = null

    //создаем массив, в котором будут храниться данные
    var userArray: ArrayList<String>? = ArrayList()

    //создаем переменную, в которой будут храниться ссобщения о результатах обмена
    var infoMessage: String = ""


    //массив, в который будет заноситься информация по выполненым транзакциям
    var log = ArrayList<String>()

    //переменные, в которых будут хранится данные о екрсах валют
    var login: String = ""


    //переменная с адресом сайта, с которого берем данные по курсам валют
    //val baseUrl = "https://free.currconv.com/api/v7/"
    //Вариант 1
    //val baseUrl = "https://api.github.com/"
    val baseUrl = "https://api.github.com/"
    lateinit var textViewInfo: TextView
  //  lateinit var textViewInfoKurs: TextView
    lateinit var buttonOK: Button
    lateinit var editTextInsertValue: EditText
    lateinit var buttonLog: Button


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewInfo = findViewById<TextView>(R.id.textViewInfo)
        editTextInsertValue = findViewById<EditText>(R.id.editTextInsertValue)

        buttonOK = findViewById<Button>(R.id.buttonOK)

        editTextInsertValue.visibility = View.INVISIBLE
        editTextInsertValue.setText("")
        buttonOK?.visibility = View.INVISIBLE

        //создаем объект, который создает список с данными из массива logList
       // userArray()

        valueUSD()
       //  textViewInfoKurs.text = login
        //  textViewInfoKurs.text = " Курс валют: Доллар - $dollar, Евро - $euro."
    }


    //обработка нажатия кнопки ОК для подтверждения ввода количества валюты для обмена
    fun onClickOK(view: View) {
        //переенная для проверки путого значения ввода
        val proverkaVvoda = editTextInsertValue.text.toString()

        //проверка на пустое значение
        if (proverkaVvoda == "") {
            Toast.makeText(
                this@MainActivity,
                "Вы не ввели количество валюты для обмена!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            textViewInfo.text = "Выберите валюту, которую хотите получить"
            editTextInsertValue.visibility = View.INVISIBLE
            buttonOK.visibility = View.INVISIBLE
        }

    }

    //обработка нажатия кнопки HISTORY(Log) для передачи данным массива с данными о проделанных операциях обмена валюты в другую активность
    fun onClickLog(view: View) {
        //создание интената для новой активности
        val intent = Intent(this@MainActivity, LogActivity::class.java)
        //передача массива в другую активность
        intent.putStringArrayListExtra("log", log)
        //запукс активности
        startActivity(intent)
    }

    //создаем функцию для подключения к сайту с данными по курсам валют

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun valueUSD() {
        var client: InterfaceAPI = retrofit.create(InterfaceAPI::class.java)
        val call: Call<Items<KursValut>> = client.getKursUSD()
        call.enqueue(object : Callback<Items<KursValut>> {

            override fun onResponse(
                call: Call<Items<KursValut>>,
                response: Response<Items<KursValut>>
            ) {
                if (!response.isSuccessful) {
                    println("code: " + response.code())
                    return
                }
                val ugit: Items<KursValut>? = response.body()
                login = String.format(ugit!!.login_user)
                textViewInfo.text = "hjjj"
//
            }

//            override fun onFailure(call: Call<KursValut>, t: Throwable) {
//                println(t)
//            }

            override fun onFailure(call: Call<Items<KursValut>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        }

        )
    }

//
//    //объявляем функцию для создания и отображения списка с данными из массива LogList
//    fun addLogInArray() {
//
//        // создаем адаптер списка с данными массива logList
//        val adapter = object : ArrayAdapter<String>(
//            this,
//            android.R.layout.simple_list_item_1, userArray!!
//        ) {
//
//            // меняем размер текста в ListView
//            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//                val view = super.getView(position, convertView, parent)
//                if (view is TextView) {
//                    view.textSize = 18f
//                }
//                return view
//            }
//        }
//
//        //присваеваем элементам списка выше созданый адаптер
//        listUserView?.adapter = adapter
//    }
}




