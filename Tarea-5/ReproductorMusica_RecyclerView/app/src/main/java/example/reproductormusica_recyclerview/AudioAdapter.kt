package example.reproductormusica_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter para mostrar la lista de audios.
 * Autor: Rodrigo Ancori
 * Fecha creación: 03-10-2024
 * Fecha última modificación: 04-10-2024
 */
class AudioAdapter(private val audioList: List<Audio>, private val listener: (Audio) -> Unit) :
    RecyclerView.Adapter<AudioAdapter.AudioViewHolder>() {

    inner class AudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val audioName: TextView = itemView.findViewById(R.id.audio_name)
        val audioImage: ImageView = itemView.findViewById(R.id.audio_image)
        val audioDuration: TextView = itemView.findViewById(R.id.audio_duration)

        fun bind(audio: Audio) {
            audioName.text = audio.name
            audioImage.setImageResource(audio.imageResId)
            audioDuration.text = audio.duration
            itemView.setOnClickListener {
                listener(audio) // Pasar el audio seleccionado al fragmento de reproducción
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_audio, parent, false)
        return AudioViewHolder(view)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        holder.bind(audioList[position])
    }

    override fun getItemCount() = audioList.size
}
