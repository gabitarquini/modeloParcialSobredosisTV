package ar.edu.unsam.algo2

interface ProgramaAgregadoObserver {
    fun ejecutar(programa: Programa)
}

class MailObserver(val mailSender: MailSender) : ProgramaAgregadoObserver {
    override fun ejecutar(programa: Programa) {
        mailSender.sendMail(Mail(
            from = "canal@mail.com",
            to = programa.conductoresPrincipales.map { it.mail }.joinToString { ", " },
            asunto = "Oportunidad!",
            cuerpo = "Fuiste seleccionado para conducir ${programa.titulo}! Ponete en contacto con la gerencia."
        )
        )
    }
}

class SponsorUrgenteObserver() : ProgramaAgregadoObserver {
    override fun ejecutar(programa: Programa) {
        if (programa.presupuestoBase > 100000.0)
            enviarMensaje(programa)
    }

    fun enviarMensaje(programa: Programa) {
        Cliowin.recibirMensaje(
            "${programa.presupuestoBase} - ${programa.titulo} - CONSEGUIR SPONSOR URGENTE!"
        )
    }
}

class LimpiarRevisionObserver(val canal: Canal) : ProgramaAgregadoObserver {
    override fun ejecutar(programa: Programa) {
        canal.limpiarRevision()
    }
}

class MailSender {
    fun sendMail(mail: Mail) {}
}

data class Mail(
    val from: String,
    val to: String,
    val asunto: String,
    val cuerpo: String
) {}

object Cliowin {
    fun recibirMensaje(msj: String) {}
}

