package br.com.wk.abs.controllers.dto.response;

import br.com.wk.abs.entities.Endereco;
import br.com.wk.abs.enumerations.Genero;
import br.com.wk.abs.enumerations.TipoSanguineo;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
public class UsuarioResponseDTO implements Serializable {

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

  private Endereco endereco;

  private String telefoneFixo;

  private String celular;

  private Double altura;

  private Integer peso;

  private TipoSanguineo tipoSanguineo;

}
