package ir.javadsh.hilt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ir.javadsh.hilt.repository.MainRepository
import ir.javadsh.hilt.retrofit.BlogRetrofit
import ir.javadsh.hilt.retrofit.NetworkMapper
import ir.javadsh.hilt.room.BlogDao
import ir.javadsh.hilt.room.CacheMapper
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}