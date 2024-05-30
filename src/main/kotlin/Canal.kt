package ar.edu.unsam.algo2

class Canal {
    val grilla = mutableListOf<Programa>()
    val restriccionAccion = mutableMapOf<Restriccion, Accion>()
    val programasRevision = mutableListOf<Programa>()
    val programaObserver = mutableListOf<ProgramaAgregadoObserver>()

    fun agregarProgramaAGrilla(programa: Programa) {
        grilla.add(programa)
        programaObserver.forEach { it.ejecutar(programa) }
    }

    fun quitarProgramaGrilla(programa: Programa) =
        grilla.remove(programa)

    fun agregarProgramaRevision(programa: Programa) =
        programasRevision.add(programa)

    fun quitarProgramaRevision(programa: Programa) =
        programasRevision.remove(programa)

    fun agregarRestriccionAccion(restriccion: Restriccion, accion: Accion) {
        restriccionAccion[restriccion] = accion
    }

    fun revisarProgramas() {
        programasRevision.forEach { it.agregarRestriccion(restriccionAccion.keys) }
        programasRevision.forEach {
            val primeraRestriccion = it.filtrarIncumplimientos().first()
            val accionATomar = restriccionAccion[primeraRestriccion]
            if (accionATomar != null) {
                it.agregarAccion(accionATomar)
                it.tomarAcciones()
            }
        }
    }

    fun limpiarRevision() {
        programasRevision.forEach {
            if (!estaEnGrilla(it))
                quitarProgramaRevision(it)
        }
    }

    fun estaEnGrilla(programa: Programa) = grilla.contains(programa)
}