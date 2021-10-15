package com.r.fia.preferences

import android.content.Context
import android.content.SharedPreferences
import java.util.*

object Preferences {
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PrefEntity.TAG, Context.MODE_PRIVATE)
    }

    fun setStringPreference(context: Context, key: String?, `val`: String?) {
        val settings = getSharedPreferences(context)
        val editor = settings.edit()
        editor.putString(key, `val`)
        editor.apply()
    }

    fun setBooleanPreference(context: Context, key: String?, `val`: Boolean) {
        val settings = getSharedPreferences(context)
        val editor = settings.edit()
        editor.putBoolean(key, `val`)
        editor.apply()
    }

    fun setIntegerPreference(context: Context, key: String?, `val`: Int) {
        val settings = getSharedPreferences(context)
        val editor = settings.edit()
        editor.putInt(key, `val`)
        editor.apply()
    }

    fun setPreference_float(context: Context, key: String?, `val`: Float) {
        val settings = getSharedPreferences(context)
        val editor = settings.edit()
        editor.putFloat(key, `val`)
        editor.apply()
    }

    fun setPreference_long(context: Context, key: String?, `val`: Long) {
        val settings = getSharedPreferences(context)
        val editor = settings.edit()
        editor.putLong(key, `val`)
        editor.apply()
    }

    fun setPreferenceArray(
        mContext: Context, key: String,
        array: List<String?>
    ): Boolean {
        val prefs = getSharedPreferences(mContext)
        val editor = prefs.edit()
        editor.putInt(key + "_size", array.size)
        for (i in array.indices) editor.putString(key + "_" + i, array[i])
        return editor.commit()
    }

    fun clearPreferenceArray(c: Context, key: String) {
        val settings = getSharedPreferences(c)
        if (getPreferenceArray(c, key) != null
            && getPreferenceArray(c, key)!!.size > 0
        ) {
            for (element in getPreferenceArray(c, key)!!) {
                if (findPrefrenceKey(c, element) != null
                    && settings.contains(findPrefrenceKey(c, element))
                ) {
                    val editor = settings.edit()
                    editor.remove(findPrefrenceKey(c, element))
                    editor.apply()
                }
            }
        }
    }

    fun findPrefrenceKey(con: Context, value: String?): String? {
        val settings = getSharedPreferences(con)
        val editor = settings.all
        for ((key, value1) in editor) {
            if (value == value1) {
                return key
            }
        }
        return null // not found
    }

    fun getStringPreference(context: Context, key: String?): String? {
        val prefs = getSharedPreferences(context)
        return prefs.getString(key, "")
    }

    fun getStringPreference1(context: Context, key: String?): String? {
        val prefs = getSharedPreferences(context)
        return prefs.getString(key, "0")
    }

    fun getPreferenceArray(
        mContext: Context,
        key: String
    ): ArrayList<String?>? {
        val prefs = getSharedPreferences(mContext)
        val size = prefs.getInt(key + "_size", 0)
        val array = ArrayList<String?>(size)
        for (i in 0 until size) array.add(prefs.getString(key + "_" + i, null))
        return array
    }

    fun getPreference_long(context: Context, key: String?): Long {
        val prefs = getSharedPreferences(context)
        return prefs.getLong(key, 0)
    }

    fun getBooleanPreference(context: Context, key: String?): Boolean {
        val prefs = getSharedPreferences(context)
        return prefs.getBoolean(key, false)
    }

    fun getIntegerPreference(context: Context, key: String?): Int {
        val prefs = getSharedPreferences(context)
        return prefs.getInt(key, -1)
    }

    fun getAllPreference(context: Context): String {
        val settings = getSharedPreferences(context)
        val editor = settings.all
        var text = ""
        try {
            for ((key, value1) in editor) {
                val value = value1!!
                // do stuff
                text += "\t$key = $value\t"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return text
    }

    fun removePreference(context: Context, key: String?) {
        val settings = getSharedPreferences(context)
        val editor = settings.edit()
        editor.remove(key)
        editor.apply()
    }

    fun removeAllPreference(context: Context) {
        val settings = getSharedPreferences(context)
        val editor = settings.edit()
        editor.clear()
        editor.apply()
    }
}