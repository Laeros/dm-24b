package example.reproductormusica_recyclerview

/**
 * Modelo de datos para un audio.
 * Autor: Rodrigo Ancori
 * Fecha creación: 03-10-2024
 * Fecha última modificación: 04-10-2024
 */

data class Audio(
    val name: String,
    val imageResId: Int,
    val duration: String,
    val audioResId: Int
)
