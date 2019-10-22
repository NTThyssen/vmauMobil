package com.tt.nicklas.vmau

import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.*
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_hearing_test_activty.*
import java.io.IOException



class HearingTestActivty: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hearing_test_activty)
        btnSoundHeard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}