package com.oussama.stackoverflow_app_example.app.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oussama.entities.State
import com.oussama.entities.User
import com.oussama.stackoverflow_app_example.R
import com.oussama.stackoverflow_app_example.app.features.detail.DetailFragment


class UsersFragment : Fragment(R.layout.fragment_users) {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var userAdapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intState(view)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)
        userAdapter = UsersAdapter({ user -> clickListener(user) }, { homeViewModel.retry() })
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = userAdapter

        homeViewModel.users.observe(viewLifecycleOwner, {
            userAdapter.submitList(it)
        })

    }

    private fun intState(view: View) {
        val errorTextView = view.findViewById<TextView>(R.id.txtError)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        errorTextView.setOnClickListener {
            homeViewModel.retry()
        }

        homeViewModel.getState().observe(viewLifecycleOwner, {
            progressBar.visibility =
                if (homeViewModel.isListEmpty() && it == State.LOADING) View.VISIBLE else View.GONE
            errorTextView.visibility =
                if (homeViewModel.isListEmpty() && it == State.ERROR) View.VISIBLE else View.GONE
            if (!homeViewModel.isListEmpty()) {
                userAdapter.setState(it ?: State.DONE)
            }
        })

    }

    private fun clickListener(user: User?) {
        val detailsFragment = DetailFragment.newInstance(user)
        (activity as HomeActivity).addDetailsFragmentWithTransition(detailsFragment)
    }

}