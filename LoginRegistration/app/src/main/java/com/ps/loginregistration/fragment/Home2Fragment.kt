package com.ps.loginregistration.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps.loginregistration.R

class Home2Fragment : Fragment() {
    private var TAG: String? = "Home2Fragment"
    var mContext: Context? = null
    var view1: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
         view1 = inflater?.inflate(
            R.layout.fragment_gallery,
            container, false
        )

        return view1
    }
}
