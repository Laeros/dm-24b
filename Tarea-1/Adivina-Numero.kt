// El usuario debe de adivinar un número aleatorio entre 1 y 30 con 5 intentos máximos.
// Autor: Rodrigo Ancori
// Fecha creación: 25/08/2024
// Fecha última modificación: 25/08/2024

import kotlin.random.Random

fun adivinarNumero() {
    val numeroSecreto = Random.nextInt(1, 31)
    var intentos = 0
    val maxIntentos = 5

    while (intentos < maxIntentos) {
        print("Adivina el número (Intento ${intentos + 1} de $maxIntentos): ")
        val adivinanza = readLine()?.toIntOrNull()

        if (adivinanza == null) {
            println("Por favor, introduce un número válido.")
            continue
        }

        when {
            adivinanza < numeroSecreto -> println("El número es mayor.")
            adivinanza > numeroSecreto -> println("El número es menor.")
            else -> {
                println("¡Felicidades! Adivinaste el número.")
                return
            }
        }

        intentos++
    }

    println("Game Over. El número era $numeroSecreto.")
}

fun main() {
    adivinarNumero()
}
