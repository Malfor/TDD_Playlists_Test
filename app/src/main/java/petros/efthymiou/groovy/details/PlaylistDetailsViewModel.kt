package petros.efthymiou.groovy.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class PlaylistDetailsViewModel @Inject constructor(
    private val service: PlaylistDetailsService
) : ViewModel() {
    val loader: MutableLiveData<Boolean> = MutableLiveData()

    fun playlistDetail(id: String) = liveData {
        loader.postValue(true)
        emitSource(
            service.fetchPlaylistDetails(id)
                .onEach {
                    loader.postValue(false)
                }.asLiveData()
        )
    }
}
