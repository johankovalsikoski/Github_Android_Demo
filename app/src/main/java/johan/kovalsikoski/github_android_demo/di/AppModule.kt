package johan.kovalsikoski.github_android_demo.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import johan.kovalsikoski.github_android_demo.data.AppDatabase
import johan.kovalsikoski.github_android_demo.data.GithubApi
import johan.kovalsikoski.github_android_demo.data.GithubUserRepositoryImpl
import johan.kovalsikoski.github_android_demo.data.GithubUserDao
import johan.kovalsikoski.github_android_demo.domain.GetGithubUserUseCase
import johan.kovalsikoski.github_android_demo.domain.GithubUserRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGithubApi(): GithubApi {
        val json = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
    }

    @Provides
    fun provideGithubUserDao(database: AppDatabase): GithubUserDao {
        return database.githubUserDao()
    }

    @Provides
    fun provideGithubRepository(githubApi: GithubApi, githubUserDao: GithubUserDao): GithubUserRepository {
        return GithubUserRepositoryImpl(githubApi, githubUserDao)
    }

    @Provides
    fun provideGetGithubUserUseCase(githubUserRepository: GithubUserRepository): GetGithubUserUseCase {
        return GetGithubUserUseCase(githubUserRepository)
    }
}
