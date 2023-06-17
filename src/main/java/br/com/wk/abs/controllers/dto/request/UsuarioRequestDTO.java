package br.com.wk.abs.controllers.dto.request;

import br.com.wk.abs.entities.Endereco;
import br.com.wk.abs.enumerations.Genero;
import br.com.wk.abs.enumerations.TipoSanguineo;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Data
public class UsuarioRequestDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String nome;

  private String cpf;

  private String rg;

  private LocalDate dataNascimento;

  private Genero genero;

  private String mae;

  private String pai;

  private String email;

  private String cep;

  private String endereco;

  private Integer numero;

  private String bairro;

  private String cidade;

  private String estado;

  private String telefoneFixo;

  private String celular;

  private Double altura;

  private Integer peso;

  private TipoSanguineo tipoSanguineo;

}
