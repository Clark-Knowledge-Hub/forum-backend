package br.com.alura.forum.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicoUpdateRequest(
    @field:NotNull(message = "O id do tópico não pode ser nulo")
    val id: Long,
    @field:NotEmpty(message = "O título não pode ser vazio")
    @field:Size(min = 5, max = 100, message = "O título deve ter entre {min} e {max} caracteres")
    val titulo: String,
    @field:NotEmpty(message = "A mensagem não pode ser vazia")
    @field:Size(min = 5, max = 100, message = "A mensagem deve ter entre {min} e {max} caracteres")
    val mensagem: String
)