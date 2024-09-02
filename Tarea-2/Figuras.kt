// Clase abstracta para representar una figura geométrica con área y perímetro, y métodos para calcular y mostrar estos valores.
// Autor: Rodrigo Ancori
// Fecha creación: 2024-09-01
// Fecha última modificación: 2024-09-01

// Clase abstracta que define la estructura general de una figura geométrica
abstract class Shape {

    // Propiedad abstracta para el área de la figura
    abstract val area: Double

    // Propiedad abstracta para el perímetro de la figura
    abstract val perimeter: Double

    // Método abstracto para imprimir los detalles de la figura
    abstract fun printDetails()

    // Método para imprimir el área y el perímetro de la figura
    fun printAreaAndPerimeter() {
        println("Área: $area")
        println("Perímetro: $perimeter")
    }
}

// Subclase que representa un cuadrado, hereda de Shape
class Square(private val side: Double) : Shape() {

    // Calcular el área del cuadrado
    override val area: Double
        get() = side * side

    // Calcular el perímetro del cuadrado
    override val perimeter: Double
        get() = 4 * side

    // Imprimir los detalles del cuadrado
    override fun printDetails() {
        println("Cuadrado:")
        printAreaAndPerimeter()
    }
}

// Subclase que representa un círculo, hereda de Shape
class Circle(private val radius: Double) : Shape() {

    // Calcular el área del círculo
    override val area: Double
        get() = Math.PI * radius * radius

    // Calcular el perímetro del círculo
    override val perimeter: Double
        get() = 2 * Math.PI * radius

    // Imprimir los detalles del círculo
    override fun printDetails() {
        println("Círculo:")
        printAreaAndPerimeter()
    }
}

// Subclase que representa un rectángulo, hereda de Shape
class Rectangle(private val width: Double, private val height: Double) : Shape() {

    // Calcular el área del rectángulo
    override val area: Double
        get() = width * height

    // Calcular el perímetro del rectángulo
    override val perimeter: Double
        get() = 2 * (width + height)

    // Imprimir los detalles del rectángulo
    override fun printDetails() {
        println("Rectángulo:")
        printAreaAndPerimeter()
    }
}

// Función principal para demostrar el uso de las figuras
fun main() {
    // Crear instancias de las figuras
    val square = Square(4.0)
    val circle = Circle(5.0)
    val rectangle = Rectangle(4.0, 6.0)

    // Mostrar los detalles de cada figura
    square.printDetails()
    circle.printDetails()
    rectangle.printDetails()
}
