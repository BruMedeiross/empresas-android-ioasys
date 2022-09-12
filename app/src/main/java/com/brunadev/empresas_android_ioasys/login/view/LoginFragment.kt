package com.brunadev.empresas_android_ioasys.login.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.brunadev.empresas_android_ioasys.R
import com.brunadev.empresas_android_ioasys.databinding.FragmentLoginBinding
import com.brunadev.empresas_android_ioasys.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presenter = LoginPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {

        btnLogin.setOnClickListener {
            it.hideKeyboard()
            var email = emailText.text.toString().trim()
            var password = passwordText.text.toString().trim()
            presenter.doLogin(email, password)

        }

    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(emailText.windowToken, 0)
        imm.hideSoftInputFromWindow(passwordText.windowToken, 0)
    }

    private fun validate(): Boolean {
        return if (emailText.text.toString().isEmpty()
            || !emailText.text.toString().contains("@")
            || passwordText.text.toString().isEmpty()
        ) {
            if (passwordText.text.toString().isEmpty()) {
                passwordText.requestFocus()
                passwordText.error = getString(R.string.pass_error)
            }
            if (emailText.text.toString().isEmpty())
                emailText.requestFocus()
                emailText.error = getString(R.string.email_error)

            if (!emailText.text.toString().contains("@"))
                emailText.requestFocus()
                email.error = getString(R.string.email_error)
                false

        } else {
            password.error = null
            email.error = null
            true
        }
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        hideProgress()
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
    }

    fun goToNextScreen() {
        val bundle = Bundle()
        val email = emailText.text.toString().trim()
        val pass = passwordText.text.toString().trim()
        bundle.putString("email", email)
        bundle.putString("password", pass)

        findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)
    }

}