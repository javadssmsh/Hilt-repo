package ir.javadsh.hilt.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.javadsh.hilt.room.BlogDao
import ir.javadsh.hilt.room.BlogDatabase
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATA_BASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDao(blogDatabase: BlogDatabase):BlogDao{
        return blogDatabase.blogDao()
    }
}