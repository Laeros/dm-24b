// Juego de Piedra, Papel o Tijera contra la computadora.
// Autor: Rodrigo Ancori
// Fecha creación: 25/08/2024
// Fecha última modificación: 25/08/2024

import kotlin.random.Random

fun PiedraPapelTijera(Usuario: String): String {
    val opciones = listOf("Piedra", "Papel", "Tijera")
    val Computadora = opciones[Random.nextInt(opciones.size)]
    
    return when {
        Usuario == Computadora -> "Empate. La computadora eligió $Computadora."
        (Usuario == "Piedra" && Computadora == "Tijera") ||
        (Usuario == "Papel" && Computadora == "Piedra") ||
        (Usuario == "Tijera" && Computadora == "Papel") -> "Ganaste! La computadora eligió $Computadora."
        else -> "Perdiste. La computadora eligió $Computadora."
    }
}

fun main() {
    print("Elige Piedra, Papel o Tijera: ")
    val Usuario = readLine()?.capitalize() ?: ""
    
    if (Usuario in listOf("Piedra", "Papel", "Tijera")) {
        val resultado = PiedraPapelTijera(Usuario)
        println(resultado)
    } else {
        println("Opción no válida.")
    }
}
