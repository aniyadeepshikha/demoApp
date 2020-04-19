package com.example.demoapp.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.demoapp.R
import com.example.demoapp.model.CountryDetails
import com.example.demoapp.model.Details
import com.example.demoapp.model.WebServiceRepository
import com.example.demoapp.viewModel.FactsViewModel


class FactsListFragment : Fragment() {

    companion object {
        fun newInstance() = FactsListFragment()
    }

    private lateinit var viewModel: FactsViewModel
    var detailList: ArrayList<Details> = ArrayList()
    var factsAdapter: FactsListAdapter? = null
    var recyclerView: RecyclerView? = null
    var refreshLayout: SwipeRefreshLayout? = null
    var rootView: View? = null
    var tvNoDataAvail: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.facts_list_fragment, container, false)
        initView()
        setupRecyclerView()
        return rootView
    }

    fun initView() {
        recyclerView = rootView?.findViewById(R.id.rv_facts_list)
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        recyclerView!!.setItemAnimator(DefaultItemAnimator())
        tvNoDataAvail = rootView!!.findViewById(R.id.tv_no_data_avail)

        refreshLayout = rootView?.findViewById(R.id.refresh_layout)
        refreshLayout?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            if (isNetworkAvailable(activity!!)) {
                refreshLayout!!.isRefreshing = true
                WebServiceRepository.getInstance()?.getFacts(activity!!)
            } else {
                refreshLayout!!.isRefreshing = false
                Toast.makeText(
                    context,
                    R.string.no_network,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FactsViewModel::class.java)
        viewModel.init(activity!!)

        viewModel.getFact()?.observe(viewLifecycleOwner, Observer(fun(it: CountryDetails?) {
            if (it != null) {
                val details: ArrayList<Details> = it!!.rows
                (activity as AppCompatActivity).supportActionBar?.title = it.title
                detailList.clear()
                detailList.addAll(details)
                refreshLayout?.isRefreshing = false
                if (detailList.size > 0) {
                    tvNoDataAvail!!.visibility = ViewGroup.GONE
                    recyclerView!!.visibility = ViewGroup.VISIBLE
                } else {
                    tvNoDataAvail!!.visibility = ViewGroup.VISIBLE
                    recyclerView!!.visibility = ViewGroup.GONE
                }
            }
            factsAdapter!!.notifyDataSetChanged()
        }))

    }

    private fun setupRecyclerView() {
        if (factsAdapter == null) {
            factsAdapter = FactsListAdapter(activity, detailList)
            recyclerView!!.setAdapter(factsAdapter)
        } else {
            factsAdapter!!.notifyDataSetChanged()
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

}
