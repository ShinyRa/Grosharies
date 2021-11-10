package com.example.grosharies.ui.navigation

import com.example.grosharies.R

sealed class Screen(val route: String, val nameResource: Int) {
    companion object {
        // Need to find screen by route for top bar navigation text
        fun findByRoute(route: String): Screen? = Screen::class.sealedSubclasses.map { it.objectInstance as Screen }
                .firstOrNull { it.route == route }
    }

    object Home : Screen("home", R.string.home)
    object Groups : Screen("group", R.string.groups)
    object GroupDetail : Screen("group/view", R.string.group_detail)
    object Lists : Screen("list", R.string.lists)
    object ListEdit : Screen("list/edit", R.string.group_detail)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}