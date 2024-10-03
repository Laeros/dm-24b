package example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment

class ImageSelectorFragment : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var buttonNext: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_selector, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = view.findViewById(R.id.spinner_images)
        buttonNext = view.findViewById(R.id.button_next)

        buttonNext.setOnClickListener {
            val selectedImage = spinner.selectedItem.toString()

            // Pasar la imagen seleccionada al siguiente fragmento mediante argumentos
            val args = Bundle()
            args.putString("selectedImage", selectedImage)

            val imageFragment = ImageFragment()
            imageFragment.arguments = args

            // Navegar al siguiente fragmento manualmente
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, imageFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
