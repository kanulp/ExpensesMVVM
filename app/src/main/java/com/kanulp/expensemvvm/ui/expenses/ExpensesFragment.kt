package com.kanulp.expensemvvm.ui.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kanulp.expensemvvm.adapters.ExpenseAdapter
import com.kanulp.expensemvvm.R
import com.kanulp.expensemvvm.databinding.FragmentExpansesBinding
import com.kanulp.expensemvvm.util.Resource
import com.kanulp.expensemvvm.viewmodels.ExpenseViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpansesBinding? = null
    private val binding get() = _binding!!

    private val expenseViewModel by viewModels<ExpenseViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExpansesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ExpenseAdapter()
        binding.recyclerView.adapter  = adapter
        subscribeUi(adapter)

    }

    private fun subscribeUi(adapter: ExpenseAdapter) {

        expenseViewModel.expenseList.observe(viewLifecycleOwner) { expanses ->
            when(expanses.status){
                Resource.Status.SUCCESS-> {
                    adapter.submitList(expanses.data)
                    expenseViewModel.sum.observe(viewLifecycleOwner){
                        binding.tvTotal.text ="Total : $$it"
                    }
                }
                Resource.Status.ERROR->binding.tvTotal.text=getString(R.string.no_data)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}