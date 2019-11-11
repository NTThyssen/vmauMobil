package com.tt.nicklas.vmau.ui

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.tt.nicklas.vmau.R
import com.tt.nicklas.vmau.view_model.HearingViewModel
import kotlinx.android.synthetic.main.activity_hearing.*
import org.jetbrains.anko.toast
import java.nio.channels.InterruptedByTimeoutException
import kotlin.concurrent.fixedRateTimer

class HearingActivity : AppCompatActivity() {
    var mBluetoothAdapter: BluetoothAdapter? = null
    lateinit var viewModel: HearingViewModel
    lateinit var mPairedDevice: Set<BluetoothDevice>
    companion object{
        val EXTRA_ADDRESS = "Device address"
    }
    val REQUEST_ENABLE_BLUETOOTH = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hearing)

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if (mBluetoothAdapter == null) {
            toast("Bluetooth not available on this device")
        }
        btState()
        PairButton.setOnClickListener {
        if(btState()) {
            pairedDevices()

        }else {

            val enableBTIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBTIntent, REQUEST_ENABLE_BLUETOOTH)
            mBluetoothAdapter!!.enable()

        }


        }

    }
    private fun pairedDevices(){
        mPairedDevice = mBluetoothAdapter!!.bondedDevices
        val list : ArrayList<BluetoothDevice> = ArrayList()

        if(!mPairedDevice.isEmpty()){
            for (d: BluetoothDevice in mPairedDevice){
                list.add(d)

            }
        }else{
            toast("No paired device found")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        BTListView.adapter = adapter
        BTListView.onItemClickListener = AdapterView.OnItemClickListener{_, _ , i, _ ->
            val device : BluetoothDevice = list[i]
            val address : String = device.address

            val intent = Intent(this, HearingTestActivty::class.java)
            intent.putExtra(EXTRA_ADDRESS, address)
            startActivity(intent)
        }
    }

    private fun btState() : Boolean {
        when (mBluetoothAdapter!!.isEnabled) {
            true -> {
                return true
            }
            false -> {
                PairButton.text = "Enable Bluetooth"
                return false
            }
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            if (resultCode == Activity.RESULT_OK) {
                if (mBluetoothAdapter!!.isEnabled) {
                    PairButton.text = "Pair Device"
                    toast("bluetooth activated")
                } else {
                    toast("bluetooth deativated")

                }

            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            toast("bluetooth was canceld")
        }
    }
}
