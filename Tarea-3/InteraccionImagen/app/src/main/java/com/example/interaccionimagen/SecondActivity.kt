// Descripción: Segunda actividad que muestra la imagen seleccionada y permite volver a la primera actividad.
// Autor: [Tu Nombre]
// Fecha creación: 25/09/2024
// Fecha última modificación: 25/09/2024

package com.example.interaccionimagen

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Inicializando los elementos de la interfaz
        imageView = findViewById(R.id.image_view)
        buttonBack = findViewById(R.id.button_back)

        // Recuperar el nombre de la imagen seleccionada de la primera actividad
        val selectedImage = intent.getStringExtra("selectedImage")

        // Mostrar la imagen seleccionada en el ImageView
        val imageResId = when (selectedImage) {
            "imagen1" -> R.drawable.platinum_halo_giratina
            "imagen2" -> R.drawable.platinum_halo_dialga
            "imagen3" -> R.drawable.platinum_halo_palkia
            else -> R.drawable.ic_launcher_background
        }
        imageView.setImageResource(imageResId)

        buttonBack.setOnClickListener {
            finish()
        }
    }
}
