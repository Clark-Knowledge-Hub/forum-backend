package br.com.alura.forum.service

import br.com.alura.forum.dto.request.TopicoRequest
import br.com.alura.forum.dto.response.TopicoResponse
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.ArrayList
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) {

    fun listar(): List<TopicoResponse> {
        return topicos.stream().map {
            t -> TopicoResponse(
                id = t.id,
                titulo = t.titulo,
                mensagem = t.mensagem,
                status = t.status,
                dataCriacao = t.dataCriacao,
        )}.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter({
            t -> t.id == id
        }).findFirst().get()
    }

    fun cadastrar(dto: TopicoRequest) {
        topicos = topicos.plus(Topico(
            id = (topicos.size + 1).toLong(),
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = usuarioService.buscarPorId(dto.idAutor)
        )
        )
    }
}