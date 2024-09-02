// Sistema de gestión de biblioteca que maneja materiales, usuarios y operaciones de préstamo y devolución.
// Autor: Rodrigo Ancori
// Fecha creación: 2024-09-01
// Fecha última modificación: 2024-09-02

import java.util.Scanner

// Clase abstracta base para representar un material en la biblioteca
abstract class Material(
    val titulo: String,
    val autor: String,
    val añoPublicacion: Int
) {
    // Método abstracto para mostrar los detalles del material
    abstract fun mostrarDetalles()
}

// Subclase que representa un libro, hereda de Material
class Libro(
    titulo: String,
    autor: String,
    añoPublicacion: Int,
    val genero: String,
    val numeroPaginas: Int
) : Material(titulo, autor, añoPublicacion) {

    // Mostrar los detalles del libro
    override fun mostrarDetalles() {
        println("Libro: $titulo")
        println("Autor: $autor")
        println("Año de publicación: $añoPublicacion")
        println("Género: $genero")
        println("Número de páginas: $numeroPaginas")
    }
}

// Subclase que representa una revista, hereda de Material
class Revista(
    titulo: String,
    autor: String,
    añoPublicacion: Int,
    val issn: String,
    val volumen: Int,
    val numero: Int,
    val editorial: String
) : Material(titulo, autor, añoPublicacion) {

    // Mostrar los detalles de la revista
    override fun mostrarDetalles() {
        println("Revista: $titulo")
        println("Autor: $autor")
        println("Año de publicación: $añoPublicacion")
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
// Función principal para interactuar con el usuario y gestionar la biblioteca
fun main() {
    val scanner = Scanner(System.`in`)
    val biblioteca = Biblioteca()  // Se crea una instancia de la clase Biblioteca

    while (true) {  // Bucle infinito para permitir al usuario realizar múltiples acciones
        println("Seleccione una opción:")
        println("1. Registrar material")
        println("2. Registrar usuario")
        println("3. Prestar material")
        println("4. Devolver material")
        println("5. Mostrar materiales disponibles")
        println("6. Mostrar materiales reservados por usuario")
        println("7. Salir")

        when (scanner.nextInt()) {
            1 -> {
                println("Ingrese el tipo de material (libro, revista):")
                val tipo = scanner.next().trim().lowercase()
                scanner.nextLine()
                println("Ingrese el título:")
                val titulo = scanner.nextLine()  // Se captura el título del material
                println("Ingrese el autor:")
                val autor = scanner.nextLine()  // Se captura el autor del material
                println("Ingrese el año de publicación:")
                val año = scanner.nextInt()  // Se captura el año de publicación
                scanner.nextLine()

                when (tipo) {  // Se determina el tipo de material y se capturan los detalles específicos
                    "libro" -> {
                        println("Ingrese el género:")
                        val genero = scanner.nextLine()  // Se captura el género del libro
                        println("Ingrese el número de páginas:")
                        val paginas = scanner.nextInt()  // Se captura el número de páginas
                        scanner.nextLine()
                        val libro = Libro(titulo, autor, año, genero, paginas)  // Se crea una instancia de Libro
                        biblioteca.registrarMaterial(libro)  // Se registra el libro en la biblioteca
                    }
                    "revista" -> {
                        println("Ingrese el ISSN:")
                        val issn = scanner.nextLine()  // Se captura el ISSN de la revista
                        println("Ingrese el volumen:")
                        val volumen = scanner.nextInt()  // Se captura el volumen de la revista
                        println("Ingrese el número:")
                        val numero = scanner.nextInt()  // Se captura el número de la revista
                        scanner.nextLine()
                        println("Ingrese la editorial:")
                        val editorial = scanner.nextLine()  // Se captura la editorial de la revista
                        val revista = Revista(titulo, autor, año, issn, volumen, numero, editorial)  // Se crea una instancia de Revista
                        biblioteca.registrarMaterial(revista)  // Se registra la revista en la biblioteca
                    }
                    else -> {
                        println("Tipo de material no reconocido.")  // Se notifica si el tipo de material ingresado no es válido
                    }
                }
            }
            2 -> {
                println("Ingrese el nombre del usuario:")
                val nombre = scanner.next()  // Se captura el nombre del usuario
                println("Ingrese el apellido del usuario:")
                val apellido = scanner.next()  // Se captura el apellido del usuario
                println("Ingrese la edad del usuario:")
                val edad = scanner.nextInt()  // Se captura la edad del usuario
                val usuario = Usuario(nombre, apellido, edad)  // Se crea una instancia de Usuario
                biblioteca.registrarUsuario(usuario)  // Se registra el usuario en la biblioteca
            }
            3 -> {
                println("Ingrese el título del material a prestar:")
                val titulo = scanner.next()  // Se captura el título del material a prestar
                println("Ingrese el nombre del usuario:")
                val nombre = scanner.next()  // Se captura el nombre del usuario
                println("Ingrese el apellido del usuario:")
                val apellido = scanner.next()  // Se captura el apellido del usuario
                val usuario = Usuario(nombre, apellido, edad = 0)  // Se crea una instancia de Usuario sin utilizar la edad
                biblioteca.prestarMaterial(titulo, usuario)  // Se intenta prestar el material al usuario
            }
            4 -> { 
                println("Ingrese el título del material a devolver:")
                val titulo = scanner.next()  // Se captura el título del material a devolver
                println("Ingrese el nombre del usuario:")
                val nombre = scanner.next()  // Se captura el nombre del usuario
                println("Ingrese el apellido del usuario:")
                val apellido = scanner.next()  // Se captura el apellido del usuario
                val usuario = Usuario(nombre, apellido, edad = 0)  // Se crea una instancia de Usuario sin utilizar la edad
                biblioteca.devolverMaterial(titulo, usuario)  // Se intenta devolver el material
            }
            5 -> {
                biblioteca.mostrarMaterialesDisponibles()  // Se muestra la lista de materiales disponibles en la biblioteca
            }
            6 -> { 
                println("Ingrese el nombre del usuario:")
                val nombre = scanner.next()  // Se captura el nombre del usuario
                println("Ingrese el apellido del usuario:")
                val apellido = scanner.next()  // Se captura el apellido del usuario
                val usuario = Usuario(nombre, apellido, edad = 0)  // Se crea una instancia de Usuario sin utilizar la edad
                biblioteca.mostrarMaterialesReservadosPorUsuario(usuario)  // Se muestra la lista de materiales reservados por el usuario
            }
            7 -> {  // Salir del programa
                println("Saliendo...") 
                break  // Se rompe el bucle, finalizando la ejecución del programa
            }
            else -> {
                println("Opción no válida. Por favor, intente de nuevo.")  // Se notifica si la opción seleccionada no es válida
            }
        }
    }
    scanner.close()
}
