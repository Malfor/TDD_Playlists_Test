package petros.efthymiou.groovy.playlist

import retrofit2.http.GET

interface PlaylistApi {

    @GET("playlist")
    suspend fun fetchAllPlaylists() : List<PlaylistRaw>
}
