package com.tt.nicklas.vmau.ui


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.tt.nicklas.vmau.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : androidx.appcompat.app.AppCompatActivity() {

        private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFrag(HomeFragment())
                navigation.onCheckIsTextEditor()
                navigation.menu.findItem(R.id.navigation_home).isChecked = true
            }
            R.id.navigation_dashboard -> {
                replaceFrag(Dashboard_fragment())
                navigation.menu.findItem(R.id.navigation_dashboard).isChecked = true
            }
            R.id.navigation_notifications -> {
                replaceFrag(About_fragment())
                navigation.menu.findItem(R.id.navigation_notifications).isChecked = true
            }
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFrag(HomeFragment())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



    }

    private fun replaceFrag(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }




}
