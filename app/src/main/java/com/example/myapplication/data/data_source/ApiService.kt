package com.example.myapplication.data.data_source

import com.example.myapplication.data.data_source.config.ResponseAPI
import com.example.myapplication.domain.model.CategoryResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("exec")
    fun getAllCategory(
        @Query("action") action: String = "Category",
        @Query("value") value: String = "ALL",
    ): Flow<ResponseAPI<List<CategoryResponse>>>
}