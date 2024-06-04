package ar.edu.unsam.algo2

interface Accion {
    fun ejecutar(programa: Programa, grilla: Grilla)
}

class PartirPrograma(): Accion{
    override fun ejecutar(programa: Programa, grilla: Grilla){
        val programa1 = Programa().apply {
            titulo = "${programa.tituloEnPalabras()[0]} en el aire"
            conductoresPrincipales = programa.mitadConductores()
            presupuestoBase = programa.mitadPresupuesto()
            sponsors = programa.sponsors
            dia = programa.dia
            duracion = programa.mitadDuracion()
        }
        val programa2 = Programa().apply {
            titulo = programa.tituloEnPalabras().getOrNull(1) ?: "Programa sin nombre"
            conductoresPrincipales = programa.otraMitadConductores()
            presupuestoBase = programa.mitadPresupuesto()
            sponsors = programa.sponsors
            dia = programa.dia
            duracion = programa.mitadDuracion()
        }
        grilla.eliminarPrograma(programa)
        grilla.agregarPrograma(programa1)
        grilla.agregarPrograma(programa2)
    }
}

class SustitucionLosSimpson : Accion{
    override fun ejecutar(programa: Programa, grilla: Grilla) {
        val losSimpson = Programa("Los Simpson", mutableListOf(), 2000000, mutableListOf("Disney"), programa.dia, programa.duracion, mutableListOf(5.5, 8.4, 3.3, 3.1, 4.5))
        grilla.eliminarPrograma(programa)
        grilla.agregarPrograma(losSimpson)
    }
}

class FusionProgramas : Accion{

    private fun elegirPrimero(): Boolean = (Math.random() * 100) > 50

    private fun elegirPrograma(programa: Programa, otroPrograma: Programa) = if(elegirPrimero()) programa else otroPrograma

    private fun elegirAlAzar() = if(elegirPrimero()) "Impacto Total" else "Un Buen Día"

    override fun ejecutar(programa: Programa, grilla: Grilla) {
        val siguiente = grilla.siguientePrograma(programa)
        val programaFusionado = Programa().apply {
            titulo = elegirAlAzar()
            conductoresPrincipales = mutableListOf(programa.primerConductor(), siguiente.primerConductor())
            presupuestoBase = Math.min(programa.presupuestoBase, siguiente.presupuestoBase)
            sponsors = elegirPrograma(programa, siguiente).sponsors
            duracion = programa.duracion + siguiente.duracion
            dia = programa.dia
        }

        grilla.eliminarPrograma(programa)
        grilla.eliminarPrograma(siguiente)
        grilla.agregarPrograma(programaFusionado)
    }
}

class MoverDiaDePrograma : Accion{
    override fun ejecutar(programa: Programa, grilla: Grilla) {
        programa.dia = programa.dia.plus(1)
    }
}