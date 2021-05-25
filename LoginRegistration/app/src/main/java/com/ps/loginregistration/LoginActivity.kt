package com.ps.loginregistration

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.DrawableCompat
import com.ps.loginregistration.api_call.APICli
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : LRBaseActivity() {
    private var TAG: String? = "LoginActivity"
    lateinit var number: EditText;
    lateinit var password: EditText;
    lateinit var tvReg: TextView;
    lateinit var login_button: Button;
    lateinit var sessionManager: SessionSession
    lateinit var apiCli: APICli

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initValues();
        setDrawable();
        clickEvents();
    }

    private fun clickEvents() {
        // TODO: 21/5/21  ("Using to click events")
        number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (s.length != 0) {
                    var drawable = resources.getDrawable(R.drawable.ic_mobile) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable,
                        resources.getColor(R.color.black)
                    ) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                    number.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    number.setCompoundDrawablesWithIntrinsicBounds(
                        resources.getDrawable(R.drawable.ic_mobile),
                        null,
                        resources.getDrawable(R.drawable.ic_cancel),
                        null
                    )
                } else if (s.length == 0) {
                    number.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_mobile,
                        0, 0, 0
                    )
                    var drawable = resources.getDrawable(R.drawable.ic_mobile) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable,
                        resources.getColor(R.color.purple_200)
                    ) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                    number.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    number.setCompoundDrawablesWithIntrinsicBounds(
                        resources.getDrawable(R.drawable.ic_mobile),
                        null, null, null
                    )
                }
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (s.length != 0) {
                    var drawable = resources.getDrawable(R.drawable.ic_password) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable,
                        resources.getColor(R.color.black)
                    ) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                    password.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    password.setCompoundDrawablesWithIntrinsicBounds(
                        resources.getDrawable(R.drawable.ic_password),
                        null,
                        resources.getDrawable(R.drawable.ic_password),
                        null
                    )
                } else if (s.length == 0) {
                    password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_mobile,
                        0, 0, 0
                    )
                    var drawable = resources.getDrawable(R.drawable.ic_password) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable,
                        resources.getColor(R.color.purple_200)
                    ) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                    password.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    password.setCompoundDrawablesWithIntrinsicBounds(
                        resources.getDrawable(R.drawable.ic_password),
                        null, null, null
                    )
                }
            }
        })


        login_button.setOnClickListener {
            if (number.text.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.enter) + " " + getString(R.string.mobile_number),
                    Toast.LENGTH_LONG
                ).show();
            }else  if (password.text.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.enter) + " " + getString(R.string.password),
                    Toast.LENGTH_LONG
                ).show();
            } else {
                /* val intent = Intent(this@LoginActivity, SplashScreen::class.java)
                 startActivity(intent)*/
               /* sendOtpAPI()*/
                startActivity(
                    Intent(this@LoginActivity, OtpActivity::class.java)
                )

                enterActivityAnimation()
            }
        }


        tvReg.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
            startActivity(intent)
            enterActivityAnimation()
        }

    }

    private fun setDrawable() {
        // TODO: 21/5/21  ("Using to set the drawable")
        number.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_mobile, 0, 0, 0)
        password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_password, 0, 0, 0)

    }

    private fun initValues() {
        // TODO: 21/5/21  ("Using to initialize the variables")
        number = findViewById(R.id.number);
        password = findViewById(R.id.password);
        login_button = findViewById(R.id.login_button);
        tvReg = findViewById(R.id.tvReg);
        sessionManager = SessionSession()
        apiCli = APICli()
//        apiService = apiCli.getClient()?.create(APIInter::class.java)!!
    }

    private fun sendOtpAPI() {
        sessionManager.LoadingSpinner(this)
        sessionManager.dialogProgress?.show()
        val params = HashMap<String, String>()
        params["user_mobile"] = number.text.toString().trim()
        params["language_id"] = "1"
        val call: Call<ResponseBody?>? = apiService.send_otp(params)
        Log.d(TAG, "\t\tparams\t\t$params")
        call!!.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                try {
                    if (response.isSuccessful) {
                        sessionManager.dialogProgress?.dismiss()
                        val responseRecieved = response.body()!!.string()
                        Log.d(TAG, "\t\tresponseRecieved\t\t$responseRecieved")
                        val jsonObjectResult = JSONObject(responseRecieved)
                        if (jsonObjectResult.getBoolean("status")) {
                            val jsonObject = jsonObjectResult.getJSONObject("data")
                            startActivity(Intent(this@LoginActivity, OtpActivity::class.java))
                            enterActivityAnimation()
                        } else {
                        }
                    }
                } catch (e: Exception) {
                    sessionManager.dialogProgress?.dismiss()
                    Log.e(TAG, "Exception \t" + e.message)
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Log.e(TAG, "ONFailure \t" + t.message)
                sessionManager.dialogProgress?.dismiss()
            }
        })
    }


}