package kacper.litwinow.trineoapprecruitment.login.activity

import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import kacper.litwinow.trineoapprecruitment.base.BaseActivity
import kacper.litwinow.trineoapprecruitment.databinding.ActivityLoginBinding
import kacper.litwinow.trineoapprecruitment.login.fragment.LoginFragment

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate

    override fun setUp() {
        replaceFragment(LoginFragment())
    }
}
