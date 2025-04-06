package johan.kovalsikoski.github_android_demo.data

import johan.kovalsikoski.github_android_demo.domain.GithubUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUserDTO(
    val id: Int,
    val login: String,
    @SerialName("avatar_url") val avatarUrl: String,
    val name: String,
    val bio: String?,
    val followers: Int = 0,
) {
    fun toDomain(): GithubUser {
        return GithubUser(
            id = id,
            login = login,
            avatarUrl = avatarUrl,
            name = name,
            bio = bio ?: "",
            followers = followers,
        )
    }
}
