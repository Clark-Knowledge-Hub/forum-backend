package br.com.alura.forum.service

import br.com.alura.forum.dto.request.TopicoRequest
import br.com.alura.forum.dto.request.TopicoUpdateRequest
import br.com.alura.forum.dto.response.TopicoResponse
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.ArrayList
import java.util.stream.Collectors
import br.com.alura.forum.mapper.TopicoRequestMapper
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoMapper: TopicoMapper,
    private val topicoRequestMapper: TopicoRequestMapper,
    private val notFoundMessage: String = "Tópico não encontrado",
    private val repository: TopicoRepository
) {

    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoResponse> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map {
            t -> topicoMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter({
            t -> t.id == id
        }).findFirst().orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun cadastrar(dto: TopicoRequest): TopicoResponse {
        val topico = topicoRequestMapper.map(dto)
        val nextId = (topicos.maxOfOrNull { it.id ?: 0L } ?: 0L) + 1L
        topico.id = nextId
        topicos = topicos + topico
        return topicoMapper.map(topico)
    }

    fun atualizar(dto: TopicoUpdateRequest): TopicoResponse {
        val existente = topicos.find { it.id == dto.id } ?: throw NotFoundException(notFoundMessage)
        val atualizado = Topico(
            id = dto.id,
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = existente.curso,
            autor = existente.autor,
            respostas = existente.respostas,
            status = existente.status,
            dataCriacao = existente.dataCriacao
        )
        topicos = topicos.map { if (it.id == dto.id) atualizado else it }
        return topicoMapper.map(atualizado)
    }

    fun deletar(id: Long){
        val topico = topicos.stream().filter({
                t -> t.id == id
        }).findFirst().orElseThrow { NotFoundException(notFoundMessage) }
        topicos.minus(topico)
    }
}