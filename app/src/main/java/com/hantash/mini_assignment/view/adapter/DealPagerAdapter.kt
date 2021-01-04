package com.hantash.mini_assignment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hantash.mini_assignment.R
import com.hantash.mini_assignment.databinding.SingleViewDealBinding

class DealPagerAdapter(
    val deals: Array<Int>
): RecyclerView.Adapter<DealPagerAdapter.DealViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
        if (context == null) context = parent.context

        val singleView: SingleViewDealBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.single_view_deal, parent, false)
        return DealViewHolder(singleView)
    }

    override fun onBindViewHolder(holder: DealViewHolder, position: Int) {
        holder.singleView.imageView.setImageResource(deals[position])
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    inner class DealViewHolder(
        val singleView: SingleViewDealBinding
    ): RecyclerView.ViewHolder(singleView.root)
}