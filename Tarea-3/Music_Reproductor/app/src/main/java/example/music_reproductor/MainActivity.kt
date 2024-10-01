package example.music_reproductor

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referenciar el Spinner y el botón
        val spinnerSongs = findViewById<Spinner>(R.id.spinner_songs)
        val buttonSelect = findViewById<Button>(R.id.button_select)

        // Lista de canciones (nombres)
        val songs = arrayOf("song1", "song2", "song3", "song4", "song5")

        // Adaptador para mostrar los nombres de las canciones en el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, songs)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSongs.adapter = adapter

        // Lógica para el botón seleccionar
        buttonSelect.setOnClickListener {
            val selectedSong = spinnerSongs.selectedItem.toString()

            // Iniciar la actividad de reproducción y pasar la canción seleccionada
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("SELECTED_SONG", selectedSong)
            startActivity(intent)
        }
    }
}
