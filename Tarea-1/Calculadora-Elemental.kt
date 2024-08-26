// Simula una calculadora básica con operaciones de suma, resta, multiplicación y división.
// Autor: Rodrigo Ancori
// Fecha creación: 25/08/2024
// Fecha última modificación: 26/08/2024

// Función que muestra el menú de operaciones disponibles para la calculadora.
fun mostrarMenu() {
    println("==== Menú ====")
    println("1. Suma")
    println("2. Resta")
    println("3. Multiplicación")
    println("4. División")
    println("5. Ver Historial")
    println("6. Salir")
}

/**
 * Función que realiza la operación matemática seleccionada por el usuario.
 * 
 * @param opcion Int que indica la operación elegida (1: Suma, 2: Resta, 3: Multiplicación, 4: División).
 * @param num1 Double que representa el primer número ingresado.
 * @param num2 Double que representa el segundo número ingresado.
 * @return Double? El resultado de la operación, o null si se intenta dividir por cero o se selecciona una opción inválida.
 */
fun realizarOperacion(opcion: Int, num1: Double, num2: Double): Double? {
    return when (opcion) {
        1 -> num1 + num2
        2 -> num1 - num2
        3 -> num1 * num2
        4 -> if (num2 != 0.0) num1 / num2 else null  // Realiza la división, validando que el divisor no sea cero.
        else -> null
    }
}

/**
 * Función principal que controla el flujo del programa, permitiendo al usuario seleccionar operaciones,
 * introducir números y ver el resultado. El programa continúa hasta que el usuario elige salir.
 * Además, almacena un historial de operaciones realizadas.
 */
fun main() {
    var opcion: Int
    val historial = mutableListOf<String>()  // Lista para almacenar el historial de operaciones.

    do {
        mostrarMenu()
        print("Selecciona una opción: ")
        opcion = readLine()?.toIntOrNull() ?: 0  // Captura y convierte la entrada del usuario a un entero.

        when (opcion) {
            in 1..4 -> {
                print("Introduce el primer número: ")
                val num1 = readLine()?.toDoubleOrNull() ?: 0.0  // Captura el primer número y lo convierte a Double.
                print("Introduce el segundo número: ")
                val num2 = readLine()?.toDoubleOrNull() ?: 0.0  // Captura el segundo número y lo convierte a Double.

                val resultado = realizarOperacion(opcion, num1, num2)
                if (resultado != null) {
                    val operacion = when (opcion) {
                        1 -> "Suma"
                        2 -> "Resta"
                        3 -> "Multiplicación"
                        4 -> "División"
                        else -> "Operación Desconocida"
                    }
                    val registro = "$operacion: $num1 y $num2 = $resultado"
                    historial.add(registro)  // Agrega la operación al historial.
                    println("Resultado: $resultado")
                } else {
                    println("No se puede dividir por cero.")  // Maneja el error de división por cero.
                }
            }
            5 -> {
                if (historial.isEmpty()) {
                    println("No hay operaciones en el historial.")
                } else {
                    println("=== Historial de Operaciones ===")
                    historial.forEach { println(it) }  // Muestra todas las operaciones almacenadas en el historial.
                }
            }
            6 -> println("Saliendo del programa.")
            else -> println("Opción no válida.")  // Informa al usuario si selecciona una opción fuera del rango.
        }
    } while (opcion != 6)  // El bucle continúa hasta que el usuario seleccione salir (opción 6).
}
