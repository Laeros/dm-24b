package example.reproductormusica_recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Fragmento que muestra la lista de audios.
 * Autor: Rodrigo Ancori
 * Fecha creación: 03-10-2024
 * Fecha última modificación: 04-10-2024
 */
class AudioListFragment : Fragment() {

    private lateinit var audioList: List<Audio>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_audio_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_audio)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Cargar la lista de audios
        audioList = listOf(
            Audio("Carol of the bells", R.drawable.carol_of_the_bells, "3:06", R.raw.carol_of_the_bells),
            Audio("Colors", R.drawable.colors, "3:38", R.raw.colors),
            Audio("Dancing with the devil", R.drawable.dancing_with_the_devil, "3:34", R.raw.dancing_with_the_devil),
            Audio("Burn the house down", R.drawable.burn_the_house_down, "3:32", R.raw.burn_the_house_down),
            Audio("We stay up all night", R.drawable.we_stay_up_all_night, "3:22", R.raw.we_stay_up_all_night)
        )

        // Asignar el adapter
        recyclerView.adapter = AudioAdapter(audioList) { audio ->
            val fragment = MusicPlayerFragment.newInstance(audio)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
