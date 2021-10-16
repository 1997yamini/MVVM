package com.app.demomvvm.ui.main.view.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.app.demomvvm.R
import com.app.demomvvm.data.model.response.UserData
import com.app.demomvvm.ui.adapter.UserListAdapter
import com.app.demomvvm.databinding.ActivityMainBinding
import com.app.demomvvm.ui.base.BaseActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.r.fia.preferences.PrefEntity
import com.r.fia.preferences.Preferences
import java.lang.reflect.Type

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var clickAction: ClickAction? = null
    private lateinit var adapter: UserListAdapter
    private var userList: ArrayList<UserData>? = ArrayList()


    override fun initView() {
       /* val fragment = HomeFragment()
        if (fragment == null) return
        // supportFragmentManager.beginTransaction().apply { add(R.id.mainContainer, fragment).commit() }
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.add(R.id.mainContainer, fragment)
        tr.commitAllowingStateLoss() */
       // loadData()
       // setAdatpter()
        setClickAction()
    }

    override fun onStart() {

        loadData()

        super.onStart()
    }

    override fun setListener() {
    }

    private fun setClickAction() {
        clickAction = ClickAction(this)
        binding.setClickAction(this)

    }

    private fun loadData() {

        if(!Preferences.getStringPreference(this, PrefEntity.USERS_LIST).isNullOrEmpty()){
            val type: Type? = object : TypeToken<ArrayList<UserData?>?>() {}.type
            userList = Gson().fromJson<Any>(Preferences.getStringPreference(this, PrefEntity.USERS_LIST), type) as ArrayList<UserData>?
        }

        Log.d("MyData",""+userList)

        setAdatpter()

    }


    fun setAdatpter(){
        adapter= UserListAdapter(this,userList!!)
        binding.recyclerUser.adapter=adapter
    }
    override fun getLayout(): Int = R.layout.activity_main

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