package com.ps.loginregistration

import `in`.aabhasjindal.otptextview.OTPListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ps.loginregistration.api_call.APICli
import com.ps.loginregistration.api_call.APIInter
import com.ps.loginregistration.databinding.ActivityOtpBinding
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class OtpActivity : AppCompatActivity() {

    private var TAG = "OtpActivity"
    lateinit var binding: ActivityOtpBinding
    lateinit var mContext: Context
    lateinit var sessionManager: SessionSession
    var valOtp: String? = ""
    lateinit var apiService: APIInter
    lateinit var apiCli: APICli

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_otp)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        clickEvents()
    }

    private fun clickEvents() {
//        TODO("Not yet implemented")
        binding.editOTP.setOtpListener(object : OTPListener {
            override fun onInteractionListener() {
                // fired when user types something in the Otpbox
                valOtp = ""
            }

            override fun onOTPComplete(otp: String) {
                // fired when user has entered the OTP fully.
                valOtp = otp
                Toast.makeText(mContext, valOtp, Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnOTP.setOnClickListener {
            verifyOTP()
        }
    }

    private fun initValues() {
//        TODO("Not yet implemented")
        mContext = this;
        sessionManager = SessionSession()
        apiCli = APICli()
        apiService = apiCli.getClient()?.create(APIInter::class.java)!!
    }

    private fun verifyOTP() {
        sessionManager.LoadingSpinner(mContext)
        sessionManager.dialogProgress?.show()
        val params = HashMap<String, String>()
        params["user_otp_id"] = "1"
        params["otp_code"] = valOtp!!
        params["role_id"] = "1"
        params["device_id"] = "qwerty"
        val call: Call<ResponseBody?>? = apiService.verify_otp(getHeaderValues(), params)
        Log.d(TAG, "\t\tparams\t\t$params")
        call!!.enqueue(object : Callback<ResponseBody?>{
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                try { if (response.isSuccessful) {
                        sessionManager.dialogProgress?.dismiss()
                        val responseRecieved = response.body()!!.string()
                        Log.d(TAG, "\t\tresponseRecieved\t\t$responseRecieved")
                        val jsonObjectResult = JSONObject(responseRecieved)
                        if (jsonObjectResult.getBoolean("status")) {
                            Log.d(TAG, "\t\tresponseRecieved\t\tif")
                            startActivity(Intent(mContext, NavigationActivity::class.java))

                        } else {
                            sessionManager.dialogProgress?.dismiss()
                            Log.d(TAG, "\t\tresponseRecieved\t\telse") }
                    } else {
                    Log.d(TAG, "\t\tresponseRecieved\t\touter else")
                        sessionManager.dialogProgress?.dismiss()
                    }                } catch (e: Exception) {
                    sessionManager.dialogProgress?.dismiss()
                    Log.e(TAG, "Exception \t" + e.message)
                    e.printStackTrace()
                } }
            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Log.e(TAG, "ONFailure \t" + t.message)
                sessionManager.dialogProgress?.dismiss()
            }
        })
    }

    fun getHeaderValues(): String? {
        var lng = "en"

        return lng
    }

}