package com.example.myapplication.domain.use_case

import com.example.myapplication.data.data_source.config.ResponseAPI
import com.example.myapplication.domain.model.CategoryResponse
import com.example.myapplication.domain.repository.RingtoneRepository
import com.example.myapplication.utils.storted.SortedProperty
import com.example.myapplication.utils.storted.SortedType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllCategory(private val repository: RingtoneRepository) {
    operator fun invoke(sortedProperty: SortedProperty = SortedProperty.Title(SortedType.Normal)): Flow<List<CategoryResponse>> {
        return repository.getAllCategory().map { response ->
            return@map if (response is ResponseAPI.Success) {
                val data: List<CategoryResponse> = when (sortedProperty.sortedType) {
                    is SortedType.Ascending -> {
                        when (sortedProperty) {
                            is SortedProperty.Title -> response.data.sortedBy {
                                it.name.toString().lowercase()
                            }

                            is SortedProperty.Id -> response.data.sortedBy { it.id }
                            is SortedProperty.Size -> response.data.sortedBy { it.total }
                            else -> response.data
                        }
                    }

                    is SortedType.Descending -> {
                        when (sortedProperty) {
                            is SortedProperty.Title -> response.data.sortedByDescending {
                                it.name.toString().lowercase()
                            }

                            is SortedProperty.Id -> response.data.sortedByDescending { it.id }
                            is SortedProperty.Size -> response.data.sortedByDescending { it.total }
                            else -> response.data
                        }
                    }

                    else -> response.data
                }

                data
            } else {
                emptyList()
            }
        }
    }
}