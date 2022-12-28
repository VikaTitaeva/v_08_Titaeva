package com.example.titaeva_v_08

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class PersonalAreaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_area)
    }
    fun onClick(view: View) {
        val intent: Intent
        when (view.id) {
            R.id.mapBtn -> {
                intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse("geo:-0.45609946,-90.26607513")
                startActivity(intent)
            }
        }
    }
}