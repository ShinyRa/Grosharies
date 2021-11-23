package com.example.grosharies.ui.navigation

import com.example.grosharies.R

sealed class Screen(val route: String, val nameResource: Int) {
    companion object {
        // Need to find screen by route for top bar navigation text
        fun findByRoute(route: String): Screen? =
            Screen::class.sealedSubclasses.map { it.objectInstance as Screen }
                .firstOrNull { it.route == route }
    }

    object Home : Screen("home", R.string.home)
    object Groups : Screen("group/overview", R.string.groups)
    object GroupNew : Screen("group/new", R.string.group_new)
    object GroupEdit : Screen("group/edit", R.string.group_edit)
    object GroupDetail : Screen("group/view", R.string.group_detail)
    object Lists : Screen("list/overview", R.string.lists)
    object ListEdit : Screen("list/edit", R.string.lists)
    object StartShopping : Screen("list/shop", R.string.group_detail)
    object GroupName : Screen("group/name", R.string.group_name)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}