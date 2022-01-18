package com.example.grosharies.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home/")
    object Groups : Screen("group/overview")
    object GroupNew : Screen("group/new")
    object GroupEdit : Screen("group/edit")
    object GroupDetail : Screen("group/view")
    object Lists : Screen("list/overview")
    object ListEdit : Screen("list/edit")
    object ListNew : Screen("list/new")
    object ListDetail : Screen("list/view")
    object ListItemNew : Screen("list/newItem")
    object StartShopping : Screen("list/shop")
    object GroupName : Screen("group/name")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}