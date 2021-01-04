package com.hantash.mini_assignment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hantash.mini_assignment.R
import com.hantash.mini_assignment.databinding.SingleViewMenuBinding
import com.hantash.mini_assignment.model.Menu

class MenuAdapter(
    var menuList: List<Menu>,
    val addCartLister: OnAddToCart
): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private var context: Context? = null

    fun updateMenuList(menuList: List<Menu>){
        this.menuList = menuList
        notifyDataSetChanged()
    }

    fun updateMenu(position: Int){
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        if (context == null) context = parent.context
        val singleView: SingleViewMenuBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.single_view_menu, parent, false)
        return MenuViewHolder(singleView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        holder.singleView.ivMenu.setImageResource(menu.image)
        holder.singleView.tvName.text = menu.name
        holder.singleView.tvPrice.text = "${menu.price} usd"

        var drawableBg = R.drawable.style_bg_price
        if (menu.isAdded) {
            drawableBg = R.drawable.style_bg_added_menu
            holder.singleView.tvPrice.text = "Added + 1"
        }

        holder.singleView.tvPrice.background =
            context?.let { ContextCompat.getDrawable(it, drawableBg) }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class MenuViewHolder(
        val singleView: SingleViewMenuBinding
    ): RecyclerView.ViewHolder(singleView.root), View.OnClickListener {
        init {
            singleView.tvPrice.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                addCartLister.add(menuList[adapterPosition].id, adapterPosition)
            }
        }
    }

    interface OnAddToCart {
        fun add(id: Long, position: Int)
    }
}