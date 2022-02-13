package petros.efthymiou.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.FragmentItemListBinding

class PlaylistFragment : Fragment() {

    private lateinit var viewModel: PlaylistViewModel
    private lateinit var viewModelFactory: PlaylistViewModelFactory

    private val service = PlaylistService(object : PlaylistApi{})
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