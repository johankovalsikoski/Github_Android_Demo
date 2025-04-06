package johan.kovalsikoski.github_android_demo.domain

import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {
    fun getUser(username: String): Flow<GithubUser>
}
