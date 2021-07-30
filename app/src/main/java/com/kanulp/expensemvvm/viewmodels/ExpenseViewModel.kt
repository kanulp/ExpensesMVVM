package com.kanulp.expensemvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanulp.expensemvvm.data.models.ExpanseModel
import com.kanulp.expensemvvm.repository.ExpenseRepository
import com.kanulp.expensemvvm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(private val repository: ExpenseRepository) : ViewModel() {

    private val _expenseList = MutableLiveData<Resource<ExpanseModel>>()
    val expenseList = _expenseList
    private val _sum : MutableLiveData<Double> = MutableLiveData<Double>()
    val sum = _sum

    init {
        fetchExpanses()
    }

    private fun fetchExpanses() {
        viewModelScope.launch {

          repository.fetchExpanses().collect {
              _expenseList.value = it
          }
            sum.value = _expenseList.value?.data?.map {
                it.amount
            }?.sum()?.toDouble()
        }
    }
}