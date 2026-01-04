package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var usuarios: List<Usuario>) {
    init {
        val usuario1 = Usuario(
            id = 1,
            nome = "João Silva",
            email = "joaosilva@gmail.com",
        )
        usuarios = listOf(usuario1)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter({
                u -> u.id == id
        }).findFirst().get()
    }
}
