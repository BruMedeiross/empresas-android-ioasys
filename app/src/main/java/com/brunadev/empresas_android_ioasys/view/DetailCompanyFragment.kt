package com.brunadev.empresas_android_ioasys.view

/*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brunadev.empresas_android_ioasys.BlankFragment
import com.brunadev.empresas_android_ioasys.R

// TODO: Rename parameter arguments, choose names that match

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailCompanyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}*/

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import com.brunadev.empresas_android_ioasys.R
import com.brunadev.empresas_android_ioasys.mydata.model.Company
import com.google.android.material.internal.ToolbarUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.fragment_detail_company.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_company.*
import org.koin.android.ext.android.bind

class DetailCompanyFragment : Fragment(R.layout.fragment_detail_company) {

    companion object {
        const val COMPANY_KEY = "COMPANY"
        const val DESC_KEY = "DESC"
        const val TYPE_KEY = "TYPE"
        const val LOCAL_KEY = "LOCAL"
        const val IMG_KEY = "IMAGE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_company, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupToolbar()
    }

    private fun setupView() {
        name_detail.text = arguments?.getString(TYPE_KEY)!!
        desc_company.text = arguments?.getString(DESC_KEY)!!
        location_company.text = arguments?.getString(LOCAL_KEY)!!

        val img = arguments?.getString(IMG_KEY)!!
        Picasso.get().load(img).into(image_company)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(app_bar_deitail)
            supportActionBar?.setDisplayShowTitleEnabled(true)
            supportActionBar?.title = arguments?.getString(COMPANY_KEY)!!
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        }
    }

}


