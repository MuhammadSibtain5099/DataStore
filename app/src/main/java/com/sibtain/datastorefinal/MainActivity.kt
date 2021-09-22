package com.sibtain.datastorefinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class MainActivity : AppCompatActivity() {
    lateinit var userDetails: UserDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userDetails = UserDetails(this)
        val btnSubmit = findViewById<Button>(R.id.btnShow)
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtAge = findViewById<TextView>(R.id.txtAge)
        btnSubmit.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                userDetails.storeDetails("Sibtain", 24)

            }
        }
        lifecycle.coroutineScope.launchWhenCreated {
            userDetails.getName().collect {
                txtName.text = it

        }
        lifecycle.coroutineScope.launchWhenCreated {
            userDetails.getAge().collect {
                binding.txtAge.text = it.toString().trim()
            }}}
        }


}