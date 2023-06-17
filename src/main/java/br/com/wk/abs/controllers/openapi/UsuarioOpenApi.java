package br.com.wk.abs.controllers.openapi;

import br.com.wk.abs.controllers.dto.request.UsuarioRequestDTO;
import br.com.wk.abs.controllers.dto.response.CandidatoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuário", description = "Serviços relacionado ao usuário")
public interface UsuarioOpenApi {

  @Operation(summary = "Salvar um lote de usuários", description = "Deve salvar um lote de usuários, "
      + "Obs: caso retorne status 204 significa que o lote foi salvo.")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "204", description = "Nenhum conteúdo", content = @Content
          ),
          @ApiResponse (
              responseCode = "400", description = "Requisição inválida", content = @Content
          ),
          @ApiResponse (
              responseCode = "500", description = "Ocorreu erro interno", content = @Content
          )
      }
  )
  ResponseEntity<Void> salvarEmLote(List<UsuarioRequestDTO> usuarioRequestDTOS);

  @Operation(summary = "Agrupar candidatos por estado", description = "Deve retornar todos os candidatos agrupados por estado")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200", description = "Ok", content = @Content
          ),
          @ApiResponse (
              responseCode = "500", description = "Ocorreu erro interno", content = @Content
          )
      }
  )
  ResponseEntity<List<CandidatoResponseDTO>> buscarAgrupadoPorEstado();
}
