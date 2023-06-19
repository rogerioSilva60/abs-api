package br.com.wk.abs.repositories.queries.impl;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.repositories.queries.UsuarioRepositoryQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

      if(!existePorEmail(usuario.getEmail())) {
        manager.persist(usuario);
      }

      if(++contadorDeInserceos == LIMITE_INCERSOES) {
        manager.flush();
        manager.clear();
        contadorDeInserceos = 0;
      }
    }
  }

  private Boolean existePorEmail(String email) {
    String jpql = "select case when(count(u) > 0) then true else false end from Usuario u "
        + "where u.email=?1";
    TypedQuery<Boolean> query = manager.createQuery(jpql, Boolean.class);
    query.setParameter(1, email);

    return query.getSingleResult();
  }
}
