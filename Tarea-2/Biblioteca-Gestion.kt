// Sistema de gestión de biblioteca que maneja materiales, usuarios y operaciones de préstamo y devolución.
// Autor: Rodrigo Ancori
// Fecha creación: 2024-09-01
// Fecha última modificación: 2024-09-01

import java.util.Scanner

// Clase abstracta base para representar un material en la biblioteca
abstract class Material(
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int
) {
    // Método abstracto para mostrar los detalles del material
    abstract fun mostrarDetalles()
}

// Subclase que representa un libro, hereda de Material
class Libro(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    val genero: String,
    val numeroPaginas: Int
) : Material(titulo, autor, anioPublicacion) {

    // Mostrar los detalles del libro
    override fun mostrarDetalles() {
        println("Libro: $titulo")
        println("Autor: $autor")
        println("Año de publicación: $anioPublicacion")
        println("Género: $genero")
        println("Número de páginas: $numeroPaginas")
    }
}

// Subclase que representa una revista, hereda de Material
class Revista(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    val issn: String,
    val volumen: Int,
    val numero: Int,
    val editorial: String
) : Material(titulo, autor, anioPublicacion) {

    // Mostrar los detalles de la revista
    override fun mostrarDetalles() {
        println("Revista: $titulo")
        println("Autor: $autor")
        println("Año de publicación: $anioPublicacion")
        println("ISSN: $issn")
        println("Volumen: $volumen")
        println("Número: $numero")
        println("Editorial: $editorial")
    }
}

// Data class para representar a un usuario
data class Usuario(
    val nombre: String,
    val apellido: String,
    val edad: Int
)

// Interfaz que define las operaciones de una biblioteca
interface IBiblioteca {
    fun registrarMaterial(material: Material)
    fun registrarUsuario(usuario: Usuario)
    fun prestarMaterial(titulo: String, usuario: Usuario): Boolean
    fun devolverMaterial(titulo: String, usuario: Usuario): Boolean
    fun mostrarMaterialesDisponibles()
    fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario)
}

