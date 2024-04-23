package com.example.domain.util

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}