package com.example.grosharies.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grosharies.data.GroceryList.GroceryList
import com.example.grosharies.data.GroceryList.GroceryListDao
import com.example.grosharies.data.Group.Group
import com.example.grosharies.data.Group.GroupDao
import com.example.grosharies.data.ListItem.ListItem
import com.example.grosharies.data.ListItem.ListItemDao

@Database(
    entities = [Group::class, ListItem::class, GroceryList::class],
    version = 1,
    exportSchema = false
)
abstract class GroshariesRoomDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun groceryListDao(): GroceryListDao
    abstract fun listItemDao(): ListItemDao

    companion object {
        private const val DATABASE_NAME = "GROSHARIES_DATABASE"

        @Volatile
        private var INSTANCE: GroshariesRoomDatabase? = null

        fun getDatabase(context: Context): GroshariesRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(GroshariesRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GroshariesRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}