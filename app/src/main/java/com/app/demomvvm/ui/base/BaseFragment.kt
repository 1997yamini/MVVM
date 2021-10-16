package com.app.demomvvm.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment <T:ViewDataBinding>: Fragment() {
    protected lateinit var binding: T
    private var mLoadingDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<T>(inflater, getLayout(), container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        setListener()
    }

    /*  fun showProgress() {
          try {
              if (mLoadingDialog == null) {
                  context?.let {
                      mLoadingDialog = Dialog(it, R.style.transDialog)
                      mLoadingDialog!!.setContentView(R.layout.app_progress)
                  }
              }
              mLoadingDialog?.setCancelable(false)
              mLoadingDialog?.show()
          } catch (e: Exception) {
              e.printStackTrace()
          }

      }*/

    /*  fun hideProgress() {
          if (mLoadingDialog != null && mLoadingDialog!!.isShowing())
              mLoadingDialog!!.dismiss()
          mLoadingDialog = null
      }

      fun navigateTo(des: Class<*>, data: Bundle? = null, request: Int? = null) {
          context?.let {
              val intent = Intent()
              intent.setClass(it, des)
              data?.let {
                  intent.putExtra(BUNDLE, it)
              }
              request?.let {
                  startActivityForResult(intent, it)
              } ?: kotlin.run { startActivity(intent) }
          }
      }
      protected fun startCropImageActivity(imageUri: Uri?) {
          CropImage.startPickImageActivity(requireContext(),this)
      }
  */
    abstract fun initView()
    abstract fun setListener()
    abstract fun getLayout(): Int
}