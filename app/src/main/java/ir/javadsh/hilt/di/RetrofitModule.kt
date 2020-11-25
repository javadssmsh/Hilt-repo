package ir.javadsh.hilt.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ir.javadsh.hilt.model.Blog
import ir.javadsh.hilt.retrofit.BlogNetworkEntity
import ir.javadsh.hilt.retrofit.NetworkMapper
import ir.javadsh.hilt.utils.EntityMapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

/*    @Singleton
    @Provides
    fun providesNetworkMapper(): EntityMapper<BlogNetworkEntity, Blog> {
        return NetworkMapper()
    }*/

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gSon: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://open-api/.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gSon))
    }

    @Singleton
    @Provides
    fun provideBlogRet
}