package com.example.grosharies.data.listItem

class ListItemRepository(private val listItemDao: ListItemDao) {

    fun getListItemById(id: Int?): ListItem? {
        return listItemDao.getListItemById(id)
    }

    suspend fun getListItemByListId(listId: Int?): MutableList<ListItem> {
        return listItemDao.getListItemsByListId(listId)
    }

    suspend fun insertListItem(listItem: ListItem) {
        listItemDao.insertListItem(listItem)
    }

    suspend fun updateListItem(listItem: ListItem) {
        listItemDao.updateListItem(listItem)
    }

    suspend fun deleteListItem(listItem: ListItem) {
        listItemDao.deleteListItem(listItem)
    }
}