package com.hantash.mini_assignment.repository

import com.hantash.mini_assignment.R
import com.hantash.mini_assignment.model.Menu
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MenuRepository {
    private val menuList = mutableListOf<Menu>()

    //Method to get all menus available
    fun getMenus() = Observable.fromCallable<List<Menu>> {
        //Thread.sleep(3000)
        menuList.addAll(listOf(
            Menu(
                1000,
                200,
                "Menu 1",
                "Descriptions",
                40,
                R.drawable.menu_4,
                false
            ),
            Menu(
                1001,
                201,
                "Menu 2",
                "Descriptions",
                50,
                R.drawable.menu_2,
                false
            ),
            Menu(
                1002,
                202,
                "Menu 3",
                "Descriptions",
                60,
                R.drawable.menu_3,
                false
            ),
            Menu(
                1003,
                203,
                "Menu 4",
                "Descriptions",
                70,
                R.drawable.menu_4,
                false
            ),
            Menu(
                1004,
                204,
                "Menu 5",
                "Descriptions",
                80,
                R.drawable.menu_5,
                false
            ),
            Menu(
                1005,
                205,
                "Menu 6",
                "Descriptions",
                90,
                R.drawable.menu_4,
                false
            ),
            Menu(
                1006,
                206,
                "Menu 7",
                "Descriptions",
                100,
                R.drawable.menu_3,
                false
            ),
            Menu(
                1007,
                207,
                "Menu 8",
                "Descriptions",
                110,
                R.drawable.menu_5,
                false
            ),
            Menu(
                1008,
                208,
                "Menu 9",
                "Descriptions",
                120,
                R.drawable.menu_2,
                false
            ),
            Menu(
                1009,
                209,
                "Menu 10",
                "Descriptions",
                130,
                R.drawable.menu_4,
                false
            )
        ))
        menuList

    }.subscribeOn(Schedulers.io())

    //Method to add menu to Cart
    fun addMenu(menuId: Long): Observable<Menu> {
        return Observable.fromCallable {
            val menu = menuList.first { menu -> menu.id == menuId }
            menu.copy(isAdded = true)
        }
    }
}