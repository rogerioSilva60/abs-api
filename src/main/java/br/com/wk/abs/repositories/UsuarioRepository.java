package br.com.wk.abs.repositories;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.repositories.queries.UsuarioRepositoryQuery;
import br.com.wk.abs.vo.CandidatoVO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {

  @Query("select new br.com.wk.abs.vo.CandidatoVO(count(u), u.endereco.estado) from Usuario u group by u.endereco.estado")
  List<CandidatoVO> buscarAgrupadoPorEstado();

}
