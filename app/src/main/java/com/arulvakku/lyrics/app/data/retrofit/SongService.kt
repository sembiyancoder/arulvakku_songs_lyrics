package com.arulvakku.lyrics.app.data.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


/**
 * @author bsoft-61 on 15/2/21.
 * */
interface SongService {
    @Headers("Content-Type: application/json")
    @GET("GetSongsList")
    suspend fun getSongs(): SongResponseNetworkEntity
}