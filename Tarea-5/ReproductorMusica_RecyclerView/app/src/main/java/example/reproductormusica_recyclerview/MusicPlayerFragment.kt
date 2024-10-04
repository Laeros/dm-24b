package example.reproductormusica_recyclerview

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Fragmento que reproduce el audio seleccionado.
 * Autor: Rodrigo Ancori
 * Fecha creación: 03-10-2024
 * Fecha última modificación: 04-10-2024
 */
class MusicPlayerFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer
    private var audioName: String? = null
    private var audioImageResId: Int = 0
    private var audioResId: Int = 0

    companion object {
        fun newInstance(audio: Audio): MusicPlayerFragment {
            val fragment = MusicPlayerFragment()
            val args = Bundle()
            args.putString("audioName", audio.name)
            args.putInt("audioImageResId", audio.imageResId)
            args.putInt("audioResId", audio.audioResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_music_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recuperar datos del Bundle
        audioName = arguments?.getString("audioName")
        audioImageResId = arguments?.getInt("audioImageResId") ?: 0
        audioResId = arguments?.getInt("audioResId") ?: 0

        val audioNameTextView = view.findViewById<TextView>(R.id.audio_name)
        val audioImageView = view.findViewById<ImageView>(R.id.audio_image)
        val playButton = view.findViewById<Button>(R.id.play_button)
        val pauseButton = view.findViewById<Button>(R.id.pause_button)
        val stopButton = view.findViewById<Button>(R.id.stop_button)

        audioNameTextView.text = audioName
        audioImageView.setImageResource(audioImageResId)

        mediaPlayer = MediaPlayer.create(context, audioResId)

        playButton.setOnClickListener {
            mediaPlayer.start()
        }

        pauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }

        stopButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer = MediaPlayer.create(context, audioResId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
