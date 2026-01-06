package br.com.alura.forum.service

import br.com.alura.forum.model.Categoria
import br.com.alura.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CursoService(var cursos: List<Curso>) {
    init {
        val curso1 = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = Categoria.PROGRAMACAO
        )
        val curso2 = Curso(
            id = 2,
            nome = "Java",
            categoria = Categoria.PROGRAMACAO
        )
        val curso3 = Curso(
            id = 3,
            nome = "Spring Boot",
            categoria = Categoria.PROGRAMACAO
        )
        cursos = Arrays.asList(curso1, curso2, curso3)
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.stream().filter({
                c -> c.id == id
        }).findFirst().get()
    }
}
