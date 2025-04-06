package johan.kovalsikoski.github_android_demo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import johan.kovalsikoski.github_android_demo.domain.GithubUser
import johan.kovalsikoski.github_android_demo.ui.theme.GithubAndroidDemoTheme

@Composable
fun GithubUserScreen(
    modifier: Modifier = Modifier,
    uiState: GithubUserUiState,
    onEvent: (GithubUserScreenUiEvent) -> Unit
) {
    var user by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = user,
            onValueChange = {
                user = it
            },
            label = { Text("Type an Github username") },
            singleLine = true,
            maxLines = 1,
            isError = uiState is GithubUserUiState.Error,
            supportingText = {
                if (uiState is GithubUserUiState.Error) {
                    Text(uiState.message)
                }
            },
            enabled = uiState !is GithubUserUiState.Loading

        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onEvent(GithubUserScreenUiEvent.Search(user))
            },
            enabled = user.isNotBlank() && uiState !is GithubUserUiState.Loading
        ) {
            if (uiState is GithubUserUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            } else {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState is GithubUserUiState.Success) {
            GithubUserCard(uiState.user)
        }
    }
}

@Composable
private fun GithubUserCard(user: GithubUser) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val isPreview = LocalInspectionMode.current
                if (isPreview) {
                    Box(
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                } else {
                    AsyncImage(
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape),
                        model = user.avatarUrl,
                        contentDescription = "${user.name} avatar"
                    )
                }

                Column {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(user.name, style = MaterialTheme.typography.titleMedium)

                    Text(user.login, style = MaterialTheme.typography.bodyMedium)

                    Text(user.bio, style = MaterialTheme.typography.bodySmall)

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun GithubUserCardPreview() {
    GithubUserCard(
        GithubUser(
            name = "Johan V. Kovalsikoski",
            avatarUrl = "https://avatars.githubusercontent.com/u/12563061?v=4",
            id = 0,
            login = "johankovalsikoski",
            bio = "Mobile App Developer",
            followers = 999,
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun GithubUserScreenPreview() {
    GithubAndroidDemoTheme {
        GithubUserScreen(
            uiState = GithubUserUiState.Success(
                GithubUser(
                    name = "Johan V. Kovalsikoski",
                    avatarUrl = "https://avatars.githubusercontent.com/u/12563061?v=4",
                    id = 0,
                    login = "johankovalsikoski",
                    bio = "Mobile App Developer",
                    followers = 999,
                )
            ),
            onEvent = {}
        )
    }

}