package com.example.decentralize.models

import android.content.Context
import android.util.Log
import com.example.decentralize.Constants
import retrofit2.Call
import retrofit2.http.*

class masterModel(context: Context){
    init { }


    interface configDataService{
        @GET("master/ap1.json")
        fun GET_CONFIG_DATA(
        ):Call<configData>
    }

    interface jobConfigService{
        @GET("jobdet/{token}")
        fun GET_JOB_CONFIG( @Path("token") token : String ) : Call<jobConfig>
    }

    interface webSiteResponse{
        @GET("/")
        fun GET_WEB_HTML(

        ): Call<String>


        @POST("/")
        fun POST_WEB_HTML_PAYLOAD(
            @Body postFields : PostFields
        ): Call<String>
    }


}