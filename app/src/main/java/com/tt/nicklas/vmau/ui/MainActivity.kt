package com.tt.nicklas.vmau.ui


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.tt.nicklas.vmau.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : androidx.appcompat.app.AppCompatActivity() {

        private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
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
