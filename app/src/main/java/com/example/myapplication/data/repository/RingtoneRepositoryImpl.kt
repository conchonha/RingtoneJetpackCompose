package com.example.myapplication.data.repository

import com.example.myapplication.data.data_source.ApiService
import com.example.myapplication.data.data_source.config.ResponseAPI
import com.example.myapplication.domain.model.CategoryResponse
import com.example.myapplication.domain.repository.RingtoneRepository
import kotlinx.coroutines.flow.Flow

class RingtoneRepositoryImpl (private val apiService: ApiService) : RingtoneRepository{
    override fun getAllCategory(): Flow<ResponseAPI<List<CategoryResponse>>> {
        return apiService.getAllCategory()
    }
}