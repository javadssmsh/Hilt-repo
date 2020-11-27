package ir.javadsh.hilt.repository

import ir.javadsh.hilt.model.Blog
import ir.javadsh.hilt.retrofit.BlogRetrofit
import ir.javadsh.hilt.retrofit.NetworkMapper
import ir.javadsh.hilt.room.BlogDao
import ir.javadsh.hilt.room.CacheMapper
import ir.javadsh.hilt.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {


    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog: Blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}

