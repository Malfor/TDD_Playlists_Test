package petros.efthymiou.groovy.playlist

data class PlaylistResponse(
    val id: String,
    val name: String,
    val category: String,
    val image: Int
)

fun List<PlaylistResponse>.mapToDomain(): List<Playlist> {
    return with(this) {
        map { playlistResponse ->
            Playlist(
                id = playlistResponse.id,
                name = playlistResponse.name,
                category = playlistResponse.category,
                image = playlistResponse.image
            )
        }
    }
}

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int
)
