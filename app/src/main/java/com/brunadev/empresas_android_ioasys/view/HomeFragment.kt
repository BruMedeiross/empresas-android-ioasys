package com.brunadev.empresas_android_ioasys.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunadev.empresas_android_ioasys.R
import com.brunadev.empresas_android_ioasys.databinding.FragmentHomeBinding
import com.brunadev.empresas_android_ioasys.mydata.model.Company
import com.brunadev.empresas_android_ioasys.presenter.HomePresenter
import com.xwray.groupie.GroupieAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.progressBar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private lateinit var presenter: HomePresenter
    private val adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presenter = HomePresenter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = arguments?.getString("email")
        val pass = arguments?.getString("password")

        setupToolbar()

        rvlist.layoutManager = LinearLayoutManager(requireContext())
        rvlist.adapter = adapter

        if (adapter.itemCount == 0) {
            presenter.findAllCompanies(email!!, pass!!)
        }

        adapter.setOnItemClickListener { item, view ->
            // transportar um dado de uma tela a outra
            val bundle = Bundle()
            val companyName = (item as CompanyItem).company.name
            val companyType = (item).company.type.enterpriseTypeName
            val companyDesc = (item).company.description
            val companyLocation = (item).company.country
            val companyImg = "https://empresas.ioasys.com.br${(item).company.photo}"

            bundle.putString(DetailCompanyFragment.COMPANY_KEY, companyName)
            bundle.putString(DetailCompanyFragment.DESC_KEY, companyDesc)
            bundle.putString(DetailCompanyFragment.TYPE_KEY, companyType)
            bundle.putString(DetailCompanyFragment.LOCAL_KEY, companyLocation)
            bundle.putString(DetailCompanyFragment.IMG_KEY, companyImg)

            findNavController().navigate(R.id.action_homeFragment_to_detailCompanyFragment, bundle)
        }
    }

    private fun setupToolbar() {

        btn_search.setOnClickListener {
            iv_logo_home.visibility = View.GONE
            edit_search.visibility = View.VISIBLE
            btn_close.visibility = View.GONE
            btn_search.visibility = View.VISIBLE
            val layoutParams = edit_search.layoutParams
            layoutParams.width = ActionBar.LayoutParams.MATCH_PARENT

            val search = edit_search.text.toString()

            if(search.isNotEmpty()) {
                showProgress()
                btn_close.visibility = View.VISIBLE
                btn_search.visibility = View.GONE
            }

            Handler(Looper.getMainLooper()).postDelayed({
                hideProgress()
                btn_close.visibility = View.GONE
                btn_search.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "Busca por $search: não localizado", Toast.LENGTH_SHORT).show()
            }, 2000)

        }

        btn_close.setOnClickListener {
            edit_search.text?.clear()
            btn_search.visibility = View.VISIBLE
            iv_logo_home.visibility = View.VISIBLE
            edit_search.visibility = View.GONE
            btn_close.visibility = View.GONE
            hideProgress()
        }
    }

    fun showCompanies(response: List<Company>) {
        val companies = response.map { CompanyItem(it) }
        adapter.addAll(companies)
        adapter.notifyDataSetChanged()
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }

}

