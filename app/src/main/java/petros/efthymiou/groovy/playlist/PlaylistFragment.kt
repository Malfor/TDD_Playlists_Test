package petros.efthymiou.groovy.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.FragmentItemListBinding

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private val viewModel: PlaylistViewModel by viewModels()

    private lateinit var binding: FragmentItemListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.bind(inflater.inflate(R.layout.fragment_item_list, container, false))

        observeLiveData()

        return binding.root
    }

    private fun observeLiveData() {
        viewModel.loader.observe(viewLifecycleOwner, { loading ->
            when (loading) {
                true -> binding.loader.isVisible = true
                false -> binding.loader.isGone = true
            }
        })

        viewModel.playlists.observe(viewLifecycleOwner, { playlist ->
            if (playlist.getOrNull() != null) {
                playlist.getOrNull()?.let { setupList(it) }
            } else {
                // TODO
            }
        })
    }

    private fun setupList(playlist: List<Playlist>) {
        with(binding.playlistsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlist) { id ->
                val action = PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment(id)
                findNavController().navigate(action)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment().apply { }
    }
}