package com.kanulp.expensemvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kanulp.expensemvvm.data.models.ExpanseModelElement
import com.kanulp.expensemvvm.databinding.ItemExpanseBinding
import com.kanulp.expensemvvm.ui.expenses.ExpensesFragmentDirections

class ExpenseAdapter : ListAdapter<ExpanseModelElement,RecyclerView.ViewHolder>(ExpenseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExpenseViewHolder(
            ItemExpanseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val expense = getItem(position)
        (holder as ExpenseViewHolder).bind(expense)
    }

    class ExpenseViewHolder(
        private val binding: ItemExpanseBinding
    ) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener{
                binding.expense?.let { expense ->
                    navigateToExpense(expense, it)
                }
            }
        }

        private fun navigateToExpense(
            expense: ExpanseModelElement,
            view: View
        ) {

            val direction =
                ExpensesFragmentDirections.actionExpensesFragmentToDetailFragment(expenseData = expense)
            view.findNavController().navigate(direction)
        }

        fun bind(item: ExpanseModelElement) {
            binding.apply {
                expense = item
                executePendingBindings()
            }
        }
    }
}

private class ExpenseDiffCallback : DiffUtil.ItemCallback<ExpanseModelElement>() {

    override fun areItemsTheSame(oldItem: ExpanseModelElement, newItem: ExpanseModelElement): Boolean {
        return oldItem.user == newItem.user
    }

    override fun areContentsTheSame(oldItem: ExpanseModelElement, newItem: ExpanseModelElement): Boolean {
        return oldItem == newItem
    }
}
