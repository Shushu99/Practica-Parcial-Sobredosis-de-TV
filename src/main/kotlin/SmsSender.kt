package ar.edu.unsam.algo2

class SmsSender {
    fun sendSms(sms: Sms){}
}

data class Sms(
    val from: String,
    val to: String,
    val content: String
)