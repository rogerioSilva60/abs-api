package br.com.wk.abs.services;

import br.com.wk.abs.entities.Usuario;
import java.util.List;

public interface UsuarioService {

  void salvarEmLote(List<Usuario> usuarios);

}
