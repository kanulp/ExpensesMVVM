package com.kanulp.expensemvvm.repository

import com.kanulp.expensemvvm.data.models.ExpanseModel
import com.kanulp.expensemvvm.data.remote.ExpenseRemoteDataSource
import com.kanulp.expensemvvm.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ExpenseRepository @Inject constructor (private val remoteDatabase: ExpenseRemoteDataSource) {

    suspend fun fetchExpanses(): Flow<Resource<ExpanseModel>> {
        return flow {
            emit(Resource.loading())
            val result = remoteDatabase.getExpanses()
            emit(result)

        }.flowOn(Dispatchers.IO)
    }


}