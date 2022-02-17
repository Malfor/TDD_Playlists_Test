package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.R

data class PlaylistRaw(
    val id: String?,
    val name: String?,
    val category: String?,
    val image: Int?
)

fun PlaylistRaw.mapToDomain() : Playlist {
    return with(this) {
        val image = if (category != null && category == "Rap") R.mipmap.rock else R.mipmap.playlist
        Playlist(
            id = id.orEmpty(),
            name = name.orEmpty(),
            category = category.orEmpty(),
            image = image
        )
    }
}