package ir.javadsh.hilt.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase : RoomDatabase() {

    companion object {
        var DATA_BASE_NAME: String = "mydb";
    }

    abstract fun blogDao(): BlogDao
}