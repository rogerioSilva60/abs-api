package br.com.wk.abs.repositories;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.repositories.queries.UsuarioRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {

}
