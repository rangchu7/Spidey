package com.example.decentralize.utility

import android.content.Context
import android.content.SharedPreferences
import com.example.decentralize.activities.HomeActivity

class PreferedStorage{

    val CURRENT_SESSION = "CURRENT_SESSION"

    fun shpr(context: Context) : SharedPreferences{
        return context.getSharedPreferences("MyPref", 0)
    }


    fun getJobId(context: Context, jobId: String) :String{
        val temp = shpr(context)
        val editor = temp.edit()
        return temp.getString(jobId, "null")!!

    }

    fun setJobId(context: Context, jobId: String, jobConfig: String){
        val temp = shpr(context)
        val editor = temp.edit()
        editor.putString(jobId, jobConfig)
        editor.commit()
    }


    fun getSessionId(context: Context) : String {
        val temp = shpr(context)
        val editor = temp.edit()
        return temp.getString(CURRENT_SESSION, "null")!!
    }

    fun setSessionId(context: Context, sid: String){
        val temp = shpr(context)
        val editor = temp.edit()
        editor.putString(CURRENT_SESSION, sid)
        editor.commit()
    }



}