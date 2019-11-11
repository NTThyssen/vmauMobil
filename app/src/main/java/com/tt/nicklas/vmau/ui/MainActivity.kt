package com.tt.nicklas.vmau.ui

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v4.app.Fragment

import com.tt.nicklas.vmau.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : android.support.v7.app.AppCompatActivity() {

        private val mOnNavigationItemSelectedListener = android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                replaceFrag(HomeFragment())
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                replaceFrag(Dashboard_fragment())

            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                replaceFrag(About_fragment())
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = 0

        imgTop.setOnClickListener {
            val intent = Intent(this, HearingActivity::class.java)
            startActivity(intent)
        }

        imgBot.setOnClickListener { val intent = Intent(this, HearingTestResult::class.java)
            startActivity(intent)
        }

    }

    private fun replaceFrag(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }




}
