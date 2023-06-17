package br.com.wk.abs.controllers;

import br.com.wk.abs.controllers.dto.request.UsuarioRequestDTO;
import br.com.wk.abs.controllers.mapper.UsuarioMapper;
import br.com.wk.abs.controllers.openapi.UsuarioOpenApi;
import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.services.UsuarioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController implements UsuarioOpenApi {

  private final UsuarioService service;
  private final UsuarioMapper mapper;

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping("lotes")
  @Override
  public ResponseEntity<Void> salvarEmLote(@RequestBody List<UsuarioRequestDTO> usuarioRequestDTOS) {

    List<Usuario> usuarios = usuarioRequestDTOS.stream()
        .map(mapper::toUsuario)
        .toList();

    service.salvarEmLote(usuarios);

    return ResponseEntity.noContent().build();
  }

}
