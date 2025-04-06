package johan.kovalsikoski.github_android_demo.domain

data class GithubUser(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val name: String,
    val bio: String = "",
    val followers: Int,
)
