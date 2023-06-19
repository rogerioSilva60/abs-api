package br.com.wk.abs.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class PadraoErro implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Instant timestamp;
  private Integer status;
  private String erro;
  private String mensagem;

}
