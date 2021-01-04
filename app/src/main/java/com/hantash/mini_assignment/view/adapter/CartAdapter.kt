package com.hantash.mini_assignment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hantash.mini_assignment.R
import com.hantash.mini_assignment.databinding.SingleViewCartBinding
import com.hantash.mini_assignment.model.Menu

class CartAdapter(
    var menuList: List<Menu>
): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var context: Context? = null

    fun updateCartList(menuList: List<Menu>){
        this.menuList = menuList
        notifyDataSetChanged()
    }

    fun updateCart(position: Int){
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        if (context == null) context = parent.context
        val singleView: SingleViewCartBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.single_view_cart, parent, false)
        return CartViewHolder(singleView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val menu = menuList[position]

        holder.singleView.ivMenu.setImageResource(menu.image)
        holder.singleView.tvName.text = menu.name
        holder.singleView.tvPrice.text = "${menu.price} usd"
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class CartViewHolder(
        val singleView: SingleViewCartBinding): RecyclerView.ViewHolder(singleView.root
    )
}