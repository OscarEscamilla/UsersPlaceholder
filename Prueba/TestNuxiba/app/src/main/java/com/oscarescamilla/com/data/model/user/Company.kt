package com.oscarescamilla.com.data.model.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
): Parcelable