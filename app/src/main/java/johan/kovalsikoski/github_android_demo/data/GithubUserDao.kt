package johan.kovalsikoski.github_android_demo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GithubUserDao {
    @Query("SELECT * FROM users WHERE login = :username")
    suspend fun getUser(username: String): GithubUserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: GithubUserEntity)
}
