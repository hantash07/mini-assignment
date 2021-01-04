package com.hantash.mini_assignment.state

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.hantash.mini_assignment.model.Menu

data class MenuState(
    val menuList: Async<List<Menu>> = Uninitialized
): MvRxState