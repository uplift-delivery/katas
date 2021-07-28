package com.uplift_delivery.advanced_gilded_rose

import kotlinx.serialization.Serializable

@Serializable
data class Item(var id: Int, var name: String, var quality: Int, var sell_in: Int)