package com.example.decentralize.utility

import android.util.Log
import com.example.decentralize.Constants
import com.example.decentralize.activities.HomeActivity
import com.example.decentralize.activities.S
import com.example.decentralize.models.configData
import com.example.decentralize.models.jobConfig
import com.example.decentralize.models.masterModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.floor

class Switch(context: HomeActivity){


    var currentDataDownloaded = 0.0
    val appContext = context


    fun conFigDataFunction(){
        Log.e(Constants.Essentials.JOB_CONFIG_ERROR, "config Data")
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.Test.API1+"/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val setData = retrofit.create(masterModel.configDataService::class.java)

        val result = setData.GET_CONFIG_DATA()
        result.enqueue(object : Callback<configData> {
            override fun onFailure(call: Call<configData>, t: Throwable) {
                Log.e(Constants.Essentials.JOB_CONFIG_ERROR, t.message.toString())
            }

            override fun onResponse(call: Call<configData>, response: Response<configData>) {
                Log.e(Constants.Essentials.JOB_CONFIG_ERROR, response.body().toString())

                if(response.isSuccessful){
                    val  temp = S.getJobId(appContext, response.body()!!.jobId)


                    if(temp == "null"){
                        jobConfigFunction(response.body()!!.jobId, response.body()!!.placeholders)
                        Log.e(Constants.Essentials.JOB_CONFIG_ERROR, "Made a preference entry !!")
                    }else{
                        val jobConfigData  = Gson().fromJson(temp, jobConfig::class.java)
                        Log.e(Constants.Essentials.JOB_CONFIG_ERROR, "Read from preference entry $jobConfigData")


                        Log.e(Constants.Essentials.JOB_CONFIG_ERROR, jobConfigData.baseUrl+"RD")
                        getRequest(jobConfigData.baseUrl, jobConfigData)

                    }


                }else{
                    // no response
                }

            }

        })

    }


    fun jobConfigFunction(jobId: String, placeholder: List<String>){
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.Test.API2+"/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val setData = retrofit.create(masterModel.jobConfigService::class.java)

        val result = setData.GET_JOB_CONFIG("${jobId}.txt")
        result.enqueue(object : Callback<jobConfig> {
            override fun onFailure(call: Call<jobConfig>, t: Throwable) {
                Log.e(Constants.Essentials.JOB_CONFIG_ERROR, t.message.toString())
            }

            override fun onResponse(call: Call<jobConfig>, response: Response<jobConfig>) {
                Log.e(Constants.Essentials.JOB_CONFIG_ERROR, response.body().toString())

                if(response.isSuccessful){


                    val gson = GsonBuilder().setPrettyPrinting().create()
                    var jobConfigJSON = gson.toJson(response.body())
                    for(i in 0..response.body()!!.countPH-1){
                        jobConfigJSON = jobConfigJSON.replace("PLACEHOLDER"+i, placeholder.get(i))
                    }
                    S.setJobId(appContext, response.body()!!.jobId, jobConfigJSON)



                    val fResponse =  Gson().fromJson(jobConfigJSON, jobConfig::class.java)




                    Log.e(Constants.Essentials.JOB_CONFIG_ERROR, fResponse.toString())

                    if(response.body()?.method.equals("GET")){
                        getRequest(fResponse.baseUrl, fResponse)
                    }else{
                        if(fResponse.reqType.equals("1")){
                            getRequest(fResponse.baseUrl, fResponse)
                        }else{
                            getRequest(fResponse.baseUrl, fResponse)
                        }
                    }
                }else{
                    //no response
                }

            }

        })
    }




    fun getRequest(temp : String, jobConfigration: jobConfig){
        val okHttpClient = OkHttpClient.Builder().build()
        val subRetrofit = Retrofit.Builder()
            .baseUrl("$temp/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val  setHTMLData = subRetrofit.create(masterModel.webSiteResponse :: class.java)
        val HTMLresult = setHTMLData.GET_WEB_HTML()

        HTMLresult.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(Constants.Essentials.JOB_CONFIG_ERROR, t.message.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val temp = response.body()
                Log.e(Constants.Essentials.JOB_CONFIG_ERROR, temp)
                //testingStuffandGo.append(temp)

                currentDataDownloaded+= (temp!!.toByteArray().size)/1000000.0
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                appContext.progessBar.progress = floor(currentDataDownloaded).toInt()

                appContext.current_data_text_view.text = "${df.format(currentDataDownloaded).toString()}/${Constants.Essentials.ALLOCATED_DATA}"
                if(currentDataDownloaded < Constants.Essentials.ALLOCATED_DATA){
                    GlobalScope.launch {
                        conFigDataFunction()
                    }
                }else{
                    S.setSessionId(appContext, "null")
                }
            }

        })

    }

    fun postRequest(temp : String, jobConfigration: jobConfig){
        val okHttpClient = OkHttpClient.Builder().build()
        val subRetrofit = Retrofit.Builder()
            .baseUrl("$temp/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val  setHTMLData = subRetrofit.create(masterModel.webSiteResponse :: class.java)
        val HTMLresult = setHTMLData.GET_WEB_HTML()

        HTMLresult.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(Constants.Essentials.JOB_CONFIG_ERROR, t.message.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val temp = response.body()
                Log.e(Constants.Essentials.JOB_CONFIG_ERROR, temp)
                //testingStuffandGo.append(temp)



            }

        })

    }



}
