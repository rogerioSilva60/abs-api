package br.com.wk.abs.controllers.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Schema(name = "Faixa etária saída")
@Data
public class FaixaEtariaResponseDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Integer menorIdade;
  private Integer maiorIdade;
  private BigDecimal mediaImc;

}
