package br.com.wk.abs.entities;


import javax.persistence.Column;
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

  @Column(nullable = false, length = 9)
  private String cep;

  @Column(nullable = false)
  private String logradouro;

  @Column(nullable = false)
  private Integer numero;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  private String cidade;

  @Column(nullable = false)
  private String estado;

}
