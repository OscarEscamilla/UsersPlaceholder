package com.oscarescamilla.com.data.model.post

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
): Parcelable