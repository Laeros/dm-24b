// Descripción: Actividad principal con un Spinner para seleccionar una imagen y un botón para pasar a la segunda actividad.
// Autor: [Tu Nombre]
// Fecha creación: 25/09/2024
// Fecha última modificación: 25/09/2024

package com.example.interaccionimagen

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerImages: Spinner
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando los elementos de la interfaz
        spinnerImages = findViewById(R.id.spinner_images)
        buttonNext = findViewById(R.id.button_next)

        // Lista de nombres de imágenes
        val imageNames = arrayOf("imagen1", "imagen2", "imagen3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, imageNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerImages.adapter = adapter

        // Recuperar el estado del Spinner si la actividad fue recreada (ej. rotación de pantalla)
        savedInstanceState?.let {
            val savedPosition = it.getInt("spinner_position", 0)
            spinnerImages.setSelection(savedPosition)
        }

        // Acción del botón para pasar a la segunda actividad
        buttonNext.setOnClickListener {
            val selectedImage = spinnerImages.selectedItem.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("selectedImage", selectedImage)
            intent.putExtra("spinner_position", spinnerImages.selectedItemPosition)
            startActivity(intent)
        }
    }

    // Guardar la posición del Spinner cuando se gire la pantalla o la actividad se recree
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("spinner_position", spinnerImages.selectedItemPosition)
    }
}
