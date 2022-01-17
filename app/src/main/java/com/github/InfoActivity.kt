package com.github

import android.accounts.AccountManager.get
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get
import java.net.URI
import java.net.URL


class InfoActivity : AppCompatActivity() {

    var login: String = ""
    var id: String = ""
    var avatar: String = ""
    var userSearch: String = ""

    lateinit var textViewGeneral: TextView
    lateinit var textViewLogin: TextView
    lateinit var textViewId: TextView
    lateinit var textViewAvatar: TextView
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Github)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        textViewGeneral = findViewById(R.id.textViewGeneral)
        textViewLogin = findViewById(R.id.textViewLogin)
        textViewId = findViewById(R.id.textViewId)
        textViewAvatar = findViewById(R.id.textViewAvatar)
        imageView = findViewById(R.id.imageView)

        //получаем данные из главной активности и присваиваем их соответствующим переменным
        login = intent.getStringExtra("login").toString()
        id = intent.getStringExtra("id").toString()
        avatar = intent.getStringExtra("avatar").toString()
        userSearch = intent.getStringExtra("userSearch").toString()

        //присваеваем новые значения текстовым View
        textViewLogin.setText("Login user: $login")
        textViewId.setText("ID user: $id")

        //выводим изображение из ссылки в переменной avatar
        Picasso.with(this)
            .load(avatar)
            .into(imageView);
    }

    fun onClickReturn(view: View) {
        this.finish()
    }
}




