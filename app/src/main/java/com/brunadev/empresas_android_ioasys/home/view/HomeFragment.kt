package com.brunadev.empresas_android_ioasys.home.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunadev.empresas_android_ioasys.R
import com.brunadev.empresas_android_ioasys.databinding.FragmentHomeBinding
import com.brunadev.empresas_android_ioasys.common.model.Company
import com.brunadev.empresas_android_ioasys.home.presenter.HomePresenter
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var uid: String

    private lateinit var presenter: HomePresenter
    private val adapter = GroupieAdapter()

    private lateinit var client: String
    private lateinit var accessToken: String
    private var newText: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        // Set app status bar color.
        activity?.window?.statusBarColor =
            ContextCompat.getColor(context as Context, R.color.pink)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()

        uid = arguments?.getString("uid").toString()
        client = arguments?.getString("client").toString()
        accessToken = arguments?.getString("accessToken").toString()


        rvlist.layoutManager = LinearLayoutManager(requireContext())
        rvlist.adapter = adapter

        if (adapter.itemCount == 0) {
            presenter.findCompanies(uid, client, accessToken, newText = null)
        }

        adapter.setOnItemClickListener { item, _ ->
            setDetails(item)
        }
    }

    private fun setDetails(item: Item<GroupieViewHolder>) {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = (menu.findItem(R.id.menu_search).actionView as SearchView)

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        adapter.clear()
                        presenter.searchCompanies(uid, client, accessToken, newText)
                        hideKeyboard(searchView)
                        return true
                    } ?: run {
                        adapter.clear()
                        presenter.findCompanies(uid, client, accessToken, newText = null)
                        hideKeyboard(searchView)
                        return true
                    }
                    return false
                }
            })
        }
    }

    private fun hideKeyboard(searchView: SearchView) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(searchView.windowToken, 0)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(app_bar_main)
            title = ""
        }
    }

    fun showCompanies(response: List<Company>) {
        val companies = response.map { CompanyItem(it) }
        txt_not_found.visibility = View.GONE
        adapter.clear()
        adapter.addAll(companies)
        adapter.notifyDataSetChanged()
    }

    fun showFailure(message: String) {
        txt_not_found.text = message
        txt_not_found.visibility = View.VISIBLE
        rvlist.visibility = View.GONE
    }

    fun showProgress() {
        progressBarMain.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBarMain.visibility = View.GONE
        searchProgressBar.visibility = View.GONE
    }

    fun showSearchProgress() {
        searchProgressBar.visibility = View.VISIBLE
    }

}



