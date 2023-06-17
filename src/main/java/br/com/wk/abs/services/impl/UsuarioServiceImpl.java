package br.com.wk.abs.services.impl;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.repositories.UsuarioRepository;
import br.com.wk.abs.services.UsuarioService;
import br.com.wk.abs.services.exceptions.NotFoundException;
import br.com.wk.abs.vo.CandidatoVO;
import br.com.wk.abs.vo.FaixaEtariaVO;
import br.com.wk.abs.vo.UsuarioImcVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

  @Transactional(readOnly = true)
  @Override
  public List<FaixaEtariaVO> buscarMediaImcPorFaixaEtariaDeDezEmDezAnos() {
    log.info("Busca media IMC por perio de dez em dez anos iniciado...");
    List<FaixaEtariaVO> lista = new ArrayList<>();
    Integer maiorIdade = repository.buscarMaiorIdade();
    int idadesPorFaixa = (maiorIdade / 10) + 1;

    for (int i=1; i <= idadesPorFaixa; i++) {
      int maiorIdadePorPeriodo = Integer.parseInt(i + "0");
      int menorIdadePorPeriodo = maiorIdadePorPeriodo == 10 ? 0 : maiorIdadePorPeriodo - 9;

      List<UsuarioImcVO> usuariosPorPeriodo = repository.buscarIMCPorIdade(menorIdadePorPeriodo, maiorIdadePorPeriodo);

      double totalImc = 0;
      for(UsuarioImcVO user : usuariosPorPeriodo) { totalImc += user.getImc(); }

      BigDecimal mediaImc = BigDecimal.valueOf(totalImc == 0 ? 0 : totalImc / usuariosPorPeriodo.size())
          .setScale(2, RoundingMode.HALF_UP);

      log.info("Faixa de idade entre : " + menorIdadePorPeriodo + " - " + maiorIdadePorPeriodo + ", Media imc: " + mediaImc);
      lista.add(new FaixaEtariaVO(menorIdadePorPeriodo, maiorIdadePorPeriodo, mediaImc));
    }

    log.info("Busca media IMC por periodo de dez em dez anos finalizado");
    return lista;
  }

}
