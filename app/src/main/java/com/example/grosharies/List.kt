package com.example.grosharies

data class List(
    val listName: String,
    val lastEdited: Long,
    val createdBy: String,
    val listItems: ArrayList<ListItems>,
) {
}