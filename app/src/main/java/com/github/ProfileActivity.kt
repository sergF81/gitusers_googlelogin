package com.github

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.squareup.picasso.Picasso


class ProfileActivity : AppCompatActivity() {
    var gso: GoogleSignInOptions? = null
    var gsc: GoogleSignInClient? = null
    lateinit var textName: TextView
    lateinit var textEmail: TextView
    lateinit var buttonSignOut: Button
    lateinit var buttonStart: Button
    lateinit var imageViewPhoto: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Github)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        textName = findViewById(R.id.textName)
        textEmail = findViewById(R.id.textEmail)
        imageViewPhoto = findViewById(R.id.imageViewPhoto)
        buttonSignOut = findViewById(R.id.buttonSignOut)
        buttonStart = findViewById(R.id.buttonStart)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestProfile().requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso!!)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personPhoto = acct.photoUrl
            var personName = acct.displayName
            var personEmail = acct.email

            textName.setText(personName)
            textEmail.setText(personEmail)

            Picasso.with(this)
                .load(personPhoto)
                .into(imageViewPhoto)

            println(personName)
            println(personEmail)
            println(personPhoto)

        }
        buttonSignOut.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                signOut()
            }
        })
    }

    fun signOut() {
        gsc!!.signOut().addOnCompleteListener {
            finish()
            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
        }
    }

    fun onClickStart(view: View) {
        finish()
        val intent = Intent(this@ProfileActivity, ListActivity::class.java)
        startActivity(intent)
     //   startActivity(Intent(this@ProfileActivity, ListActivity::class.java))
    }
}