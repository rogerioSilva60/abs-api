package br.com.wk.abs.repositories.queries.impl;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.repositories.queries.UsuarioRepositoryQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryQueryImpl implements UsuarioRepositoryQuery {

  public static final int LIMITE_INCERSOES = 20;

  @PersistenceContext
  private EntityManager manager;

  @Override
  public void salvarEmLote(List<Usuario> usuarios) {
    int contadorDeInserceos = 0;
    for(Usuario usuario : usuarios) {
      manager.persist(usuario);

      if(++contadorDeInserceos == LIMITE_INCERSOES) {
        manager.flush();
        manager.clear();
        contadorDeInserceos = 0;
      }
    }
  }

}
