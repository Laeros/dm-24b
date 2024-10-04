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
    private var isPlaying = false // Estado para saber si hay una canción sonando

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

        // Obtener el ID de la canción seleccionada a través de argumentos
        val songId = arguments?.getInt("selectedSong") ?: 0

        // Inicializar el MediaPlayer
        mediaPlayer = MediaPlayer.create(requireContext(), songId)

        // Configurar los botones de control
        playButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause() // Pausa si ya está sonando
            } else {
                if (!isPlaying) {
                    mediaPlayer.seekTo(0) // Reinicia la canción si no estaba sonando
                }
                startMusic()
            }
        }

        pauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlaying = false
            }
        }

        // Volver a la lista de canciones
        backButton.setOnClickListener {
            stopMusic() // Detener la música antes de regresar
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SongListFragment())
                .addToBackStack(null)
                .commit()
        }

        mediaPlayer.setOnCompletionListener {
            seekBar.progress = 0
            isPlaying = false
        }

        seekBar.max = mediaPlayer.duration
    }

    // Iniciar la música y actualizar la barra de progreso
    private fun startMusic() {
        mediaPlayer.start()
        isPlaying = true
        updateSeekBar()
    }

    // Detener la música si ya está sonando
    private fun stopMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset() // Reiniciar el MediaPlayer para cargar una nueva canción
        }
        isPlaying = false
    }

    // Actualizar la barra de progreso
    private fun updateSeekBar() {
        seekBar.progress = mediaPlayer.currentPosition
        if (mediaPlayer.isPlaying) {
            handler.postDelayed({ updateSeekBar() }, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Liberar el MediaPlayer para evitar fugas
        handler.removeCallbacksAndMessages(null)
    }
}
