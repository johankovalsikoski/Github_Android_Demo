package johan.kovalsikoski.github_android_demo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GithubUserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun githubUserDao(): GithubUserDao

}
