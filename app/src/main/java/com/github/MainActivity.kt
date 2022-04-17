package com.github

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task
import org.jetbrains.annotations.Nullable
import android.R.attr.data
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.common.api.ApiException
import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import com.github.R


class MainActivity : AppCompatActivity() {
    lateinit var gso: GoogleSignInOptions
    lateinit var gsc: GoogleSignInClient
    lateinit var buttonSignIn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Github)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSignIn = findViewById(R.id.buttonSignIn)

        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestProfile().requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso)
        var acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {

        }

        buttonSignIn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                signIn()
            }
        })
    }

//    fun signIn() {
//        val intent = Intent(this, SecondActivity::class.java)
//        resultLauncher.launch(intent)
//    }

    fun signIn() {
        var signInIntent: Intent = gsc.getSignInIntent()
        startActivityForResult(signInIntent, 1000)

    }

//    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
//        if (result.resultCode === Activity.RESULT_OK) {
//            // There are no request codes
//            val data: Intent? = result.data
//            //if (requestCode === 1000) {
//            var task: Task<GoogleSignInAccount> =
//                GoogleSignIn.getSignedInAccountFromIntent(data)
//
//
//            try {
//                task.getResult(ApiException::class.java)
//                navigateToSecondActivity()
//            } catch (e: ApiException) {
//                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
//            }
//       // }
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === 1000) {
            var task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                task.getResult(ApiException::class.java)
                finish()
        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
        startActivity(intent)

//                navigateToSecondActivity()
            } catch (e: ApiException) {
                println(e)
               // Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun navigateToSecondActivity() {
//        finish()
//        val intent = Intent(this@MainActivity, SecondActivity::class.java)
//        startActivity(intent)

    }

}


//
//
//
//
//
//
//fun signIn() {
//    val intent = Intent(this, SecondActivity::class.java)
//    resultLauncher.launch(intent)
//}
//
//
//
//
//
//fun openSomeActivityForResult() {
//    val intent = Intent(this, SomeActivity::class.java)
//    startActivityForResult(intent, 123)
//}
//
//override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//    if (resultCode == AppCompatActivity.RESULT_OK && requestCode == 123) {
//        doSomeOperations()
//    }
//}
//
//
//
//
//fun openSomeActivityForResult() {
//    val intent = Intent(this, SomeActivity::class.java)
//    resultLauncher.launch(intent)
//}
//
//var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
//    if (result.resultCode == Activity.RESULT_OK) {
//        // There are no request codes
//        val data: Intent? = result.data
//        doSomeOperations()
//    }
//}
