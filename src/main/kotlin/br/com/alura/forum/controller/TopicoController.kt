package br.com.alura.forum.controller

import br.com.alura.forum.dto.request.TopicoRequest
import br.com.alura.forum.dto.request.TopicoUpdateRequest
import br.com.alura.forum.dto.response.TopicoResponse
import br.com.alura.forum.model.Categoria
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.service.TopicoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
open class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
    open fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size=5, sort=["data_criacao"], direction= Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoResponse> {
        return service.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): Topico {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    open fun cadastrar(
        @RequestBody @Valid topico: TopicoRequest,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoResponse> {
        val topicoResponse = service.cadastrar(topico)
        val uri = uriComponentsBuilder.path("/topicos/${topicoResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoResponse)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    open fun atualizar(@RequestBody @Valid topico: TopicoUpdateRequest): ResponseEntity<TopicoResponse> {
        val topico = service.atualizar(topico)
        return ResponseEntity.ok(topico)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    open fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}