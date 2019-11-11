package com.tt.nicklas.vmau.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.tt.nicklas.vmau.R
import kotlinx.android.synthetic.main.activity_first_time.*

class FirstTimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time)
        val pref = SharedPreferences(this)
        if(pref.getValueBoolean("firstTime", true)){
            showFirstTimeDialog()
            pref.save("firstTime", false)
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        FirstTimeButton.setOnClickListener {
            val intent = Intent(this, HearingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showFirstTimeDialog(){
        val alert = AlertDialog.Builder(this)
            alert.setTitle("One time Dialog")
            alert.setMessage("Welcome to Audientes. \nFirst time using the app we will perform a hearing test.")
            alert.setPositiveButton("ok",{  dialogInterface: DialogInterface?, i: Int ->
            } )
        alert.show()
    }

}
