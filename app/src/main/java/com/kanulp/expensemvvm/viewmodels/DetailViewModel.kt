package com.kanulp.expensemvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanulp.expensemvvm.data.models.ExpanseModelElement
import kotlinx.coroutines.launch

public class DetailViewModel: ViewModel() {

    private val _expense = MutableLiveData<ExpanseModelElement>()
    val expense = _expense

    fun setExpense(expense : ExpanseModelElement) {
        viewModelScope.launch {
                _expense.value = expense
        }
    }
}