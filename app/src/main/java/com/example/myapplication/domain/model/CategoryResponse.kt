package com.example.myapplication.domain.model

import com.google.gson.annotations.SerializedName


class CategoryResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("total")
    val total: Int? = 0,
    @SerializedName("type")
    val type: String? = ""
) {
    override fun hashCode() = id ?: super.hashCode()

    override fun equals(other: Any?): Boolean {
        return (other as? CategoryResponse)?.let {
            other.name == name && other.image == image && other.total == total && other.type == type
        } ?: false
    }
}