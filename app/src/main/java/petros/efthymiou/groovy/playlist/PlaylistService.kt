package petros.efthymiou.groovy.playlist

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistService @Inject constructor(
    private val api: PlaylistApi
) {

    suspend fun fetchPlaylists() : Flow<Result<List<Playlist>>> {
        return flow {
            emit(Result.success(api.fetchAllPlaylists().mapToDomain()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}
