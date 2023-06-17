package br.com.wk.abs.services;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.vo.CandidatoVO;
import br.com.wk.abs.vo.FaixaEtariaVO;
import java.util.List;

public interface UsuarioService {

  void salvarEmLote(List<Usuario> usuarios);

  List<CandidatoVO> buscarAgrupadoPorEstado();

  List<FaixaEtariaVO> buscarMediaImcPorFaixaEtariaDeDezEmDezAnos();

}
