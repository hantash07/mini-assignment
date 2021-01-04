package com.hantash.mini_assignment

import android.app.Application
import com.hantash.mini_assignment.repository.MenuRepository

class BaseApplication: Application() {
    val menuRepository by lazy {
        MenuRepository()
    }
}