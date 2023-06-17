package br.com.wk.abs.repositories.queries;

import br.com.wk.abs.entities.Usuario;
import java.util.List;

public interface UsuarioRepositoryQuery {

  void salvarEmLote(List<Usuario> usuarios);

}
