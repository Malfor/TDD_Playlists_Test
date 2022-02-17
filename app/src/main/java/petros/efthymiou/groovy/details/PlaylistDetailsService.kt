package petros.efthymiou.groovy.details

import java.lang.RuntimeException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistDetailsService @Inject constructor(
    private val api: PlaylistDetailsApi
) {

    suspend fun fetchPlaylistDetails(id: String) : Flow<Result<PlaylistDetails>> {
        return flow {
            emit(Result.success(api.fetchPlaylistDetails(id)))
        }.catch {
            emit(Result.failure(RuntimeException("Damn backend developers again 500!!")))
        }
    }
}
