package com.kanulp.expensemvvm.data.remote

import javax.inject.Inject


class ExpenseRemoteDataSource @Inject constructor(private val expenseApiService : ExpensesApiService):BaseDataSource(){
    suspend fun getExpanses() =  getResult { expenseApiService.getExpanses() }

}
