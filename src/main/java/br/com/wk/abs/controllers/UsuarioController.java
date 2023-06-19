package br.com.wk.abs.controllers;

import br.com.wk.abs.controllers.dto.request.UsuarioRequestDTO;
import br.com.wk.abs.controllers.dto.response.CandidatoResponseDTO;
import br.com.wk.abs.controllers.dto.response.FaixaEtariaResponseDTO;
import br.com.wk.abs.controllers.mapper.UsuarioMapper;
import br.com.wk.abs.controllers.openapi.UsuarioOpenApi;
import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.enumerations.Genero;
import br.com.wk.abs.services.UsuarioService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("agrupado-por-estado")
  @Override
  public ResponseEntity<List<CandidatoResponseDTO>> buscarAgrupadoPorEstado() {
    List<CandidatoResponseDTO> candidatos = service.buscarAgrupadoPorEstado().stream()
        .map(mapper::toCandidatoResponseDTO)
        .toList();
    return ResponseEntity.ok(candidatos);
  }

  @GetMapping("imc-faixa-etaria-dez-em-dez-anos")
  @Override
  public ResponseEntity<List<FaixaEtariaResponseDTO>> buscarMediaImcPorFaixaEtariaDeDezEmDezAnos() {
    List<FaixaEtariaResponseDTO> faixaEtarias = service.buscarMediaImcPorFaixaEtariaDeDezEmDezAnos()
        .stream()
        .map(mapper::toFaixaEtariaResponseDTO)
        .toList();

    return ResponseEntity.ok(faixaEtarias);
  }

  @GetMapping("percentual-obeso-por-genero")
  public ResponseEntity<Map<Genero, BigDecimal>> buscarPercentualObeso() {
    Map<Genero, BigDecimal> generoObesoImcVOMap = service.buscarPercentualDeObesosPorGenero();
    return ResponseEntity.ok(generoObesoImcVOMap);
  }
}
