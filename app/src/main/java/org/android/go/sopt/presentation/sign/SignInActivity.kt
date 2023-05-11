package org.android.go.sopt.presentation.sign

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignInBinding
import org.android.go.sopt.presentation.home.HomeActivity
import org.android.go.sopt.presentation.model.UserInfo
import org.android.go.sopt.presentation.util.binding.BindingActivity
import org.android.go.sopt.presentation.util.extension.hideKeyboard
import org.android.go.sopt.presentation.util.extension.showSnackBar
import org.android.go.sopt.presentation.util.extension.showToast

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        checkAutoSignIn()
        setSignUpResult()
        addListener()
        collectData()
    }

    private fun addListener() {
        binding.root.setOnClickListener {
            hideKeyboard(it)
        }
        binding.btnSignUp.setOnClickListener {
            moveToSignUp()
        }
    }

    private fun collectData() {
        lifecycleScope.launchWhenStarted {
            viewModel.isCompleteSignIn.collect { isCompleteSignIn ->
                if (isCompleteSignIn == null) return@collect
                if (isCompleteSignIn) {
                    showToast(getString(R.string.sign_in_success_message))
                    moveToHome()
                } else {
                    showToast(getString(R.string.sign_in_fail_message))
                }
            }
        }
    }

    private fun setSignUpResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val resultData = result.data ?: return@registerForActivityResult
                    resultData.getParcelableExtra<UserInfo>(USER_INFO)?.let {
                        viewModel.setUserInfo(it)
                        binding.root.showSnackBar(getString(R.string.sign_up_success_message))
                    }
                }
            }
    }

    private fun checkAutoSignIn() {
        if (viewModel.isAutoSignIn.value)
            moveToHome()
    }

    private fun moveToSignUp() {
        resultLauncher.launch(Intent(this, SignUpActivity::class.java))
    }

    private fun moveToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}