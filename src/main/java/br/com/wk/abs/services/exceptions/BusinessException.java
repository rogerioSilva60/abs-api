package br.com.wk.abs.services.exceptions;

public class BusinessException extends RuntimeException {

  private static final long seriaVersionUID = 1L;

  public BusinessException(String message) {
    super(message);
  }
}
