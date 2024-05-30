package ar.edu.unsam.algo2

//PUNTO 1

interface Restriccion {
    fun cumpleCondicion(programa: Programa): Boolean
}

class PromedioRating(val ratingMinimo: Double) : Restriccion {
    override fun cumpleCondicion(programa: Programa) =
        programa.promedioRatingAnteriores() > ratingMinimo

}

class ConductoresMaximo(val maximo: Int) : Restriccion {
    override fun cumpleCondicion(programa: Programa) =
        programa.cantidadConductores() <= maximo
}

class ConductoresPuntuales(var conductoresRequeridos: MutableList<Conductor>) : Restriccion {
    override fun cumpleCondicion(programa: Programa) =
        programa.conductoresPrincipales.containsAll(conductoresRequeridos)
}

class PresupuestoMaximo(val presupuestoMaximo: Double) : Restriccion {
    override fun cumpleCondicion(programa: Programa) =
        programa.presupuestoBase <= presupuestoMaximo
}

class RestriccionMultiple() : Restriccion {
    override fun cumpleCondicion(programa: Programa) =
        primeraCondicion(programa) && segundaCondicion(programa)

    fun primeraCondicion(programa: Programa) =
        PromedioRating(5.0).cumpleCondicion(programa) || ConductoresMaximo(3).cumpleCondicion(programa)

    fun segundaCondicion(programa: Programa) =
        ConductoresPuntuales(
            mutableListOf(
                Conductor(
                    "Pinky",
                    mail = "pinky@mail.com"
                )
            )
        ).cumpleCondicion(programa) && PresupuestoMaximo(100000.0).cumpleCondicion(programa)

}

