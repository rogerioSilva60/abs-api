package br.com.wk.abs.vo;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FaixaEtariaVO {

  private Integer menorIdade;
  private Integer maiorIdade;
  private BigDecimal mediaImc;

}
