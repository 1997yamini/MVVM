package com.app.demomvvm.ui.main.view.fragment

import android.content.Context
import android.content.Intent
import android.view.View
import com.app.demomvvm.R
import com.app.demomvvm.databinding.FragmentHomeBinding
import com.app.demomvvm.ui.base.BaseFragment
import com.app.demomvvm.ui.main.view.activity.FormActivity

class HomeFragment() : BaseFragment<FragmentHomeBinding>() {
    private var clickAction: ClickAction? = null

    override fun initView() {
        setClickAction()
    }

    private fun setClickAction() {
        clickAction = ClickAction(requireContext())
    }

    override fun setListener() {
    }

    override fun getLayout(): Int = R.layout.fragment_home

    class ClickAction(var context: Context?) {

        fun OpenForm(view: View?) {

            val inn = Intent(context, FormActivity::class.java)
            context!!.startActivity(inn)
            /* //fragment to fragment call
             val newFragment: Fragment = FormFragment()

             val transaction = parentFragmentManager.beginTransaction()
             transaction.replace(
                 R.id.mainContainer,
                 newFragment
             )
             transaction.addToBackStack(null)

             transaction.commit()*/
        }
    }
}