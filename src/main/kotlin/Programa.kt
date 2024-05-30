package ar.edu.unsam.algo2

class Programa(
    var titulo: String,
    var conductoresPrincipales: MutableList<Conductor>,
    var presupuestoBase: Double,
    var sponsorPublicidad: String,
    var dia: Dia,
    var duracion: Int,
    var ratingAnteriores: MutableList<Double>
) {
    var restricciones: MutableSet<Restriccion> = mutableSetOf()
    var acciones: MutableSet<Accion> = mutableSetOf()

    fun agregarRestriccion(restricciones: MutableSet<Restriccion>) = restricciones.addAll(restricciones)

    fun eliminarRestriccion(restriccion: Restriccion) = restricciones.remove(restriccion)

    fun filtrarIncumplimientos() = restricciones.filter { !it.cumpleCondicion(this) }

    fun cantidadConductores() = conductoresPrincipales.size

    fun promedioRatingAnteriores() = ratingAnteriores.average()

    fun agregarConductorPrincipal(conductor: Conductor) = conductoresPrincipales.add(conductor)

    fun eliminarConductorPrincipal(conductor: Conductor) = conductoresPrincipales.remove(conductor)

    fun agregarAccion(accion: Accion) = acciones.add(accion)

    fun tomarAcciones() = acciones.forEach { it.ejecutar(this) }


}

data class Conductor(
    val nombre: String,
    val mail: String
)

enum class Dia {
    LUNES,
    MARTES,
    MIERCOLES,
    JUEVES,
    VIERNES,
    SABADO,
    DOMINGO
}