package example.reproductormusicafragments

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import androidx.fragment.app.Fragment

class MusicPlayerFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var backButton: Button
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_music_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seekBar = view.findViewById(R.id.seek_bar)
        playButton = view.findViewById(R.id.play_button)
        pauseButton = view.findViewById(R.id.pause_button)
        backButton = view.findViewById(R.id.back_button)

        val songId = arguments?.getInt("selectedSong") ?: 0
        mediaPlayer = MediaPlayer.create(requireContext(), songId)

        playButton.setOnClickListener {
            mediaPlayer.start()
            updateSeekBar()
        }

        pauseButton.setOnClickListener {
            mediaPlayer.pause()
        }

        backButton.setOnClickListener {
            // Reemplazar el fragmento actual con la lista de canciones
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SongListFragment())
                .addToBackStack(null)
                .commit()
        }

        mediaPlayer.setOnCompletionListener {
            seekBar.progress = 0
        }

        seekBar.max = mediaPlayer.duration
    }

    private fun updateSeekBar() {
        seekBar.progress = mediaPlayer.currentPosition
        if (mediaPlayer.isPlaying) {
            handler.postDelayed({ updateSeekBar() }, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        handler.removeCallbacksAndMessages(null)
    }
}
