package com.tt.nicklas.vmau.ui


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tt.nicklas.vmau.R
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)


        view.imgTopFrag.setOnClickListener {
            val intent = Intent(activity, HearingActivity::class.java)
            startActivity(intent)
        }

        view.imgBotFrag.setOnClickListener { val intent = Intent(activity, HearingTestResult::class.java)
            startActivity(intent)
        }

        return view
    }


}
