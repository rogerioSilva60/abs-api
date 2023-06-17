package br.com.wk.abs.controllers.dto.request;

import br.com.wk.abs.enumerations.Genero;
import br.com.wk.abs.enumerations.TipoSanguineo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Schema(name = "Usuario entrada")
@Data
public class UsuarioRequestDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String nome;

  private String cpf;

  private String rg;

  @Schema(example = "17/06/2023")
  @JsonProperty(value = "data_nasc")
  private LocalDate dataNascimento;

  @JsonProperty(value = "sexo")
  private Genero genero;

  private String mae;

  private String pai;

  @NotBlank
  private String email;

  private String cep;

  private String endereco;

  private Integer numero;

  private String bairro;

  private String cidade;

  private String estado;

  @JsonProperty(value = "telefone_fixo")
  private String telefoneFixo;

  private String celular;

  private Double altura;

  private Integer peso;

  @JsonProperty(value = "tipo_sanguineo")
  private TipoSanguineo tipoSanguineo;

}
