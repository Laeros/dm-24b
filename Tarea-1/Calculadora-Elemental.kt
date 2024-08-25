// Simula una calculadora básica con operaciones de suma, resta, multiplicación y división.
// Autor: Rodrigo Ancori
// Fecha creación: 25/08/2024
// Fecha última modificación: 25/08/2024

fun mostrarMenu() {
    println("==== Menú ====")
    println("1. Suma")
    println("2. Resta")
    println("3. Multiplicación")
    println("4. División")
    println("5. Salir")
}

fun realizarOperacion(opcion: Int, num1: Double, num2: Double): Double? {
    return when (opcion) {
        1 -> num1 + num2
        2 -> num1 - num2
        3 -> num1 * num2
        4 -> if (num2 != 0.0) num1 / num2 else null
        else -> null
    }
}

fun main() {
    var opcion: Int
    do {
        mostrarMenu()
        print("Selecciona una opción: ")
        opcion = readLine()?.toIntOrNull() ?: 0

        if (opcion in 1..4) {
            print("Introduce el primer número: ")
            val num1 = readLine()?.toDoubleOrNull() ?: 0.0
            print("Introduce el segundo número: ")
            val num2 = readLine()?.toDoubleOrNull() ?: 0.0

            val resultado = realizarOperacion(opcion, num1, num2)
            if (resultado != null) {
                println("Resultado: $resultado")
            } else {
                println("No se puede dividir por cero.")
            }
        } else if (opcion != 5) {
            println("Opción no válida.")
        }
    } while (opcion != 5)

    println("Saliendo del programa.")
}
