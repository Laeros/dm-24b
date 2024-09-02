// Clase para representar un producto con un precio y un descuento aplicable
// Autor: Rodrigo Ancori
// Fecha creación: 2024-09-01
// Fecha última modificación: 2024-09-01

class Producto(private var precio: Double, private var descuento: Double) {

    init {
        // Validar que el precio y el descuento sean válidos
        if (precio < 0) {
            throw IllegalArgumentException("El precio no puede ser negativo.")
        }
        if (descuento < 0 || descuento > 20) {
            throw IllegalArgumentException("El descuento debe estar entre 0 y 20.")
        }
    }

    // Método get para obtener el precio
    fun getPrecio(): Double {
        return precio
    }

    // Método set para establecer el precio (validación de que no sea negativo)
    fun setPrecio(nuevoPrecio: Double) {
        if (nuevoPrecio >= 0) {
            precio = nuevoPrecio
        } else {
            println("Error: El precio no puede ser negativo.")
        }
    }

    // Método get para obtener el descuento
    fun getDescuento(): Double {
        return descuento
    }

    // Método set para establecer el descuento (validación del rango de descuento)
    fun setDescuento(nuevoDescuento: Double) {
        if (nuevoDescuento in 0.0..20.0) {
            descuento = nuevoDescuento
        } else {
            println("Error: El descuento debe estar entre 0 y 20.")
        }
    }
  
    // Método para calcular el precio final después de aplicar el descuento
    fun calcularPrecioFinal(): Double {
        return precio - (precio * descuento / 100)
    }
}

// Función principal para demostrar el uso de la clase Producto
fun main() {
    try {
        // Crear una instancia de Producto con precio inicial de 100 y descuento del 15%
        val producto = Producto(100.0, 15.0)

        // Mostrar el precio y descuento iniciales
        println("Precio inicial: \$${producto.getPrecio()}")
        println("Descuento: ${producto.getDescuento()}%")

        // Calcular y mostrar el precio final
        println("Precio final después del descuento: \$${producto.calcularPrecioFinal()}")

        // Modificar el precio y el descuento
        producto.setPrecio(120.0)
        producto.setDescuento(10.0) 
        println("\nNuevo precio: \$${producto.getPrecio()}")
        println("Nuevo descuento: ${producto.getDescuento()}%")
        println("Nuevo precio final después del descuento: \$${producto.calcularPrecioFinal()}")

        // Intentar establecer un descuento inválido
        producto.setDescuento(25.0)

    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}
