package com.ps.loginregistration

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ps.loginregistration.databinding.ActivityRegistrationBinding

class RegistrationActivity : LRBaseActivity() {
    private var TAG: String? = "RegistrationActivity"
    private lateinit var binding: ActivityRegistrationBinding;
    private lateinit var context: Context;
    private var email: String? = ""
    private var password: String? = ""
    private var address: String? = ""
    lateinit var sessionManager: SessionSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        clickEvent()
    }

    private fun initValues() {
        context = this;
        sessionManager = SessionSession()
    }

    private fun clickEvent() {

        binding.tvLogin.setOnClickListener {
            finish()
            exitActivityAnimation()
        }

        binding.regButton.setOnClickListener {
            sessionManager.abc = binding.name.text.toString().trim()
            email = binding.email.text.toString().trim()
            password = binding.password.text.toString().trim()
            address = binding.address.text.toString().trim()
            Log.d(TAG, "" + sessionManager.abc)
            Log.d(TAG, "email\t$email")
            Log.d(TAG, "password\t$password")
            Log.d(TAG, "address\t$address")

            Toast.makeText(context, sessionManager.getValues(), Toast.LENGTH_LONG).show()

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        exitActivityAnimation()
    }
}