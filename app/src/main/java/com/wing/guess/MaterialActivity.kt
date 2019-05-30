package com.wing.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        Log.d(TAG, "Secret number is: ${secretNumber.secret}")

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game?")
                .setMessage("Are you sure?")
                .setPositiveButton(getString(R.string.ok), {dialog, which ->
                    secretNumber.reset()
                    count.setText(secretNumber.count.toString())
                    ed_number.setText("")
                })
                .setNeutralButton("Cancel", null)
                .show()
        }
        count.setText(secretNumber.count.toString())
    }

    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG, "numvber: $n")

        val diff = secretNumber.validate(n)
        var messenge = getString(R.string.yes_you_got_it)


        when {
            diff < 0 -> messenge = getString(R.string.bigger)
            diff > 0 -> messenge = getString(R.string.smaller)
            diff == 0 && secretNumber.count < 3 -> messenge = getString(R.string.excellent) + secretNumber.secret.toString()
        }
        count.setText(secretNumber.count.toString())



//        Toast.makeText(this,messenge,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_titile))
            .setMessage(messenge)
            .setPositiveButton("ok", null)
            .show()
    }
}
