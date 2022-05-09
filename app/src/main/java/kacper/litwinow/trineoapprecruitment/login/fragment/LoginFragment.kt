package kacper.litwinow.trineoapprecruitment.login.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kacper.litwinow.trineoapprecruitment.base.BaseFragment
import kacper.litwinow.trineoapprecruitment.cameras.activity.CamerasActivity
import kacper.litwinow.trineoapprecruitment.common.*
import kacper.litwinow.trineoapprecruitment.databinding.FragmentLoginBinding
import kacper.litwinow.trineoapprecruitment.login.viewmodel.LoginViewModel
import kacper.litwinow.trineoapprecruitment.model.Credentials

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val loginViewModel: LoginViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun setUp() {
        loginButtonAction()
        handleExceptionLogin()
    }

    private fun handleExceptionLogin() {
        loginViewModel.result.observe(viewLifecycleOwner) {
            when (it) {

                Error -> {
                    binding.appbar.bannerErrorAuthenticationAuthorization.banner.gone()
                    binding.progressBar.gone()
                    binding.errorUnexpected.show()
                }

                Loading -> {
                    binding.appbar.bannerErrorAuthenticationAuthorization.banner.gone()
                    binding.errorUnexpected.gone()
                }

                LoginSuccess -> {
                    val intent = Intent(requireContext(), CamerasActivity::class.java)
                    requireActivity().startActivity(intent)
                    binding.appbar.bannerErrorAuthenticationAuthorization.banner.gone()
                    binding.errorUnexpected.gone()
                }

                AuthorizeError -> {
                    binding.appbar.bannerErrorAuthenticationAuthorization.banner.show()
                    binding.progressBar.gone()
                    binding.errorUnexpected.gone()
                }

                AuthenticateError -> {
                    binding.appbar.bannerErrorAuthenticationAuthorization.banner.show()
                    binding.progressBar.gone()
                    binding.errorUnexpected.gone()
                }
            }
        }
    }

    private fun loginButtonAction() {
        binding.btnLogin.setOnClickListener {
            loginViewModel.login(
                Credentials(
                    binding.etUsername.text.toString(),
                    binding.etPassword.text.toString(),
                )
            )
            hideKeyboard()
            binding.progressBar.show()
        }
    }
}