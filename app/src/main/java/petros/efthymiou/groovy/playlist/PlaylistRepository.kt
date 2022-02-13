package petros.efthymiou.groovy.playlist

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PlaylistRepository @Inject constructor(
    private val service: PlaylistService
) {
    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> = service.fetchPlaylists()
}
