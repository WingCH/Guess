package com.wing.guess

//新版, 之前叫import android.support.v7.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //R = com.wing.guess.R.xxx
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Secret Number: "+ secretNumber.secret)

    }

    fun check(view: View){
        val n = ed_number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG, "numvber: $n")

        val diff = secretNumber.validate(n)
        var messenge = getString(R.string.yes_you_got_it)


        when{
            diff < 0 -> messenge = getString(R.string.bigger)
            diff > 0 -> messenge = getString(R.string.smaller)
        }

//        Toast.makeText(this,messenge,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_titile))
            .setMessage(messenge)
            .setPositiveButton("ok",null)
            .show()


    }


}
