package johan.kovalsikoski.github_android_demo.domain

import kotlinx.coroutines.flow.Flow

class GetGithubUserUseCase(private val repository: GithubUserRepository) {

    operator fun invoke(username: String): Flow<GithubUser> {
        return repository.getUser(username)
    }

}
