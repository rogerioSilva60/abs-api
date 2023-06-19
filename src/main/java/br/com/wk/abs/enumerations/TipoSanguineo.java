package br.com.wk.abs.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.ObjectUtils;

@Schema(type = "string", allowableValues = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" })
public enum TipoSanguineo {

  A_POSITIVO("A+") {
    @Override
    public List<TipoSanguineo> gruposCompativeis() {
      return List.of(A_POSITIVO,A_NEGATIVO,O_POSITIVO, O_NEGATIVO);
    }
  },
  A_NEGATIVO("A-") {
    @Override
    public List<TipoSanguineo> gruposCompativeis() {
      return List.of(A_NEGATIVO, O_NEGATIVO);
    }
  },
  B_POSITIVO("B+") {
    @Override
    public List<TipoSanguineo> gruposCompativeis() {
      return List.of(B_POSITIVO, B_NEGATIVO, O_POSITIVO, O_NEGATIVO);
    }
  },
  B_NEGATIVO("B-") {
    @Override
    public List<TipoSanguineo> gruposCompativeis() {
      return List.of(B_NEGATIVO, O_NEGATIVO);
    }
  },
  AB_POSITIVO("AB+") {
    @Override
    public List<TipoSanguineo> gruposCompativeis() {
      return List.of(A_POSITIVO, B_POSITIVO, O_POSITIVO, AB_POSITIVO, A_NEGATIVO, B_NEGATIVO, O_NEGATIVO, AB_NEGATIVO);
    }
  },
  AB_NEGATIVO("AB-") {
    @Override
    public List<TipoSanguineo> gruposCompativeis() {
      return List.of(A_NEGATIVO, B_NEGATIVO, O_NEGATIVO, AB_NEGATIVO);
    }
  },
  O_POSITIVO("O+") {
    @Override
    public List<TipoSanguineo> gruposCompativeis() {
      return List.of(O_POSITIVO, O_NEGATIVO);
    }
  },
  O_NEGATIVO("O-") {
    @Override
    public List<TipoSanguineo> gruposCompativeis() {
      return List.of(O_NEGATIVO);
    }
  };

  private final String valor;

  TipoSanguineo(String valor) { this.valor = valor; }

  @JsonValue
  public String getValor() { return this.valor; }

  public List<TipoSanguineo> gruposCompativeis() { return new ArrayList<>(); }

//  public static List<TipoSanguineo> obterGruposCompativeisParaAPositivo() {
//    return List.of(A_POSITIVO,A_NEGATIVO,O_POSITIVO, O_NEGATIVO);
//  }
//  public static List<TipoSanguineo> obterGruposCompativeisParaANegativo() {
//    return List.of(A_NEGATIVO, O_NEGATIVO);
//  }
//  public static List<TipoSanguineo> obterGruposCompativeisParaBPositivo() {
//    return List.of(B_POSITIVO, B_NEGATIVO, O_POSITIVO, O_NEGATIVO);
//  }
//  public static List<TipoSanguineo> obterGruposCompativeisParaBNegativo() {
//    return List.of(B_NEGATIVO, O_NEGATIVO);
//  }
//  public static List<TipoSanguineo> obterGruposCompativeisParaABPositivo() {
//    return List.of(A_POSITIVO, B_POSITIVO, O_POSITIVO, AB_POSITIVO, A_NEGATIVO, B_NEGATIVO, O_NEGATIVO, AB_NEGATIVO);
//  }
//  public static List<TipoSanguineo> obterGruposCompativeisParaABNegativo() {
//    return List.of(A_NEGATIVO, B_NEGATIVO, O_NEGATIVO, AB_NEGATIVO);
//  }
//  public static List<TipoSanguineo> obterGruposCompativeisParaOPositivo() {
//    return List.of(O_POSITIVO, O_NEGATIVO);
//  }
//  public static List<TipoSanguineo> obterGruposCompativeisParaONegativo() {
//    return List.of(O_NEGATIVO);
//  }

  @JsonCreator
  public static TipoSanguineo converter(String valor) {
    if(!ObjectUtils.isEmpty(valor)) {
      for(TipoSanguineo tipoSanguineo : values()) {
        if(tipoSanguineo.valor.equalsIgnoreCase(valor)) {
          return tipoSanguineo;
        }
      }
    }
    throw new IllegalArgumentException(
        String.format("Tipo sanguineo desconhecido '%s', Os valores permitidos são %s", valor,
            Arrays.toString(values())));
  }

  @Override
  public String toString() { return valor; }
}
