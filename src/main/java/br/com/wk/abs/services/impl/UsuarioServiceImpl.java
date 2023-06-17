package br.com.wk.abs.services.impl;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.repositories.UsuarioRepository;
import br.com.wk.abs.services.UsuarioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository repository;

  @Transactional
  @Override
  public void salvarEmLote(List<Usuario> usuarios) {
    log.info("Salvando usuarios em lote...");
    repository.salvarEmLote(usuarios);
    log.info(String.format("Usuarios em lote salvos, total: %s", usuarios.size()));
  }

}
