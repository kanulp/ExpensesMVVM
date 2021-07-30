package com.kanulp.expensemvvm.data.remote

import com.kanulp.expensemvvm.data.models.ExpanseModel
import retrofit2.Response
import retrofit2.http.GET

interface ExpensesApiService {
    @GET("v3/f7795a9e-fc24-4b66-ad1b-08613565a610")
    suspend fun getExpanses(): Response<ExpanseModel>
}