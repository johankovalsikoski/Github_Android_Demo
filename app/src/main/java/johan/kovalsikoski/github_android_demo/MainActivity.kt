package johan.kovalsikoski.github_android_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import johan.kovalsikoski.github_android_demo.presentation.GithubUserScreen
import johan.kovalsikoski.github_android_demo.presentation.GithubUserUiState
import johan.kovalsikoski.github_android_demo.presentation.GithubUserViewModel
import johan.kovalsikoski.github_android_demo.ui.theme.GithubAndroidDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            GithubAndroidDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel: GithubUserViewModel = hiltViewModel()
                    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

                    GithubUserScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = uiState.value
                    ) {
                        viewModel.onEvent(it)
                    }

                }
            }
        }
    }
}
