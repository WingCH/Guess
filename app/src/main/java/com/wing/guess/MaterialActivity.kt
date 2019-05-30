package com.wing.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    private val REQUEST_RECORD: Int = 100
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        Log.d(TAG, "onCreate: ");

        Log.d(TAG, "Secret number is: ${secretNumber.secret}")

        fab.setOnClickListener { view ->
            replay()
        }
        count.setText(secretNumber.count.toString())

        val count= getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", -1)
        val nick = getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)

        Log.d(TAG, "data: $count/$nick");

        

    }

    private fun replay() {
        AlertDialog.Builder(this)
            .setTitle("Replay game?")
            .setMessage("Are you sure?")
            .setPositiveButton(getString(R.string.ok), { dialog, which ->
                secretNumber.reset()
                count.setText(secretNumber.count.toString())
                ed_number.setText("")
            })
            .setNeutralButton("Cancel", null)
            .show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ");
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ");
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ");
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ");
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
            .setPositiveButton(R.string.ok, {dialog, which ->
                if (diff==0){
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
//                    startActivity(intent)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            })
            .show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_RECORD){
            if (resultCode == Activity.RESULT_OK){
                val nickname = data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult: $nickname");
                replay()
            }
        }
    }


}
