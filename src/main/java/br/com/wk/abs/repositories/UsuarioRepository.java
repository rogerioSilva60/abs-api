package br.com.wk.abs.repositories;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.repositories.queries.UsuarioRepositoryQuery;
import br.com.wk.abs.vo.CandidatoVO;
import br.com.wk.abs.vo.UsuarioImcVO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {

  @Query("select new br.com.wk.abs.vo.CandidatoVO(count(u), u.endereco.estado) from Usuario u group by u.endereco.estado")
  List<CandidatoVO> buscarAgrupadoPorEstado();

  @Query("select new br.com.wk.abs.vo.UsuarioImcVO(u.nome, datediff(yy, u.dataNascimento, current_date) as idade, "
      + "(u.peso/(u.altura * u.altura)) as imc) from Usuario u order by datediff(yy, u.dataNascimento, current_date) asc")
  List<UsuarioImcVO> buscarIMCPorUsuario();

  @Query("select new br.com.wk.abs.vo.UsuarioImcVO(datediff(yy, u.dataNascimento, current_date) as idade, "
      + "(u.peso/(u.altura * u.altura)) as imc) from Usuario u where datediff(yy, u.dataNascimento, current_date) between ?1 and ?2 "
      + "order by datediff(yy, u.dataNascimento, current_date) asc")
  List<UsuarioImcVO> buscarIMCPorIdade(int menroIdade, int maiorIdade);

  @Query("select max(datediff(yy, u.dataNascimento, current_date)) as maior_idade from Usuario u")
  Integer buscarMaiorIdade();

}
