package br.com.wk.abs.services.impl;

import static br.com.wk.abs.mother.UsuarioMother.CANDIDATO_ESTADO;
import static br.com.wk.abs.mother.UsuarioMother.CANDIDATO_TOTAL;
import static br.com.wk.abs.mother.UsuarioMother.INDEX;
import static br.com.wk.abs.mother.UsuarioMother.candidatoVO;
import static br.com.wk.abs.mother.UsuarioMother.candidatoVOS;
import static br.com.wk.abs.mother.UsuarioMother.startCandidatoVo;
import static br.com.wk.abs.mother.UsuarioMother.startUsuario;
import static br.com.wk.abs.mother.UsuarioMother.usuarios;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.wk.abs.repositories.UsuarioRepository;
import br.com.wk.abs.services.UsuarioService;
import br.com.wk.abs.services.exceptions.BusinessException;
import br.com.wk.abs.services.exceptions.NotFoundException;
import br.com.wk.abs.vo.CandidatoVO;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UsuarioServiceImplTest {

  private UsuarioService service;

  @MockBean
  private UsuarioRepository repository;

  @BeforeEach
  void setUp() {
    service = new UsuarioServiceImpl(repository);
    startUsuario();
    startCandidatoVo();
  }

  @Test
  void givenUsuariosWhenSalvarEmLoteThenReturnSuccess() {
    doNothing().when(repository).salvarEmLote(usuarios);

    service.salvarEmLote(usuarios);

    verify(repository, times(1)).salvarEmLote(usuarios);
  }

  @Test
  void givenUsuariosWhenSalvarEmLoteThenReturnAnBusinessException() {
    doThrow(new RuntimeException()).when(repository).salvarEmLote(any());

    Throwable exception = Assertions.catchThrowable(() -> service.salvarEmLote(new ArrayList<>()));

    assertThat(exception)
        .isInstanceOf(BusinessException.class)
        .hasMessage("Ocorreu um erro ao salvar usuários");
  }

  @Test
  void givenBuscaWhenBuscarAgrupadoPorEstadoThenReturnSuccess() {
    when(repository.buscarAgrupadoPorEstado()).thenReturn(candidatoVOS);

    List<CandidatoVO> resposta = service.buscarAgrupadoPorEstado();

    assertNotNull(resposta);
    assertEquals(1, resposta.size());
    assertEquals(CandidatoVO.class, resposta.get(INDEX).getClass());
    assertEquals(CANDIDATO_ESTADO, resposta.get(INDEX).getEstado());
    assertEquals(CANDIDATO_TOTAL, resposta.get(INDEX).getTotal());
  }

  @Test
  void givenBuscaWhenBuscarAgrupadoPorEstadoThenReturnAnNotFoundException() {
    when(repository.buscarAgrupadoPorEstado()).thenReturn(new ArrayList<>());

    Throwable exception = Assertions.catchThrowable(() -> service.buscarAgrupadoPorEstado());

    assertThat(exception)
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Candidatos não encontrado(s)");
  }
}