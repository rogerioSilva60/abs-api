package br.com.wk.abs.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioImcVO {

  private String nome;
  private Integer idade;
  private Double imc;

  public UsuarioImcVO(Integer idade, Double imc) {
    this.idade = idade;
    this.imc = imc;
  }
}
