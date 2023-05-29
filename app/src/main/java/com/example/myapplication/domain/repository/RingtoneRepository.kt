package com.example.myapplication.domain.repository

import com.example.myapplication.data.data_source.config.ResponseAPI
import com.example.myapplication.domain.model.CategoryResponse
import kotlinx.coroutines.flow.Flow

interface RingtoneRepository {
    fun getAllCategory() : Flow<ResponseAPI<List<CategoryResponse>>>
}