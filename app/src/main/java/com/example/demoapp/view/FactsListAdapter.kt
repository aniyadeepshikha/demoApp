package com.example.demoapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.demoapp.R
import com.example.demoapp.model.Details


class FactsListAdapter(var context: Context?, var factsList: ArrayList<Details>?) :
    RecyclerView.Adapter<FactsListAdapter.FactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.fact_list_item, parent, false)
        return FactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return factsList!!.size
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.ic_launcher)
        requestOptions.error(R.mipmap.ic_launcher)

        holder.tvTitle.setText(factsList!!.get(position).title);
        holder.tvDescription.setText(factsList!!.get(position).description);
        Glide.with(context!!)
            .setDefaultRequestOptions(requestOptions)
            .load(factsList!!.get(position).imageHref)
            .into(holder.ivImage);
    }


    class FactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvDescription: TextView
        var ivImage: ImageView

        init {
            tvTitle = itemView.findViewById(R.id.tvName)
            tvDescription = itemView.findViewById(R.id.tvDesCription)
            ivImage = itemView.findViewById(R.id.ivImage)
        }
    }

}