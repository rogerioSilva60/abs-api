package br.com.wk.abs.repositories;

import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.enumerations.Genero;
import br.com.wk.abs.enumerations.TipoSanguineo;
import br.com.wk.abs.repositories.queries.UsuarioRepositoryQuery;
import br.com.wk.abs.vo.CandidatoVO;
import br.com.wk.abs.vo.GrupoSanguineoVO;
import br.com.wk.abs.vo.UsuarioImcVO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {

  @Query("select new br.com.wk.abs.vo.CandidatoVO(count(u), u.endereco.estado) from Usuario u group by u.endereco.estado")
  List<CandidatoVO> buscarAgrupadoPorEstado();

  @Query("select new br.com.wk.abs.vo.UsuarioImcVO(u.nome, (datediff(current_date, u.dataNascimento) / 365) as idade, "
      + "(u.peso/(u.altura * u.altura)) as imc) from Usuario u order by (datediff(current_date, u.dataNascimento) / 365) asc")
  List<UsuarioImcVO> buscarIMCPorUsuario();

  @Query("select new br.com.wk.abs.vo.UsuarioImcVO(u.nome, (datediff(current_date, u.dataNascimento) / 365) as idade, (u.peso/(u.altura * u.altura)) as imc) "
      + "from Usuario u where u.genero=?1 "
      + "order by (datediff(current_date, u.dataNascimento) / 365)")
  List<UsuarioImcVO> buscarIMCDoUsuarioPorGenero(Genero genero);

  @Query("select new br.com.wk.abs.vo.UsuarioImcVO((datediff(current_date, u.dataNascimento) / 365) as idade, "
      + "(u.peso/(u.altura * u.altura)) as imc) from Usuario u where (datediff(current_date, u.dataNascimento) / 365) between ?1 and ?2 "
      + "order by (datediff(current_date, u.dataNascimento) / 365) asc")
  List<UsuarioImcVO> buscarIMCPorIdade(int menroIdade, int maiorIdade);

  @Query("select new br.com.wk.abs.vo.GrupoSanguineoVO(u.tipoSanguineo, (sum((datediff(current_date, u.dataNascimento) / 365)) / count(u.tipoSanguineo))) from Usuario u "
      + "group by u.tipoSanguineo order by u.tipoSanguineo asc")
  List<GrupoSanguineoVO> buscarMediaIdadePorTipoSanguineo();

  @Query("select count(u) from Usuario u where u.tipoSanguineo in (?1) "
      + "and (datediff(current_date, u.dataNascimento) / 365) between ?2 and ?3 and u.peso > ?4")
  Long buscarTotalDoaresPorTipoSanguineo(List<TipoSanguineo> tipoSanguineos,
      Integer menorIdade, Integer maiorIdade, Integer peso);

  @Query("select max((datediff(current_date, u.dataNascimento) / 365)) as maior_idade from Usuario u")
  Integer buscarMaiorIdade();

}
