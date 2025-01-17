package ar.edu.unsam.algo2

class MailSender {
    fun sendMail(mail: Mail){}
}

data class Mail(
    val from: String,
    val to: String,
    val subject: String,
    val content: String
)