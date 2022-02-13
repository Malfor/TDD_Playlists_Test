package petros.efthymiou.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import okhttp3.OkHttpClient
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.FragmentItemListBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistFragment : Fragment() {

    private lateinit var viewModel: PlaylistViewModel
    private lateinit var viewModelFactory: PlaylistViewModelFactory

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://62058486161670001741bc0e.mockapi.io/android-tdd/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(PlaylistApi::class.java)
    private val service = PlaylistService(api)
    private val repository = PlaylistRepository(service)

    private lateinit var binding: FragmentItemListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.bind(inflater.inflate(R.layout.fragment_item_list, container, false))

        setupViewModel()

        viewModel.playlists.observe(viewLifecycleOwner, { playlist ->
            if (playlist.getOrNull() != null) {
                playlist.getOrNull()?.let { setupList(it) }
            } else {
                // TODO
            }
        })

        return binding.root
    }

    private fun setupList(playlist: List<Playlist>) {
        with(binding.playlistsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlist)
        }
    }

    private fun setupViewModel() {
        viewModelFactory = PlaylistViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment().apply { }
    }
}