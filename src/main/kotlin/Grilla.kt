package ar.edu.unsam.algo2

class Grilla {
    val programas = mutableListOf<Programa>()
    val observer = mutableListOf<ObserverNuevoPrograma>()
    val programasEnRevision = mutableListOf<Programa>()

    fun agregarPrograma(programa: Programa){
        programas.add(programa)
        observer.forEach { it.notificar(programa, this)}
    }

    fun eliminarPrograma(programa: Programa){
        programas.remove(programa)
    }

    fun siguientePrograma(programa: Programa): Programa{
        val indicePrograma = programas.indexOf(programa)
        return if (programas.size > indicePrograma) programas[indicePrograma + 1] else programas[0]
    }

    fun sincronizarProgramasEnRevision(){
        programasEnRevision.removeAll { programasEnRevision -> !programas.contains(programasEnRevision)}
    }
}