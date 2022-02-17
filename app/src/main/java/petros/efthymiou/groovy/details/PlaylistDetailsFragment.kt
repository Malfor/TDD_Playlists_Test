package petros.efthymiou.groovy.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.FragmentPlaylistDetailBinding

@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    private val viewModel: PlaylistDetailsViewModel by viewModels()

    private val args: PlaylistDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentPlaylistDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistDetailBinding.bind(
            inflater.inflate(R.layout.fragment_playlist_detail, container, false)
        )

        observeLiveData()

        return binding.root
    }

    private fun observeLiveData() {
        viewModel.playlistDetail(args.playlistId).observe(viewLifecycleOwner, { playlistDetails ->
            if (playlistDetails.getOrNull() != null) {
                setupPlaylistDetails(playlistDetails.getOrNull())
            } else {
                // TODO
            }
        })

        viewModel.loader.observe(viewLifecycleOwner, { loading ->
            when (loading) {
                true -> binding.loader.isVisible = true
                false -> binding.loader.isGone = true
            }
        })
    }

    private fun setupPlaylistDetails(details: PlaylistDetails?) {
        with(binding) {
            playlistName.text = details?.name
            playlistDetails.text = details?.details
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistDetailsFragment()
    }
}