package johan.kovalsikoski.github_android_demo.presentation

import johan.kovalsikoski.github_android_demo.domain.GithubUser

sealed class GithubUserUiState {
    data object Idle: GithubUserUiState()
    data object Loading : GithubUserUiState()
    data class Success(val user: GithubUser) : GithubUserUiState()
    data class Error(val message: String) : GithubUserUiState()
}
