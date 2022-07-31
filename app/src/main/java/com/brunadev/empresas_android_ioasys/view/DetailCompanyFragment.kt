package com.brunadev.empresas_android_ioasys.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.brunadev.empresas_android_ioasys.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_company.*


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


