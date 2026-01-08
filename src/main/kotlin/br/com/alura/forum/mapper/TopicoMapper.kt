package br.com.alura.forum.mapper

import br.com.alura.forum.dto.response.TopicoResponse
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoMapper: Mapper<Topico, TopicoResponse> {
    override fun map(t: Topico): TopicoResponse {
        return TopicoResponse(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            status = t.status,
            dataCriacao = t.dataCriacao,
        )
    }
}