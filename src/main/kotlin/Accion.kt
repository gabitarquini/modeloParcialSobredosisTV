package ar.edu.unsam.algo2

//PUNTO 2

interface Accion {
    fun ejecutar (programa: Programa)
}

class PartirPrograma (val grilla : MutableList<Programa>) : Accion {

    override fun ejecutar(programa: Programa) {
        val palabrasTitulo = programa.titulo.split(" ")

        var programa2 = Programa (
            titulo = palabrasTitulo.getOrNull(1) ?: "Programa sin nombre",
            conductoresPrincipales = programa.conductoresPrincipales.subList(programa.cantidadConductores()/2,programa.cantidadConductores()),
            presupuestoBase = programa.presupuestoBase / 2,
            sponsorPublicidad = programa.sponsorPublicidad,
            dia = programa.dia,
            duracion = (programa.duracion / 2),
            ratingAnteriores = mutableListOf()
        )

        programa.titulo = palabrasTitulo.first()
        programa.conductoresPrincipales = programa.conductoresPrincipales.subList(0,programa.cantidadConductores()/2)
        programa.presupuestoBase = (programa.presupuestoBase / 2)
        programa.duracion = (programa.duracion / 2)

    }
}

class ReemplazarPorLosSimpson () : Accion {

    override fun ejecutar(programa: Programa) {
        programa.titulo = LosSimpson.titulo
        programa.conductoresPrincipales = LosSimpson.conductoresPrincipales
        programa.presupuestoBase = LosSimpson.presupuestoBase
        programa.sponsorPublicidad = LosSimpson.sponsorPublicidad
        programa.ratingAnteriores = LosSimpson.ratingAnteriores
    }
}

var LosSimpson = Programa(
    titulo = "Los Simpson",
    conductoresPrincipales = mutableListOf(),
    presupuestoBase = 0.0,
    sponsorPublicidad = "Movistar",
    dia = Dia.LUNES,
    duracion = 60,
    ratingAnteriores = mutableListOf(5.0,6.5,7.0,5.2,9.2)
)

class fusionarPrograma(val canal : Canal) : Accion{

    val titulosPosibles = mutableListOf("Impacto total", "Un buen día")

    override fun ejecutar(programa: Programa) {
        val indice = canal.grilla.indexOf(programa)
        val siguientePrograma = canal.grilla.getOrNull(indice+1) ?: canal.grilla.first()

        val programaFusionado = Programa(
            titulo = titulosPosibles.random(),
            conductoresPrincipales = mutableListOf(programa.conductoresPrincipales.first(), siguientePrograma.conductoresPrincipales.first()),
            presupuestoBase = minOf(programa.presupuestoBase, siguientePrograma.presupuestoBase),
            sponsorPublicidad = mutableListOf(programa.sponsorPublicidad + siguientePrograma.sponsorPublicidad).random(),
            dia = programa.dia,
            duracion = programa.duracion + siguientePrograma.duracion,
            ratingAnteriores = mutableListOf<Double>()
        )
        canal.quitarProgramaGrilla(programa)
        canal.quitarProgramaGrilla(siguientePrograma)
        canal.agregarProgramaAGrilla(programaFusionado)
    }
}

class moverDia (val nuevoDia: Dia) : Accion{
    override fun ejecutar(programa: Programa) {
        programa.dia = nuevoDia
    }
}


