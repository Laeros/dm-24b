package example.music_reproductor

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlayerActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        // Referencias a los elementos de la interfaz
        val songNameTextView = findViewById<TextView>(R.id.textView_song_name)
        val playButton = findViewById<Button>(R.id.button_play)
        val pauseButton = findViewById<Button>(R.id.button_pause)
        val stopButton = findViewById<Button>(R.id.button_stop)
        val backButton = findViewById<Button>(R.id.button_back)
        val songImageView = findViewById<ImageView>(R.id.imageView_song)

        // Obtener la canción seleccionada de la actividad anterior
        val selectedSong = intent.getStringExtra("SELECTED_SONG")

        // Mostrar el nombre de la canción seleccionada
        songNameTextView.text = selectedSong

        // Seleccionar la imagen asociada a la canción (opcional, puedes personalizarlo)
        songImageView.setImageResource(R.drawable.icon_music)

        // Seleccionar el archivo de audio según la canción seleccionada
        val songResId = when (selectedSong) {
            "song1" -> R.raw.Burn_the_house_down
            "song2" -> R.raw.Colors
            "song3" -> R.raw.Carol_of_the_bells
            "song4" -> R.raw.Dancing_with_the_devil
            "song5" -> R.raw.we_stay_up_all_night
            else -> R.raw.Burn_the_house_down
        }

        // Inicializar MediaPlayer
        mediaPlayer = MediaPlayer.create(this, songResId)

        // Configurar el botón de reproducción
        playButton.setOnClickListener {
            mediaPlayer?.start()
        }

        // Configurar el botón de pausa
        pauseButton.setOnClickListener {
            mediaPlayer?.pause()
        }

        // Configurar el botón de detener
        stopButton.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.prepare() // Debe prepararse después de detenerse
        }

        // Botón para volver a la actividad anterior
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
