package br.com.alura.forum.model

import java.time.LocalDateTime

class Topico (
    val id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val curso: Curso,
    val autor: Usuario,
    var status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    var respostas: List<Resposta> = ArrayList()
)