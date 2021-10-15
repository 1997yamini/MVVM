package com.app.demomvvm

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: T
    protected lateinit var dialog: AlertDialog
    private var mLoadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<T>(this, getLayout())

        // initDialog()
        initView()
        setListener()
    }
    /*  private fun initDialog() {
          dialog = AlertDialog.Builder(this, R.style.transDialog).setView(R.layout.app_progress).create()
      }

      fun showProgress() {
          try {
              if (mLoadingDialog == null) {
                  mLoadingDialog = Dialog(this, R.style.transDialog)
                  mLoadingDialog!!.setContentView(R.layout.app_progress)
              }
              mLoadingDialog!!.setCancelable(false)
              mLoadingDialog!!.show()
          } catch (e: Exception) {
              e.printStackTrace()
          }
      }

      fun hideProgress() {
          if (mLoadingDialog != null && mLoadingDialog!!.isShowing) {
              mLoadingDialog!!.dismiss()
          }
          mLoadingDialog = null
      }

      fun navigateTo(des: Class<*>, data: Bundle? = null, request: Int? = null) {
          val intent = Intent()
          intent.setClass(this, des)
          data?.let {
              intent.putExtra(BUNDLE, it)
          }
          request?.let {
              startActivityForResult(intent, it)
          } ?: kotlin.run {
              startActivity(intent) }
      }
  */

    abstract fun initView()
    abstract fun setListener()
    abstract fun getLayout(): Int
}