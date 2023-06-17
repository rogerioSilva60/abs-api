package br.com.wk.abs.entities;


import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {

  private String cep;
  private String logradouro;
  private Integer numero;
  private String bairro;
  private String cidade;
  private String estado;

}
