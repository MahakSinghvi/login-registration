package com.ps.loginregistration

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.ps.loginregistration.api_call.APICli
import com.ps.loginregistration.api_call.APIInter

public abstract class LRBaseActivity : AppCompatActivity() {
    lateinit var mContext: Context
    lateinit var sessionSession: SessionSession
    lateinit var apiService: APIInter
    lateinit var shakeAnimation: Animation
    lateinit var bounceAnimation: Animation
    lateinit var apiCli1: APICli

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mContext = this
        sessionSession = SessionSession()
        apiCli1 = APICli()
        apiService = apiCli1.getClient()?.create(APIInter::class.java)!!
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake)
        bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
    }

    //todo calling animation when entering form activity
    public open fun enterActivityAnimation() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }
    //todo calling animation when exit form activity
    open fun exitActivityAnimation() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }


}