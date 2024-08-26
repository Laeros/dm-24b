// Juego de Piedra, Papel o Tijera contra la computadora.
// Autor: Rodrigo Ancori
// Fecha creación: 25/08/2024
// Fecha última modificación: 26/08/2024

import kotlin.random.Random  // Importa la clase Random para generar opciones aleatorias para la computadora.

/**
 * Función que simula el juego de Piedra, Papel o Tijera entre el usuario y la computadora.
 * @param usuario String que representa la elección del usuario ("Piedra", "Papel", "Tijera").
 * @return String con el resultado del juego, indicando si el usuario ganó, perdió o empató.
 */
fun piedraPapelTijera(usuario: String): String {
    // Lista de opciones posibles para el juego.
    val opciones = listOf("Piedra", "Papel", "Tijera")
    // La computadora selecciona una opción aleatoria.
    val computadora = opciones[Random.nextInt(opciones.size)]
    
    // Determina el resultado del juego comparando la elección del usuario con la de la computadora.
    return when {
        usuario == computadora -> "Empate. La computadora eligió $computadora."
        (usuario == "Piedra" && computadora == "Tijera") ||
        (usuario == "Papel" && computadora == "Piedra") ||
        (usuario == "Tijera" && computadora == "Papel") -> "Ganaste! La computadora eligió $computadora."
        else -> "Perdiste. La computadora eligió $computadora."
    }
}

/**
 * Función principal que controla el flujo del juego, permitiendo al usuario jugar múltiples rondas
 * y mostrando un resumen de los resultados al final.
 */
fun main() {
    var continuar: String  // Variable para controlar si el usuario desea jugar otra ronda.
    var victorias = 0  // Contador de las victorias del usuario.
    var derrotas = 0   // Contador de las derrotas del usuario.
    var empates = 0    // Contador de los empates.
    
    do {
        // Solicita al usuario que elija Piedra, Papel o Tijera.
        print("Elige Piedra, Papel o Tijera: ")
        val usuario = readLine()?.capitalize() ?: ""  // Captura la entrada del usuario y la capitaliza.

        // Verifica si la entrada del usuario es válida.
        if (usuario in listOf("Piedra", "Papel", "Tijera")) {
            // Llama a la función piedraPapelTijera y obtiene el resultado.
            val resultado = piedraPapelTijera(usuario)
            println(resultado)
            
            // Actualiza los contadores de victorias, derrotas o empates basado en el resultado.
            when {
                "Ganaste" in resultado -> victorias++
                "Perdiste" in resultado -> derrotas++
                "Empate" in resultado -> empates++
            }
        } else {
            println("Opción no válida.")
        }
        
        print("¿Quieres jugar otra vez? (s/n): ")
        continuar = readLine()?.lowercase() ?: "n"
        
    } while (continuar == "s")  // El bucle continúa mientras el usuario quiera seguir jugando.

    // Muestra un resumen de los resultados de todas las rondas jugadas.
    println("\nResumen del juego:")
    println("Victorias: $victorias")
    println("Derrotas: $derrotas")
    println("Empates: $empates")
}
