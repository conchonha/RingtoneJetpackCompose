package com.example.myapplication.domain.use_case

import com.example.myapplication.data.data_source.config.ResponseAPI
import com.example.myapplication.domain.model.CategoryResponse
import com.example.myapplication.domain.repository.RingtoneRepository
import com.example.myapplication.utils.extension.sored
import com.example.myapplication.utils.storted.SortedProperty
import com.example.myapplication.utils.storted.SortedType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllCategory(private val repository: RingtoneRepository) {
    operator fun invoke(sortedProperty: SortedProperty = SortedProperty.Title(SortedType.Normal)): Flow<List<CategoryResponse>> {
        return repository.getAllCategory().map { response ->
            return@map if (response is ResponseAPI.Success) {
                return@map response.data.sored(sortedProperty) as List<CategoryResponse>
            } else {
                emptyList()
            }
        }
    }
}