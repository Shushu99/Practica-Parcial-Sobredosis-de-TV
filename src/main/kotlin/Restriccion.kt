package ar.edu.unsam.algo2

abstract class Restriccion {
    val acciones = mutableListOf<Accion>()


    abstract fun seCumple(programa: Programa): Boolean

    fun ejecutarAcciones(programa: Programa, grilla: Grilla){
        acciones.forEach {accion: Accion -> accion.ejecutar(programa, grilla)}
    }
}

class PromedioRating(val valor: Double) : Restriccion(){
    override fun seCumple(programa: Programa): Boolean = programa.promedioRating() > valor
}

class MaximoConductores(val n: Int): Restriccion(){
    override fun seCumple(programa: Programa): Boolean = programa.conductoresPrincipales.size <= n
}

class ConductorDestacado(val conductor: String): Restriccion(){
    override fun seCumple(programa: Programa): Boolean = programa.conductoresPrincipales.contains(conductor)
}

class ExcesoPresupuesto(val presupuesto: Int): Restriccion(){
    override fun seCumple(programa: Programa): Boolean = programa.presupuestoBase <= presupuesto
}

class RestriccionOrCompuesta(val restricciones: List<Restriccion>): Restriccion(){
    override fun seCumple(programa: Programa): Boolean = restricciones.any { restriccion: Restriccion -> restriccion.seCumple(programa) }
}

class RestriccionAndCompuesta(val restricciones: List<Restriccion>): Restriccion(){
    override fun seCumple(programa: Programa): Boolean = restricciones.all { restriccion: Restriccion -> restriccion.seCumple(programa) }
}
