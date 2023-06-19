package br.com.wk.abs.controllers.exceptions;

import br.com.wk.abs.controllers.exceptions.PadraoErro.PadraoErroBuilder;
import br.com.wk.abs.services.exceptions.BusinessException;
import br.com.wk.abs.services.exceptions.NotFoundException;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<PadraoErro> businessException(BusinessException e) {
    String erro = "Erro de negócio";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    PadraoErro resposta = criarPadraoErroBuilder(status, erro, e.getMessage()).build();
    return ResponseEntity.status(status).body(resposta);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<PadraoErro> notFoundException(NotFoundException e) {
    String erro = "Recurso não encontrado";
    HttpStatus status = HttpStatus.NOT_FOUND;
    PadraoErro resposta = criarPadraoErroBuilder(status, erro, e.getMessage()).build();
    return ResponseEntity.status(status).body(resposta);
  }

  private PadraoErroBuilder criarPadraoErroBuilder(HttpStatus status, String erro, String mensagem) {
    return PadraoErro.builder()
        .timestamp(Instant.now())
        .status(status.value())
        .erro(erro)
        .mensagem(mensagem);
  }

}
