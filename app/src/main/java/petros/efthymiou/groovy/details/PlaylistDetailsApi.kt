package petros.efthymiou.groovy.details

import retrofit2.http.GET
import retrofit2.http.Path

interface PlaylistDetailsApi {

    @GET("playlist-detail/{id}")
    suspend fun fetchPlaylistDetails(@Path("id") id: String) : PlaylistDetails
}
