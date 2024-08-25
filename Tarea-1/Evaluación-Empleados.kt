// Script que evalúa el rendimiento de un empleado basado en su puntuación y calcula el dinero extra que recibirá.
// Autor: Rodrigo Ancori
// Fecha creación: 24/08/2024
// Fecha última modificación: 25/08/2024

fun calcularNivelYDinero(puntuacion: Int, salario: Double): Pair<String, Double> {
    val nivel = when (puntuacion) {
        in 0..3 -> "Inaceptable"
        in 4..6 -> "Aceptable"
        in 7..10 -> "Meritorio"
        else -> "Puntuación no válida"
    }
    val dinero = if (puntuacion in 0..10) salario * (puntuacion / 10.0) else 0.0
    return Pair(nivel, dinero)
}

fun main() {
    print("Introduce tu puntuación: ")
    val puntuacion = readLine()?.toIntOrNull() ?: 0
    print("Introduce tu salario mensual: ")
    val salario = readLine()?.toDoubleOrNull() ?: 0.0

    val (nivel, dinero) = calcularNivelYDinero(puntuacion, salario)
    println("Nivel de Rendimiento: $nivel")
    println("Cantidad de Dinero Recibido: $$dinero")
}
