package com.hantash.mini_assignment.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.hantash.mini_assignment.R
import com.hantash.mini_assignment.model.Menu
import com.hantash.mini_assignment.view.adapter.CartAdapter
import com.hantash.mini_assignment.view.adapter.MenuAdapter
import com.hantash.mini_assignment.view_model.MenuViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : BaseMvRxFragment() {
    private val viewModel: MenuViewModel by activityViewModel()
    private var cartAdapter: CartAdapter? = null
    private var menuList: List<Menu> = ArrayList<Menu>()

    companion object {
        @JvmStatic
        fun newInstance() =
            CartFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Setup cartAdapter
        cartAdapter = CartAdapter(menuList)
        rv_cart.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = cartAdapter
        }

        iv_back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            when (state.menuList) {
                is Loading -> {}
                is Success -> {
                    menuList = state.menuList.invoke().filter {
                        it.isAdded
                    }
                    cartAdapter?.updateCartList(menuList)
                    updateTotalPrice()
                }
                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        "Failed to load cart",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun updateTotalPrice() {
        var total = 0
        for (menu in menuList) {
            total += menu.price
        }

        tv_price.text = "$total usd"
    }
}