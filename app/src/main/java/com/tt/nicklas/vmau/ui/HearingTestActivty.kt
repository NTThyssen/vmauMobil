package com.tt.nicklas.vmau.ui

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.tt.nicklas.vmau.R
import kotlinx.android.synthetic.main.activity_hearing_test_activty.*

import java.io.IOException
import java.util.*


class HearingTestActivty: AppCompatActivity() {

    companion object{
        var mUUID: UUID = UUID.fromString("00001108-0000-1000-8000-00805f9b34fb")

        var mBluetootSocket: BluetoothSocket? = null
        lateinit var mBlueToothAdapter: BluetoothAdapter
        var isConnected: Boolean = false
        lateinit var mAdress: String
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hearing_test_activty)
        mAdress = intent.getStringExtra((HearingActivity.EXTRA_ADDRESS))

        ConnectToDevice(this).execute()



        btnSoundHeard.setOnClickListener {

            //sendCmd(input = )
            /*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()*/
        }
    }

    private fun sendCmd(input: String){
        if(mBluetootSocket != null){
            try{
                mBluetootSocket!!.outputStream.write(input.toByteArray())
            }catch (e :IOException){
                e.printStackTrace()
            }
        }

    }

    private class ConnectToDevice(c : Context) : AsyncTask<Void, Void, String>() {
        private var connectSucces : Boolean = true
        private val context : Context

        init {
            this.context = c
        }

        override fun doInBackground(vararg p0: Void?): String? {
            Log.i("connection", "-------")
            try{
                if(mBluetootSocket == null || !isConnected){

                    mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter()
                    val device: BluetoothDevice = mBlueToothAdapter.getRemoteDevice(mAdress)

                    Log.i("this shit uuid", device.getUuids()[0].toString())
                    mBluetootSocket = device.createInsecureRfcommSocketToServiceRecord(mUUID)
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery()

                    mBluetootSocket!!.connect()
                    Log.i("connection",  "------")
                }
            }catch (e: IOException){
                connectSucces = false
                Log.i("connection", "FAILED")
                e.printStackTrace()
            }
            return null
        }

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (!connectSucces){
                Log.i("connection", "connection failed")
            }else{
                isConnected = true
            }

        }

    }
}