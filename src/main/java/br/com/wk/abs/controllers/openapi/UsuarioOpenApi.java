package br.com.wk.abs.controllers.openapi;

import br.com.wk.abs.controllers.dto.request.UsuarioRequestDTO;
import br.com.wk.abs.controllers.dto.response.CandidatoResponseDTO;
import br.com.wk.abs.controllers.dto.response.DoadorResponseDTO;
import br.com.wk.abs.controllers.dto.response.FaixaEtariaResponseDTO;
import br.com.wk.abs.controllers.dto.response.GrupoSanguineoResponseDTO;
import br.com.wk.abs.enumerations.Genero;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
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
              responseCode = "200", description = "Ok",
              content = {
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      array = @ArraySchema( schema = @Schema(implementation = CandidatoResponseDTO.class))
                  )
              }
          ),
          @ApiResponse (
              responseCode = "500", description = "Ocorreu erro interno", content = @Content
          )
      }
  )
  ResponseEntity<List<CandidatoResponseDTO>> buscarAgrupadoPorEstado();

  @Operation(summary = "Media Imc por faixa etária", description = "Deve retornar o IMC de todas as faixas etárias de dez em dez anos")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200", description = "Ok",
              content = {
                  @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema( schema = @Schema(implementation = FaixaEtariaResponseDTO.class))
                  )
              }
          ),
          @ApiResponse (
              responseCode = "500", description = "Ocorreu erro interno", content = @Content
          )
      }
  )
  ResponseEntity<List<FaixaEtariaResponseDTO>> buscarMediaImcPorFaixaEtariaDeDezEmDezAnos();

  @Operation(summary = "Percentual de obesos por gênero", description = "Deve retornar o percentual de obeso por gênero")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200", description = "Ok",
              content = {
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = Map.class)
                  )
              }
          ),
          @ApiResponse (
              responseCode = "500", description = "Ocorreu erro interno", content = @Content
          )
      }
  )
  ResponseEntity<Map<Genero, BigDecimal>> buscarPercentualObeso();

  @Operation(summary = "Media idade por grupo sanguineo", description = "Deve retornar a media de idade por grupo sanguineo")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200", description = "Ok",
              content = {
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      array = @ArraySchema( schema = @Schema(implementation = GrupoSanguineoResponseDTO.class))
                  )
              }
          ),
          @ApiResponse (
              responseCode = "500", description = "Ocorreu erro interno", content = @Content
          )
      }
  )
  ResponseEntity<List<GrupoSanguineoResponseDTO>> buscarMediaIdadeDoTipoSanguineo();

  @Operation(summary = "Quantidade de possíveis doadores por grupo sanguíneo",
      description = "Deve retornar a quantidade de possíveis doadores para um determinado grupo sanguíneo")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200", description = "Ok",
              content = {
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      array = @ArraySchema( schema = @Schema(implementation = DoadorResponseDTO.class))
                  )
              }
          ),
          @ApiResponse (
              responseCode = "500", description = "Ocorreu erro interno", content = @Content
          )
      }
  )
  ResponseEntity<List<DoadorResponseDTO>> buscarPossiveiDoadoresPorTipoSanguineo();
}
