package br.com.wk.abs.services.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public NotFoundException() {
    super("Não encontrado");
  }

  public NotFoundException(String message) {
    super(message);
  }

}
