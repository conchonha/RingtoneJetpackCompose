package com.example.myapplication.utils.storted

sealed class SortedProperty(val sortedType: SortedType) {
    class Title(sortedType: SortedType) : SortedProperty(sortedType = sortedType)
    class Date(sortedType: SortedType) : SortedProperty(sortedType = sortedType)
    class Id(sortedType: SortedType) : SortedProperty(sortedType = sortedType)
    class Size(sortedType: SortedType) : SortedProperty(sortedType = sortedType)
    object Normal : SortedProperty(sortedType = SortedType.Normal)
}