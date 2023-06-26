package org.android.go.sopt.presentation.sign

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.presentation.util.binding.BindingActivity
import org.android.go.sopt.presentation.util.extension.hideKeyboard
import org.android.go.sopt.presentation.util.extension.showSnackBar

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.root.setOnClickListener {
            hideKeyboard(it)
        }
    }

    private fun collectData() {
        viewModel.isCompleteSignUp.flowWithLifecycle(lifecycle).onEach { isCompleteSignUp ->
            if (isCompleteSignUp) {
                viewModel.saveUserInfo()
                moveToSignIn()
            } else
                binding.root.showSnackBar(getString(R.string.sign_up_fail_message))
        }.launchIn(lifecycleScope)
    }

    private fun moveToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        with(binding) {
            intent.putExtra(
                USER_INFO, viewModel?.getUserInfo()
            )
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}