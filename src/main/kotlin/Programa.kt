package ar.edu.unsam.algo2

import java.time.DayOfWeek

class Programa(
    var titulo: String = "",
    var conductoresPrincipales: List<Conductor> = mutableListOf(),
    var presupuestoBase: Int = 0,
    var sponsors: List<String> = mutableListOf(),
    var dia: DayOfWeek = DayOfWeek.FRIDAY,
    var duracion: Int = 0,
    val ratings: List<Double> = mutableListOf(),
    val restricciones: List<Restriccion> = mutableListOf()
){
    fun promedioRating() = ratings.average()
    fun mitadConductores() = conductoresPrincipales.take(conductoresPrincipales.size / 2)
    fun otraMitadConductores() = conductoresPrincipales.minus(mitadConductores().toSet())
    fun mitadPresupuesto() = presupuestoBase / 2
    fun mitadDuracion() = duracion / 2
    fun tituloEnPalabras() = titulo.split(" ")
    fun primerConductor() = conductoresPrincipales.first()

    fun revisar(grilla: Grilla){
        val primeraRestriccion = restricciones.find { restriccion -> !restriccion.seCumple(this)}
        primeraRestriccion?.ejecutarAcciones(this, grilla)
    }
}

class Conductor(val nombre: String, val mail: String){}