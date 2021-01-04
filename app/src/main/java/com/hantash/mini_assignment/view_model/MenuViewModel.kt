package com.hantash.mini_assignment.view_model

import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.hantash.mini_assignment.BaseApplication
import com.hantash.mini_assignment.model.Menu
import com.hantash.mini_assignment.repository.MenuRepository
import com.hantash.mini_assignment.state.MenuState
import io.reactivex.Observable
import java.util.Collections.copy

class MenuViewModel(
    initialState: MenuState,
    private val repository: MenuRepository
): BaseMvRxViewModel<MenuState>(initialState, debugMode = true) {
    val errorMessage = MutableLiveData<String>()

    companion object : MvRxViewModelFactory<MenuViewModel, MenuState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: MenuState): MenuViewModel? {
            val repository =
                viewModelContext.app<BaseApplication>().menuRepository
            return MenuViewModel(state, repository)
        }
    }

    init {
        // Updating the state
        setState {
            copy(menuList = Loading())
        }

        // Getting menus
        repository.getMenus()
            .execute {
                copy(menuList = it)
            }
    }

    fun addMenu(menuId: Long) {
        withState { state ->
            if (state.menuList is Success) {
                val index = state.menuList.invoke().indexOfFirst {
                    it.id == menuId
                }
                repository.addMenu(menuId)
                    .execute {
                        if (it is Success) {
                            copy(
                                menuList = Success(
                                    state.menuList.invoke().toMutableList().apply {
                                        set(index, it.invoke())
                                    }
                                )
                            )
                        } else if (it is Fail){
                            errorMessage.postValue("Failed to add menu to cart")
                            copy()
                        } else {
                            copy()
                        }
                    }
            }
        }
    }
}