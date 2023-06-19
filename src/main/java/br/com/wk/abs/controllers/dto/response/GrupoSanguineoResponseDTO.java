package br.com.wk.abs.controllers.dto.response;

import br.com.wk.abs.enumerations.TipoSanguineo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Grupo sanguíneo saída")
@Data
public class GrupoSanguineoResponseDTO {

  private TipoSanguineo tipoSanguineo;
  private Long mediaIdade;

}
