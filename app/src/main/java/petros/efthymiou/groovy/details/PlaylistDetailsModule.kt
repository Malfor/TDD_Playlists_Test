package petros.efthymiou.groovy.details

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class PlaylistDetailsModule {

    @Provides
    fun playlistDetailsApi(retrofit: Retrofit) = retrofit.create(PlaylistDetailsApi::class.java)
}