package com.hantash.mini_assignment.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.hantash.mini_assignment.R
import com.hantash.mini_assignment.model.Menu
import com.hantash.mini_assignment.view.adapter.DealPagerAdapter
import com.hantash.mini_assignment.view.adapter.MenuAdapter
import com.hantash.mini_assignment.view_model.MenuViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_menus.*
import kotlinx.android.synthetic.main.layout_menus.*

class MenusFragment : BaseMvRxFragment() {
    private val viewModel: MenuViewModel by activityViewModel()
    private var menuAdapter: MenuAdapter? = null
    private var menuList: List<Menu> = ArrayList<Menu>()

    companion object {
        @JvmStatic
        fun newInstance() =
            MenusFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            when (state.menuList) {
                is Loading -> {}
                is Success -> {
                    menuAdapter?.updateMenuList(state.menuList.invoke())
                }
                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        "Failed to load menus",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun setupUI() {
        //Setup viewPager for showing Deals
        val deals = arrayOf(R.drawable.deal_01, R.drawable.deal_02, R.drawable.deal_03)
        view_pager.adapter = DealPagerAdapter(deals)

        //Attaching page indicator with viewPager
        page_indicator.attachToPager(view_pager)

        //Setup menu adapter
        menuAdapter = MenuAdapter(menuList, object : MenuAdapter.OnAddToCart {
            override fun add(id: Long, position: Int) {
                viewModel.addMenu(id)
                menuAdapter?.updateMenu(position)
            }
        })
        rv_menus.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = menuAdapter
        }
    }

}