// El usuario debe adivinar un número aleatorio entre 1 y 30 con un máximo de 5 intentos.
// Autor: Rodrigo Ancori
// Fecha creación: 25/08/2024
// Fecha última modificación: 26/08/2024

import kotlin.random.Random

// Función principal del juego donde el usuario intenta adivinar un número secreto
fun adivinarNumero() {
    // Se genera un número aleatorio entre 1 y 30 que el usuario debe adivinar
    val numeroSecreto = Random.nextInt(1, 31)
    // Contador para el número de intentos realizados por el usuario
    var intentos = 0
    // Número máximo de intentos permitidos
    val maxIntentos = 5

    // Bucle que permite al usuario adivinar el número hasta que alcance el número máximo de intentos
    while (intentos < maxIntentos) {
        // Se solicita al usuario que ingrese su adivinanza
        print("Adivina el número (Intento ${intentos + 1} de $maxIntentos): ")
        val adivinanza = readLine()?.toIntOrNull()

        // Validación para asegurarse de que el usuario introduzca un número válido
        if (adivinanza == null) {
            println("Por favor, introduce un número válido.")
            continue // Se reinicia el bucle si la entrada no es válida
        }

        // Se compara la adivinanza del usuario con el número secreto
        when {
            adivinanza < numeroSecreto -> println("El número es mayor.") // El número secreto es mayor
            adivinanza > numeroSecreto -> println("El número es menor.") // El número secreto es menor
            else -> {
                println("¡Felicidades! Adivinaste el número.")
                return // Finaliza la función si el número es correcto
            }
        }

        intentos++
    }

    // Si el usuario no adivina el número en los intentos permitidos, muestra el número secreto
    println("Game Over. El número era $numeroSecreto.")
}

// Función principal del programa
fun main() {
    var jugarDeNuevo: String
    do {
        adivinarNumero() // Inicia el juego
        // Pregunta al usuario si quiere jugar de nuevo
        print("¿Quieres jugar otra vez? (si/no): ")
        jugarDeNuevo = readLine()?.lowercase() ?: "no"
    } while (jugarDeNuevo == "si") // Si el usuario ingresa "si", el juego se reinicia

    println("Gracias por jugar. ¡Hasta la próxima!") // Mensaje de despedida
}
