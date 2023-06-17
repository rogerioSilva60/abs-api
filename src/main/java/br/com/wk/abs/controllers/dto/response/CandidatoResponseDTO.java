package br.com.wk.abs.controllers.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Schema(name = "Candidato saída")
@Data
public class CandidatoResponseDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long total;
  private String estado;
}
