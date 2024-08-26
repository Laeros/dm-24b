// Script que evalúa el rendimiento de un empleado basado en su puntuación y calcula el dinero extra que recibirá.
// Autor: Rodrigo Ancori
// Fecha creación: 24/08/2024
// Fecha última modificación: 25/08/2024

/**
 * @param puntuacion Entero que representa la puntuación del empleado (de 0 a 10).
 * @param salario Double que representa el salario mensual del empleado.
 * @return Pair que contiene el nivel de rendimiento como String y el dinero extra como Double.
 */
fun calcularNivelYDinero(puntuacion: Int, salario: Double): Pair<String, Double> {
    // Asigna un nivel de rendimiento basado en la puntuación
    val nivel = when (puntuacion) {
        in 0..3 -> "Inaceptable"
        in 4..6 -> "Aceptable"  
        in 7..10 -> "Meritorio" 
        else -> "Puntuación no válida"
    }
    // Calcula el dinero extra basado en la puntuación, solo si la puntuación es válida
    val dinero = if (puntuacion in 0..10) salario * (puntuacion / 10.0) else 0.0
    return Pair(nivel, dinero)  // Retorna un par con el nivel y el dinero extra calculado
}

// Función principal que solicita la puntuación y salario del usuario
fun main() {
    print("Introduce tu puntuación: ")
    // Lee la puntuación del usuario, con manejo de errores en caso de entrada no válida
    val puntuacion = readLine()?.toIntOrNull() ?: 0 

    print("Introduce tu salario mensual: ")
    // Lee el salario del usuario, con manejo de errores en caso de entrada no válida
    val salario = readLine()?.toDoubleOrNull() ?: 0.0 

    // Llama a la función para calcular el nivel de rendimiento y el dinero extra basado en la puntuación y salario
    val (nivel, dinero) = calcularNivelYDinero(puntuacion, salario)

    // Muestra el nivel de rendimiento calculado
    println("Nivel de Rendimiento: $nivel")
    // Muestra el dinero extra que recibirá el empleado
    println("Cantidad de Dinero Recibido: $$dinero")
    
    // Funcionalidad adicional: Cálculo del dinero extra acumulado en varios meses
    print("Introduce el número de meses para calcular el dinero extra acumulado: ")
    // Lee el número de meses del usuario, con manejo de errores en caso de entrada no válida
    val meses = readLine()?.toIntOrNull() ?: 1

    // Calcula el dinero extra acumulado
    val dineroAcumulado = dinero * meses
    // Muestra el dinero extra acumulado durante el número de meses especificado
    println("Dinero Extra Acumulado en $meses mes(es): $$dineroAcumulado") 
}
