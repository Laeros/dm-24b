package example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ImageFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var buttonNext: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.image_view)
        buttonNext = view.findViewById(R.id.button_next)

        // Obtener la imagen seleccionada del argumento
        val selectedImage = arguments?.getString("selectedImage")

        // Mostrar la imagen en el ImageView según la selección
        when (selectedImage) {
            "Image 1" -> imageView.setImageResource(R.drawable.platinum_halo_palkia)
            "Image 2" -> imageView.setImageResource(R.drawable.platinum_halo_dialga)
            "Image 3" -> imageView.setImageResource(R.drawable.platinum_halo_giratina)
        }

        // Navegar al siguiente fragmento (ImageDisplayFragment) al hacer clic
        buttonNext.setOnClickListener {
            val displayFragment = ImageDisplayFragment()
            displayFragment.arguments = arguments  // Pasar los mismos argumentos

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, displayFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
