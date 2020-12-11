package com.oussama.stackoverflow_app_example.app.features.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oussama.entities.State
import com.oussama.entities.User
import com.oussama.stackoverflow_app_example.R

private const val ARG_USER = "user_param"

class DetailFragment : Fragment() {

    private var user: User? = null
    private val detailViewModel: DetailViewModel by viewModels()

    private  val detailAdapter by lazy {
        DetailAdapter{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(ARG_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(user: User?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, user)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user?.let {
            detailViewModel.addItem(it)
            detailViewModel.addItem(State.LOADING)
            detailViewModel.getDetails(it.userId)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = detailAdapter

        detailViewModel.details.observe(viewLifecycleOwner, {
            detailAdapter.setList(it)
        })
    }
}