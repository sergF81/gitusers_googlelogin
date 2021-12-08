package com.github

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//Класс для получения даных из JSON ответа

data class Items (
    @SerializedName("items")
    @Expose
    val items: String
        )

data class KursValut (


@SerializedName("login")
@Expose
    val login_user : String
//@SerializedName("EUR_RUB")
//    val eurrub : Double
)