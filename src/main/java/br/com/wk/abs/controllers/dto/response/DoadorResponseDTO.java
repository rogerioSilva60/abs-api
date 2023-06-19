package br.com.wk.abs.controllers.dto.response;

import br.com.wk.abs.enumerations.TipoSanguineo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Doador saída")
@Data
public class DoadorResponseDTO {

  private TipoSanguineo tipoSanguineo;
  private Long quantidade;

}
