package org.android.go.sopt.presentation.sign

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignInBinding
import org.android.go.sopt.presentation.common.ViewModelFactory
import org.android.go.sopt.presentation.home.HomeActivity
import org.android.go.sopt.presentation.model.UserInfo
import org.android.go.sopt.presentation.util.extension.hideKeyboard
import org.android.go.sopt.presentation.util.extension.showSnackBar
import org.android.go.sopt.presentation.util.extension.showToast

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel: SignViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        viewModel.isCompleteSign.flowWithLifecycle(lifecycle).onEach { isCompleteSign ->
            isCompleteSign?.let {
                if (it) {
                    showToast(getString(R.string.sign_in_success_message))
                    moveToHome()
                } else
                    showToast(getString(R.string.sign_in_fail_message))
            }
        }.launchIn(lifecycleScope)
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