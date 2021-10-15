package com.app.demomvvm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.demomvvm.databinding.ActivityFormBinding
import android.widget.Toast

import com.google.gson.Gson

import android.content.SharedPreferences

import android.content.Context.MODE_PRIVATE
import android.util.Log

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import androidx.recyclerview.widget.LinearLayoutManager
import com.r.fia.preferences.PrefEntity
import com.r.fia.preferences.Preferences

class FormActivity : BaseActivity<ActivityFormBinding>() {

    private var clickAction:ClickAction?=null
    private var userList: ArrayList<UserData>? = ArrayList()

    override fun initView() {
        if(!Preferences.getStringPreference(this,PrefEntity.USERS_LIST).isNullOrEmpty()){
            val type: Type? = object : TypeToken<ArrayList<UserData?>?>() {}.type
            userList = Gson().fromJson<Any>(Preferences.getStringPreference(this,PrefEntity.USERS_LIST), type) as ArrayList<UserData>?
        }
        Log.d("MYDATA1",Gson().toJson(userList)+"--->"+Preferences.getStringPreference(this,PrefEntity.USERS_LIST))

        setClickAction()
    }

    private fun setClickAction() {
        clickAction= ClickAction(this)
        binding.clickActionForm=clickAction
    }


    override fun setListener() {
        binding.btnsave.setOnClickListener {
            if(isValidate()){
                val userData=UserData()
                userData.Name = binding.etname.text.toString()
                userList!!.add(userData)
               saveData()
            }else{
                Toast.makeText(this,"pls enter text",Toast.LENGTH_LONG).show()
            }
        }

       /* binding.btnGetData.setOnClickListener {
            loadData()
        }
*/
    }


    private fun saveData() {

        Preferences.setStringPreference(this,PrefEntity.USERS_LIST,Gson().toJson(userList))

        // after saving data we are displaying a toast message.
        Log.d("MYDATA2",Gson().toJson(userList)+"--->"+Preferences.getStringPreference(this,PrefEntity.USERS_LIST))

        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun isValidate(): Boolean {
        if(!binding.etname.text.isNullOrEmpty()){
            return true
        }else return false

    }

    override fun getLayout(): Int =R.layout.activity_form

    class ClickAction(var context: Context?) {

    }
}