package br.com.wk.abs.services.impl;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.enumerations.Genero;
import br.com.wk.abs.enumerations.TipoSanguineo;
import br.com.wk.abs.repositories.UsuarioRepository;
import br.com.wk.abs.services.UsuarioService;
import br.com.wk.abs.services.exceptions.BusinessException;
import br.com.wk.abs.services.exceptions.NotFoundException;
import br.com.wk.abs.vo.CandidatoVO;
import br.com.wk.abs.vo.DoadorVO;
import br.com.wk.abs.vo.FaixaEtariaVO;
import br.com.wk.abs.vo.GrupoSanguineoVO;
import br.com.wk.abs.vo.UsuarioImcVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    try {
      log.info("Salvando usuarios em lote...");
      repository.salvarEmLote(usuarios);
      log.info(String.format("Lote de usuarios salvos, total: %s", usuarios.size()));
    } catch (Exception e) {
      throw new BusinessException("Ocorreu um erro ao salvar usuários");
    }
  }

  @Transactional(readOnly = true)
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
    try{
      log.info("Busca media IMC por perio de dez em dez anos iniciado...");
      List<FaixaEtariaVO> lista = new ArrayList<>();
      Integer maiorIdade = repository.buscarMaiorIdade();
      int idadesPorFaixa = (maiorIdade / 10) + 1;

      for (int i=1; i <= idadesPorFaixa; i++) {
        int maiorIdadePorPeriodo = Integer.parseInt(i + "0");
        int menorIdadePorPeriodo = maiorIdadePorPeriodo == 10 ? 0 : maiorIdadePorPeriodo - 9;

        List<UsuarioImcVO> usuariosPorPeriodo = repository.buscarIMCPorIdade(menorIdadePorPeriodo,
            maiorIdadePorPeriodo);

        double totalImc = 0;
        for (UsuarioImcVO user : usuariosPorPeriodo) {
          totalImc += user.getImc();
        }

        BigDecimal mediaImc = BigDecimal.valueOf(
                totalImc == 0 ? 0 : totalImc / usuariosPorPeriodo.size())
            .setScale(2, RoundingMode.HALF_UP);

        log.info("Faixa de idade entre : " + menorIdadePorPeriodo + " - " + maiorIdadePorPeriodo
            + ", Media imc: " + mediaImc);
        lista.add(new FaixaEtariaVO(menorIdadePorPeriodo, maiorIdadePorPeriodo, mediaImc));
      }
      log.info("Busca media IMC por periodo de dez em dez anos finalizado");
      return lista;
    } catch (Exception e) {
      throw new BusinessException("Ocorreu um erro ao buscar a media do IMC por faixa etária de dez em dez anos");
    }
  }

  @Transactional(readOnly = true)
  @Override
  public Map<Genero, BigDecimal> buscarPercentualDeObesosPorGenero() {
    try{
      log.info("Busca de percentual de obesos por genero iniciado...");
      List<UsuarioImcVO> usuariosMasculinoImc = repository.buscarIMCDoUsuarioPorGenero(Genero.MASCULINO);
      List<UsuarioImcVO> usuariosFemininoImc = repository.buscarIMCDoUsuarioPorGenero(Genero.FEMININO);

      List<UsuarioImcVO> usuariosMasculinoObesos = usuariosMasculinoImc.stream()
          .filter(usuarioImcVO -> usuarioImcVO.getImc() > 30)
          .toList();
      List<UsuarioImcVO> usuariosFemininoObesos = usuariosFemininoImc.stream()
          .filter(usuarioImcVO -> usuarioImcVO.getImc() > 30)
          .toList();

      double percentualMasculino = ((double) (usuariosMasculinoObesos.size() * 100) / usuariosMasculinoImc.size());
      double percentualFeminino = ((double) (usuariosFemininoObesos.size() * 100) / usuariosFemininoImc.size());

      Map<Genero, BigDecimal> mapRestultado = new HashMap<>();
      mapRestultado.put(Genero.MASCULINO, BigDecimal.valueOf(percentualMasculino)
          .setScale(2, RoundingMode.HALF_UP));
      mapRestultado.put(Genero.FEMININO, BigDecimal.valueOf(percentualFeminino)
          .setScale(2, RoundingMode.HALF_UP));

      log.info("Busca de percentual de obesos por genero finalizado");

      return mapRestultado;
    } catch (Exception e) {
      throw new BusinessException("Ocorreu um erro ao buscar o percentual de obesos por gênero");
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<GrupoSanguineoVO> buscarMediaIdadePorTipoSanguineo() {
    log.info("Busca media de idades por tipo sanguineo iniciado...");
    List<GrupoSanguineoVO> grupoSanguineoVOS = repository.buscarMediaIdadePorTipoSanguineo();
    log.info("Busca media de idades por tipo sanguineo finalizado...");

    if(grupoSanguineoVOS.isEmpty()){ throw new NotFoundException("Media de idade por grupo sanguíneo não encontrado"); }

    return grupoSanguineoVOS;
  }

  @Transactional(readOnly = true)
  @Override
  public List<DoadorVO> buscarPossiveisDoadoresPorTipoSanguineo() {
    try{
      log.info("Busca quantidade possiveis de doadores por tipo sanguineo iniciado...");
      Integer menorIdade = 16;
      Integer maiorIdade = 69;
      Integer peso = 50;
      List<DoadorVO> grupos = new ArrayList<>();
      for(TipoSanguineo tipoSanguineo : TipoSanguineo.values()) {
        Long total = repository.buscarTotalDoaresPorTipoSanguineo(tipoSanguineo.gruposCompativeis(),
            menorIdade, maiorIdade, peso);
        grupos.add(new DoadorVO(tipoSanguineo, total));
      }
      log.info("Busca quantidade possiveis de doadores por tipo sanguineo finalizado...");
      return grupos;
    } catch (Exception e) {
      throw new BusinessException("Ocorreu um erro ao busacar a quantidade dos possíveis doadores");
    }
  }
}
