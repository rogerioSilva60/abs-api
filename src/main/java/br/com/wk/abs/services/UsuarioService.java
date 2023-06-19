package br.com.wk.abs.services;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.enumerations.Genero;
import br.com.wk.abs.vo.CandidatoVO;
import br.com.wk.abs.vo.FaixaEtariaVO;
import br.com.wk.abs.vo.GrupoSanguineoVO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UsuarioService {

  void salvarEmLote(List<Usuario> usuarios);

  List<CandidatoVO> buscarAgrupadoPorEstado();

  List<FaixaEtariaVO> buscarMediaImcPorFaixaEtariaDeDezEmDezAnos();

  Map<Genero, BigDecimal> buscarPercentualDeObesosPorGenero();

  List<GrupoSanguineoVO> buscarMediaIdadePorTipoSanguineo();

}
