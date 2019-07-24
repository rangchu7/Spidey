package com.example.decentralize.activities


import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.decentralize.Constants
import kotlinx.android.synthetic.main.activity_allocate_data.*
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.decentralize.utility.ScheduledJobService
import android.R.id




class AllocateData : AppCompatActivity() {

    private val workManager = WorkManager.getInstance(application)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.decentralize.R.layout.activity_allocate_data)

        //applySevice()

        hourSet.setOnClickListener{
            var curr = hourSet.text.toString().toInt()
            curr = (curr+1)%12
            if(curr == 0){ hourSet.text = "12" }
            else{ hourSet.text = curr.toString() }
            if(hourSet.text.toString().length < 2){hourSet.text = "0${hourSet.text.toString()}"}

        }
        minSet.setOnClickListener{
            var curr = minSet.text.toString().toInt()
            curr = (curr+10)%60
            minSet.text = curr.toString()
            if(minSet.text.toString().length < 2){minSet.text = "0${minSet.text.toString()}"}
        }
        amPmSet.setOnClickListener{
            if(amPmSet.text.toString() == "AM"){amPmSet.text = "PM"}
            else if(amPmSet.text.toString() == "PM"){amPmSet.text = "AM"}
        }
        useSet.setOnClickListener{
            if(useSet.text.toString() == "NN"){useSet.text = "YY"}
            else if(useSet.text.toString() == "YY"){useSet.text = "NN"}
        }



        startButton.setOnClickListener{

            Toast.makeText(this, "La", Toast.LENGTH_LONG).show()

            if(data_allocated_textView.text.isNotEmpty() ){
                val data= data_allocated_textView.text.toString().toDouble()


                Constants.Essentials.ALLOCATED_DATA = data

                if(Constants.Essentials.ALLOCATED_DATA != 0.0){
                    val  homeActivityIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeActivityIntent)
                    finish()
                }

            }
        }


    }



    internal fun applySevice() {
        workManager.enqueue(OneTimeWorkRequest.from(ScheduledJobService::class.java))
    }


}
