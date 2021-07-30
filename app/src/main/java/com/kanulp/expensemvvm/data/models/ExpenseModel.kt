package com.kanulp.expensemvvm.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

typealias ExpanseModel = ArrayList<ExpanseModelElement>

@Parcelize
data class ExpanseModelElement (
    val amount: Long,
    val attachments: @RawValue List<Attachment>,
    val date: String,
    val expenseTitle: String,
    val category: String,
    val currencyCode: String,
    val user: @RawValue User
): Parcelable

data class Attachment (
    val id: String,
    val userID: String,
    val image: String
)

data class User (
    val email: String,
    val created: String,
    val id: String
)