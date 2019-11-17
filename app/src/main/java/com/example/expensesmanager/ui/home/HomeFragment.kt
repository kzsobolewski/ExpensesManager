package com.example.expensesmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensesmanager.R
import com.example.expensesmanager.db.ExpenseDao
import com.example.expensesmanager.db.ExpenseRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main_page.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var newEntryFAB: FloatingActionButton

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView: RecyclerView = activity!!.findViewById(R.id.recyclerview2)
        val adapter = ExpenseListAdapter(recyclerView.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        val navController = findNavController(activity!!,R.id.main_nav_fragment)
        newEntryFAB.setOnClickListener {
            navController.navigate(R.id.action_bottomNavBarFragment_to_addEntryFragment)
        }

        homeViewModel.allExpenses.observe(this, Observer { expenses ->
            expenses?.let{adapter.setExpenses(it)}
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        newEntryFAB = activity!!.findViewById(R.id.newEntryFab)

        return inflater.inflate(R.layout.fragment_expenses, container, false)
    }

    override fun onPause() {
        super.onPause()
        newEntryFAB?.hide()
    }

    override fun onResume() {
        super.onResume()
        newEntryFAB?.show()
    }

}