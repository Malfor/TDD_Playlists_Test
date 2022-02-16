package petros.efthymiou.groovy.playlist

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(ViewModelComponent::class)
class PlaylistModule {

    @Provides
    fun playlistApi(retrofit: Retrofit): PlaylistApi = retrofit.create(PlaylistApi::class.java)

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://62058486161670001741bc0e.mockapi.io/android-tdd/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}