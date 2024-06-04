package ar.edu.unsam.algo2

interface ObserverNuevoPrograma {
    fun notificar(programa: Programa, grilla: Grilla){}
}

class NotificarAConductores: ObserverNuevoPrograma{
    lateinit var mailSender: MailSender
    override fun notificar(programa: Programa, grilla: Grilla) {
        programa.conductoresPrincipales.forEach {
            mailSender.sendMail(
                Mail(
                    from = "programa@eltrece.tv",
                    to = it.mail,
                    subject = "¡Oportunidad!",
                    content = "¡Fuiste seleccionado para conducir ${programa.titulo}! Ponete en contacto con la gerencia."
                )
            )
        }
    }
}

class DarMensajePresupuestarioCliowin : ObserverNuevoPrograma{
    override fun notificar(programa: Programa, grilla: Grilla) {
        lateinit var smsSender : SmsSender
        if(ExcesoPresupuesto(100000).seCumple(programa)) {
            smsSender.sendSms(
                Sms(
                    from = "programacionElTrece",
                    to = "Clowin",
                    content = "${programa.presupuestoBase} - ${programa.titulo} - ¡CONSEGUIR SPONSOR URGENTE!"
                )
            )
        }
    }
}

class QuitarProgramasRevision : ObserverNuevoPrograma {
    override fun notificar(programa: Programa, grilla: Grilla) {
        grilla.sincronizarProgramasEnRevision()
    }
}

