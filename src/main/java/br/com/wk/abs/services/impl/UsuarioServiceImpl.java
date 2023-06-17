package br.com.wk.abs.services.impl;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.repositories.UsuarioRepository;
import br.com.wk.abs.services.UsuarioService;
import br.com.wk.abs.services.exceptions.NotFoundException;
import br.com.wk.abs.vo.CandidatoVO;
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
    log.info(String.format("Lote de usuarios salvos, total: %s", usuarios.size()));
  }

  @Override
  public List<CandidatoVO> buscarAgrupadoPorEstado() {
    log.info("Busca dos candidatos agrupados por estado iniciado...");
    List<CandidatoVO> candidatoVOS = repository.buscarAgrupadoPorEstado();
    log.info("Busca dos candidatos agrupados por estado finalizado");

    if(candidatoVOS.isEmpty()){ throw new NotFoundException("Candidatos não encontrado(s)"); }

    return candidatoVOS;
  }

}
