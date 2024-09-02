// Clase para manejar una cuenta bancaria con saldo y límite de retiro, con una interfaz interactiva para el usuario.
// Autor: Rodrigo Ancori
// Fecha creación: 2024-01-09
// Fecha última modificación: 2024-01-09
import java.util.Scanner

class CuentaBancaria(private var saldo: Double, private val limiteRetiro: Double) {
    init {
        // Asegurar que el saldo inicial no sea negativo
        if (saldo < 0) {
            throw IllegalArgumentException("El saldo inicial no puede ser negativo.")
        }
    }

    // Método get para obtener el saldo actual
    fun getSaldo(): Double {
        return saldo
    }

    // Método set para establecer un nuevo saldo (validación de que no sea negativo)
    fun setSaldo(nuevoSaldo: Double) {
        if (nuevoSaldo >= 0) {
            saldo = nuevoSaldo
        } else {
            println("Error: El saldo no puede ser negativo.")
        }
    }

    // Método para realizar retiros
    fun retirar(monto: Double) {
        when {
            monto > limiteRetiro -> println("Error: El monto excede el límite de retiro.")
            monto > saldo -> println("Error: Saldo insuficiente.")
            else -> {
                saldo -= monto
                println("Retiro realizado. Saldo actual: $$saldo")
            }
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    println("Bienvenido al sistema de gestión de cuentas bancarias.")

    // Solicitar saldo inicial y límite de retiro
    print("Ingrese el saldo inicial de la cuenta: ")
    val saldoInicial = scanner.nextDouble()

    print("Ingrese el límite de retiro: ")
    val limiteRetiro = scanner.nextDouble()

    val miCuenta = CuentaBancaria(saldoInicial, limiteRetiro)

    while (true) {
        println("\nSeleccione una opción:")
        println("1. Consultar saldo")
        println("2. Retirar dinero")
        println("3. Salir")

        val opcion = scanner.nextInt()

        when (opcion) {
            1 -> {
                println("Saldo actual: ${miCuenta.getSaldo()}")
            }
            2 -> {
                print("Ingrese el monto a retirar: ")
                val monto = scanner.nextDouble()
                miCuenta.retirar(monto)
            }
            3 -> {
                println("Gracias por usar el sistema. ¡Adiós!")
                break
            }
            else -> {
                println("Opción no válida. Por favor, intente de nuevo.")
            }
        }
    }

    scanner.close()
}
