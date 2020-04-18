package com.example.demoapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.model.CountryDetails
import com.example.demoapp.model.Details
import com.example.demoapp.viewModel.FactsViewModel


class FactsListFragment : Fragment() {

    companion object {
        fun newInstance() = FactsListFragment()
    }

    private lateinit var viewModel: FactsViewModel
    var detailList: ArrayList<Details> = ArrayList()
    var factsAdapter: FactsListAdapter? = null
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.facts_list_fragment, container, false)
        recyclerView = view.findViewById(R.id.rv_facts_list)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FactsViewModel::class.java)
        viewModel.init()
        viewModel.getFact()?.observe(viewLifecycleOwner, Observer(fun(it: CountryDetails?) {
            val details: ArrayList<Details> = it!!.rows
            detailList.addAll(details)
            factsAdapter!!.notifyDataSetChanged()
        }))
    setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (factsAdapter == null) {
            factsAdapter = FactsListAdapter(activity, detailList)
            recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
            recyclerView!!.setAdapter(factsAdapter)
            recyclerView!!.setItemAnimator(DefaultItemAnimator())
            recyclerView!!.setNestedScrollingEnabled(true)
        } else {
            factsAdapter!!.notifyDataSetChanged()
        }
    }

}
