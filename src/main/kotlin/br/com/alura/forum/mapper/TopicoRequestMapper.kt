package br.com.alura.forum.mapper

import br.com.alura.forum.dto.request.TopicoRequest
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoRequestMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
): Mapper<TopicoRequest, Topico>{
    override fun map(t: TopicoRequest): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)
        )
    }

}
