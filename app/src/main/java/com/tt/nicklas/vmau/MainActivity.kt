package com.tt.nicklas.vmau

import android.app.Activity
import android.app.DownloadManager
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    var mBluetoothAdapter: BluetoothAdapter? = null
    lateinit var mPairedDevice: Set<BluetoothDevice>
    val REQUEST_ENABLE_BLUETOOTH = 1

    companion object{
        val EXTRA_ADDRESS: String = "Device_address"
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                replaceFrag(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                replaceFrag(Dashboard_fragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                replaceFrag(About_fragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = 0
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (mBluetoothAdapter == null){
            toast("Bluetooth not available on this device")
        }
        if(!mBluetoothAdapter!!.isEnabled){
            val enableBTIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBTIntent, REQUEST_ENABLE_BLUETOOTH)
        }


        imgTopLeft.setOnClickListener { mBluetoothAdapter!!.enable() }

    }

    private fun replaceFrag(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if(requestCode == REQUEST_ENABLE_BLUETOOTH){
                if(resultCode == Activity.RESULT_OK){
                    if(mBluetoothAdapter!!.isEnabled){
                        toast("bluetooth activated")
                    }else{
                        toast("bluetooth deativated")
                    }

                }
            }else if(resultCode == Activity.RESULT_CANCELED){
                toast("bluetooth was canceld")
            }
    }


}
