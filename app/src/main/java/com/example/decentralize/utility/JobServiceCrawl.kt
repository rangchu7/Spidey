package com.example.decentralize.utility

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.decentralize.Constants
import com.example.decentralize.activities.HomeActivity

class ScheduledJobService(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {

        return try{
            /*for (i in 0..50){
                Log.e("cw", i.toString())
                Thread.sleep(5000)
            }*/
            Log.e( Constants.Essentials.JOB_CONFIG_ERROR, "call ed" )
            Switch(applicationContext as HomeActivity).conFigDataFunction()
            Result.success()
        }catch (e : Exception){
            Result.failure()
        }
    }






}