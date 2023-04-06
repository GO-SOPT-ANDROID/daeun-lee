package org.android.go.sopt.presentation.sign

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.data.datasource.local.GSDataStore
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.presentation.common.ViewModelFactory
import org.android.go.sopt.presentation.model.UserInfo
import org.android.go.sopt.presentation.util.extension.hideKeyboard
import org.android.go.sopt.presentation.util.extension.showSnackBar

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
    }

    private fun addListeners() {
        binding.root.setOnClickListener {
            hideKeyboard(it)
        }
        binding.btnSignUp.setOnClickListener {
            viewModel.isValid()
            if (viewModel.isValidSign.value) {
                GSDataStore(this).userName = viewModel.inputName.value
                GSDataStore(this).userFavoriteSong = viewModel.inputFavoriteSong.value
                moveToSignIn()
            } else
                it.showSnackBar(getString(R.string.sign_up_fail_message))
        }
    }

    private fun moveToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        with(binding) {
            intent.putExtra(
                USER_INFO,
                UserInfo(
                    etSignUpId.text.toString(),
                    etSignUpPw.text.toString(),
                    etSignUpName.text.toString(),
                    etSignUpFavoriteSong.text.toString()
                )
            )
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}