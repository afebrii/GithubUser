package com.afebrii.githubuser.data.retrofit

import com.afebrii.githubuser.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: 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")
    @GET("search/users")
    fun getUsers(
        @Query("q") query: String
    ): Call<GithubResponse>
}