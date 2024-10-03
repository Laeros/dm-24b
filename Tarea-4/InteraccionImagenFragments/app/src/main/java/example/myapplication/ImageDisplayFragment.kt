package example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

// Fragmento para mostrar la imagen seleccionada
// Autor: Rodrigo Ancori
// Fecha creación: [01-10-2024]
// Fecha última modificación: [02-10-2024]

class ImageDisplayFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var buttonBack: Button
    private lateinit var buttonSelectImage: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_display, container, false)

        // Referencias a los elementos del layout
        imageView = view.findViewById(R.id.image_view)
        buttonBack = view.findViewById(R.id.button_back)
        buttonSelectImage = view.findViewById(R.id.button_select_image)

        // Obtener la imagen seleccionada del bundle
        val selectedImage = arguments?.getString("selectedImage")

        // Lógica para mostrar la imagen seleccionada en el ImageView
        when (selectedImage) {
            "Image 1" -> imageView.setImageResource(R.drawable.platinum_halo_palkia)
            "Image 2" -> imageView.setImageResource(R.drawable.platinum_halo_dialga)
            "Image 3" -> imageView.setImageResource(R.drawable.platinum_halo_giratina)
        }

        // Acción del botón "Back"
        buttonBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Configurar el botón para regresar al selector de imágenes (ImageSelectorFragment)
        buttonSelectImage.setOnClickListener {
            val imageSelectorFragment = ImageSelectorFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, imageSelectorFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}