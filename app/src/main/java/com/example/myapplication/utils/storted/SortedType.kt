package com.example.myapplication.utils.storted

sealed class SortedType {
    object Ascending : SortedType()
    object Descending : SortedType()
    object Normal : SortedType()
}