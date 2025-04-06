package johan.kovalsikoski.github_android_demo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import johan.kovalsikoski.github_android_demo.domain.GetGithubUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserViewModel @Inject constructor(
    private val getGithubUserUseCase: GetGithubUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<GithubUserUiState>(GithubUserUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: GithubUserScreenUiEvent) {
        when (event) {
            is GithubUserScreenUiEvent.Search -> {
                searchUser(event.username)
            }
        }
    }

    private fun searchUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = GithubUserUiState.Loading
            getGithubUserUseCase(username)
                .catch {
                    _uiState.value = GithubUserUiState.Error(it.message ?: "Unknown error")
                }
                .collect { user ->
                    _uiState.value = GithubUserUiState.Success(user)
                }
        }
    }

}
