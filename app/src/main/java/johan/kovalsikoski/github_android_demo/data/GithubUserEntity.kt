package johan.kovalsikoski.github_android_demo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import johan.kovalsikoski.github_android_demo.domain.GithubUser

@Entity(tableName = "users")
data class GithubUserEntity(
    @PrimaryKey val id: Int,
    val login: String,
    val avatarUrl: String,
    val name: String,
    val bio: String,
    val followers: Int,
)

fun GithubUserEntity.toDomain(): GithubUser {
    return GithubUser(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        name = name,
        bio = bio,
        followers = followers,
    )
}

fun GithubUser.toEntity(): GithubUserEntity {
    return GithubUserEntity(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        name = name,
        bio = bio,
        followers = followers,
    )
}