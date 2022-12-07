package com.brunadev.empresas_android_ioasys.splash.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.brunadev.empresas_android_ioasys.R

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        Handler(Looper.myLooper()!!).postDelayed({
             findNavController().navigate(R.id.loginFragment)
        }, 1500)

        // set status bar color.
        activity?.window?.statusBarColor =
            ContextCompat.getColor(context as Context, R.color.pink)

        return view
    }
}