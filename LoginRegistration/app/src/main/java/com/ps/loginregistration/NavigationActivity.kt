package com.ps.loginregistration

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ps.loginregistration.databinding.ActivityNavigationBinding
import com.ps.loginregistration.fragment.Home2Fragment
import com.ps.loginregistration.fragment.HomeFragment

class NavigationActivity : AppCompatActivity() {
    private var TAG: String? = "NavigationActivity"
    lateinit var binding: ActivityNavigationBinding
    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_navigation)
        loadFragment(HomeFragment())
        binding.imageView.setOnClickListener {
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
            loadFragment(Home2Fragment())
        }
    }

    fun loadFragment(fragment: Fragment?) {
        try {
            val transaction =
                supportFragmentManager.beginTransaction()
            transaction.replace(R.id.drawerFrame, fragment!!)
            transaction.addToBackStack(null)
            transaction.commit()
            //        binding.drawerView.closeDrawer(GravityCompat.START);
        } catch (e: Exception) {
            Log.d(TAG, "\tloadFragment\t$e")
        }
    }

}