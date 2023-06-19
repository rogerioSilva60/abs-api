package br.com.wk.abs.controllers.mapper;

import br.com.wk.abs.controllers.dto.request.UsuarioRequestDTO;
import br.com.wk.abs.controllers.dto.response.CandidatoResponseDTO;
import br.com.wk.abs.controllers.dto.response.DoadorResponseDTO;
import br.com.wk.abs.controllers.dto.response.FaixaEtariaResponseDTO;
import br.com.wk.abs.controllers.dto.response.GrupoSanguineoResponseDTO;
import br.com.wk.abs.controllers.dto.response.UsuarioResponseDTO;
import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.vo.CandidatoVO;
import br.com.wk.abs.vo.DoadorVO;
import br.com.wk.abs.vo.FaixaEtariaVO;
import br.com.wk.abs.vo.GrupoSanguineoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UsuarioMapper {

  @Mapping(source = "endereco", target = "endereco.logradouro")
  @Mapping(source = "cep", target = "endereco.cep")
  @Mapping(source = "numero", target = "endereco.numero")
  @Mapping(source = "bairro", target = "endereco.bairro")
  @Mapping(source = "cidade", target = "endereco.cidade")
  @Mapping(source = "estado", target = "endereco.estado")
  Usuario toUsuario(UsuarioRequestDTO usuarioRequestDTO);

  UsuarioResponseDTO toUsuarioResponseDTO(UsuarioResponseDTO usuarioResponseDTO);

  CandidatoResponseDTO toCandidatoResponseDTO(CandidatoVO candidatoVO);

  FaixaEtariaResponseDTO toFaixaEtariaResponseDTO(FaixaEtariaVO faixaEtariaVO);

  GrupoSanguineoResponseDTO toGrupoSanguineoResponseDTO(GrupoSanguineoVO grupoSanguineoVO);

  DoadorResponseDTO toDoadorResponseDTO(DoadorVO doadorVO);
}
