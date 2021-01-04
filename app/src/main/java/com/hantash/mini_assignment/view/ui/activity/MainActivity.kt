package com.hantash.mini_assignment.view.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.hantash.mini_assignment.R
import com.hantash.mini_assignment.view.ui.fragment.MenusFragment
import com.hantash.mini_assignment.view.ui.fragment.CartFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_main)

        //showing menusFragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(container_fragment.id, MenusFragment.newInstance(), "")
        transaction.commit()

        //Showing Cart Fragment
        fab.setOnClickListener{
            if (supportFragmentManager.getBackStackEntryCount() == 0) {
                gotoCartFragment()
                fab.setImageResource(R.drawable.ic_card)
            }
        }
    }

    private fun gotoCartFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter_from_bottom,
            R.anim.exit_to_top,
            R.anim.enter_from_top,
            R.anim.exit_to_bottom
        )
        transaction.replace(container_fragment.id, CartFragment.newInstance(), "CartFragment")
        transaction.addToBackStack("CartFragment")
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        fab.setImageResource(R.drawable.ic_cart)
    }
}