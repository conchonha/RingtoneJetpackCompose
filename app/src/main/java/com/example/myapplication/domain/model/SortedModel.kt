package com.example.myapplication.domain.model

open class SortedModel(
    @Transient
    val titleSort: String = "",
    @Transient
    val dateSort: String = "",
    @Transient
    val idSort : Int = 0,
    @Transient
    val sizeSort : String = "",
)