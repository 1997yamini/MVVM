package com.app.demomvvm.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.app.demomvvm.R
import com.app.demomvvm.data.model.request.ContactUsRequest
import com.app.demomvvm.databinding.ActivityContactUsBinding
import com.app.demomvvm.ui.base.BaseActivity
import com.app.demomvvm.ui.main.viewModel.WebViewModel
import com.app.demomvvm.utils.AMFIXO_HTTPS_FAILURE
import com.app.demomvvm.utils.AMFIXO_HTTPS_LOADING
import com.app.demomvvm.utils.AMFIXO_HTTPS_SUCCESS
import com.app.demomvvm.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactUsActivity : BaseActivity<ActivityContactUsBinding>() {

    private val webViewModel by viewModel<WebViewModel>()
    private lateinit var contactUsRequest: ContactUsRequest


    override fun initView() {
        setObserver()
    }

    private fun setObserver() {

        webViewModel.contactUsLiveData.observe(this,{

            when(it.status){
                AMFIXO_HTTPS_LOADING-> showProgress()
                AMFIXO_HTTPS_FAILURE ->{
                    hideProgress()
                }
                AMFIXO_HTTPS_SUCCESS -> {
                    hideProgress()
                    showToast("message sent")

                }
            }
        })
    }

    override fun setListener() {
        binding.ivBack.setOnClickListener { onBackPressed() }
        binding.tvRegister.setOnClickListener {
            if (isValid())
                webViewModel.contactUs(contactUsRequest)
        }
    }

    private fun isValid(): Boolean {

        if (binding.etName.text.toString().isEmpty()) {
            showToast("Please enter your name.")
            return false
        }
        if (binding.etEmail.text.toString().isEmpty()) {
            showToast("Please enter your email address.")
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()) {
            showToast("Please enter valid email address.")
            return false
        }
        if (binding.etContact.text.toString().isEmpty()) {
            showToast("Please enter your mobile number")
            return false
        }
        if (!Patterns.PHONE.matcher(binding.etContact.text.toString())
                .matches() || binding.etContact.text.toString().length != 10
        ) {
            showToast("Please enter valid mobile number")
            return false
        }
        if (binding.etQuery.text.toString().isEmpty()) {
            showToast("Please enter your query.")
            return false
        }
        contactUsRequest = ContactUsRequest(
            binding.etName.text.toString(),
            binding.etEmail.text.toString(),
            binding.etContact.text.toString(),
            binding.etQuery.text.toString()
        )
        return true
    }


    override fun getLayout(): Int = R.layout.activity_contact_us

}