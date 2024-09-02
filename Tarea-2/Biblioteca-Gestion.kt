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

// Clase que implementa la gestión de la biblioteca, manejando el registro de materiales, 
// usuarios, préstamos y devoluciones. Esta clase implementa la interfaz IBiblioteca.
class Biblioteca : IBiblioteca {

    // Lista mutable que almacena los materiales disponibles en la biblioteca.
    private val materialesDisponibles = mutableListOf<Material>()
    
    // Mapa que asocia cada usuario con una lista mutable de materiales que tiene en préstamo.
    private val usuariosConPrestamos = mutableMapOf<Usuario, MutableList<Material>>()

    // Registra un nuevo material en la lista de materiales disponibles.
    override fun registrarMaterial(material: Material) {
        materialesDisponibles.add(material)
        println("Material registrado: ${material.titulo}")
    }

    // Registra un nuevo usuario en el sistema, inicializando su lista de préstamos vacía.
    override fun registrarUsuario(usuario: Usuario) {
        if (!usuariosConPrestamos.containsKey(usuario)) {
            usuariosConPrestamos[usuario] = mutableListOf()
            println("Usuario registrado: $usuario")
        } else {
            println("El usuario ya está registrado.")
        }
    }

    // Realiza el préstamo de un material si está disponible y el usuario está registrado.
    override fun prestarMaterial(titulo: String, usuario: Usuario): Boolean {
        // Busca el material por título en la lista de materiales disponibles.
        val material = materialesDisponibles.find { it.titulo == titulo }
        // Verifica si el material existe y si el usuario está registrado.
        return if (material != null && usuariosConPrestamos.containsKey(usuario)) {
            materialesDisponibles.remove(material) // Remueve el material de la lista de disponibles.
            usuariosConPrestamos[usuario]?.add(material) // Añade el material a la lista de préstamos del usuario.
            println("Material prestado: $titulo")
            true
        } else {
            println("No se pudo realizar el préstamo.")
            false
        }
    }

    // Realiza la devolución de un material por parte de un usuario.
    override fun devolverMaterial(titulo: String, usuario: Usuario): Boolean {
        // Busca el material en la lista de préstamos del usuario.
        val material = usuariosConPrestamos[usuario]?.find { it.titulo == titulo }
        // Verifica si el material está en la lista de préstamos del usuario.
        return if (material != null) {
            usuariosConPrestamos[usuario]?.remove(material) // Remueve el material de la lista de préstamos del usuario.
            materialesDisponibles.add(material) // Añade el material de vuelta a la lista de disponibles.
            println("Material devuelto: $titulo")
            true
        } else {
            println("No se pudo realizar la devolución.") 
            false
        }
    }

    // Muestra todos los materiales que actualmente están disponibles en la biblioteca.
    override fun mostrarMaterialesDisponibles() {
        println("Materiales disponibles:")
        for (material in materialesDisponibles) {
            material.mostrarDetalles()
            println(------------------------------------------------------------------------------------)
        }
    }

    // Muestra los materiales que un usuario específico tiene reservados.
    override fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario) {
        val materiales = usuariosConPrestamos[usuario]
        if (materiales != null && materiales.isNotEmpty()) {
            println("Materiales reservados por ${usuario.nombre} ${usuario.apellido}:")
            for (material in materiales) {
                material.mostrarDetalles()
                println(--------------------------------------------------------------------------------)
            }
        } else {
            println("No hay materiales reservados por ${usuario.nombre} ${usuario.apellido}.")
        }
    }
}
