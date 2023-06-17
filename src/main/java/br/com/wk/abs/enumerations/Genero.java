package br.com.wk.abs.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import org.springframework.util.ObjectUtils;

public enum Genero {

  MASCULINO("Masculino"),
  FEMININO("Feminino");

  private final String valor;
  Genero(String valor) { this.valor = valor; }

  @JsonValue
  public String getValor() { return this.valor; }

  @JsonCreator
  public static Genero converter(String valor) {
    if(!ObjectUtils.isEmpty(valor)) {
      for(Genero genero : values()) {
        if(genero.valor.equalsIgnoreCase(valor)) {
          return genero;
        }
      }
    }
    throw new IllegalArgumentException(
        String.format("Genero desconhecido '%s', Os valores permitidos são %s", valor,
            Arrays.toString(values())));
  }

  @Override
  public String toString() { return valor; }
}
