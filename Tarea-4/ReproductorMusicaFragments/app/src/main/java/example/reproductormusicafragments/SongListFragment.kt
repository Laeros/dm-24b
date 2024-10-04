package example.reproductormusicafragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class SongListFragment : Fragment() {

    private lateinit var songListView: ListView
    private val songs = listOf("Dancing with the devil", "Burn the house down", "Carol of the bells", "Colors", "We stay up all night")
    private val songResources = listOf(R.raw.dancing_with_the_devil, R.raw.burn_the_house_down, R.raw.carol_of_the_bells, R.raw.colors, R.raw.we_stay_up_all_night)  // Lista de canciones

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el ListView con la lista de canciones
        songListView = view.findViewById(R.id.song_list_view)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, songs)
        songListView.adapter = adapter

        // Configurar el clic en una canción para ir al fragmento de MusicPlayer
        songListView.setOnItemClickListener { _, _, position, _ ->
            val musicPlayerFragment = MusicPlayerFragment()

            // Pasar el ID de la canción seleccionada al MusicPlayerFragment
            val bundle = Bundle()
            bundle.putInt("selectedSong", songResources[position])
            musicPlayerFragment.arguments = bundle

            // Navegar al MusicPlayerFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, musicPlayerFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
