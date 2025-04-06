package johan.kovalsikoski.github_android_demo.presentation

sealed interface GithubUserScreenUiEvent {
    data class Search(val username: String) : GithubUserScreenUiEvent
}
