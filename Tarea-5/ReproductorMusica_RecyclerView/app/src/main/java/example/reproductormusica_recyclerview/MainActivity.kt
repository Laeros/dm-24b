package example.reproductormusica_recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

/**
 * Descripción: MainActivity para manejar la inicialización de los fragments del reproductor de música.
 * Autor: Rodrigo Ancori
 * Fecha creación: [03-10-2024]
 * Fecha última modificación: [04-10-2024]
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Establece el layout de la actividad

        // Inicia el fragmento que contiene la lista de audios
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AudioListFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
        }
    }

    // Cambiar a MusicPlayerFragment cuando se selecciona un audio
    fun openMusicPlayerFragment(audio: Audio) {
        val fragment = MusicPlayerFragment.newInstance(audio)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Permitir regresar a la lista
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}
