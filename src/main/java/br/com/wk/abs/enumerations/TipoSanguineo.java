package br.com.wk.abs.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import org.springframework.util.ObjectUtils;

public enum TipoSanguineo {

  A_POSITIVO("A+"),
  A_NEGATIVO("A-"),
  B_POSITIVO("B+"),
  B_NEGATIVO("B-"),
  AB_POSITIVO("AB+"),
  AB_NEGATIVO("AB-"),
  O_POSITIVO("O+"),
  O_NEGATIVO("O-");

  private final String valor;

  TipoSanguineo(String valor) { this.valor = valor; }

  @JsonValue
  public String getValor() { return this.valor; }

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
