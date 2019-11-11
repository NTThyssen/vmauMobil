package com.tt.nicklas.vmau.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tt.nicklas.vmau.R
import kotlinx.android.synthetic.main.activity_hearing_test_result.*
import org.jetbrains.anko.toast

class HearingTestResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hearing_test_result)

        btnDetail.setOnClickListener { toast("Functionality coming soon") }
    }
}
