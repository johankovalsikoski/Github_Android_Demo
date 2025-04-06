package johan.kovalsikoski.github_android_demo.data

import android.util.Log
import johan.kovalsikoski.github_android_demo.domain.GithubUser
import johan.kovalsikoski.github_android_demo.domain.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubUserRepositoryImpl(
    private val api: GithubApi,
    private val dao: GithubUserDao
): GithubUserRepository {

    override fun getUser(username: String): Flow<GithubUser> = flow {
        try {
            val user = api.getUser(username).toDomain()
            dao.insertUser(user.toEntity())
            emit(user)
        } catch (e: Exception) {
            val local = dao.getUser(username)

            if (local != null) {
                emit(local.toDomain())
            } else {
                Log.e("GithubRepositoryImpl", "getUser: ", e)
                throw e
            }

        }
    }

}
