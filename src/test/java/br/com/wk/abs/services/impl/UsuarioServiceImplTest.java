package br.com.wk.abs.services.impl;

import static br.com.wk.abs.mother.UsuarioMother.startUsuario;
import static br.com.wk.abs.mother.UsuarioMother.usuarios;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.wk.abs.repositories.UsuarioRepository;
import br.com.wk.abs.services.UsuarioService;
import br.com.wk.abs.services.exceptions.BusinessException;
import java.util.ArrayList;
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
  }

  @Test
  void givenUsuariosWhenSalvarEmLoteThenReturnSuccess() {
    doNothing().when(repository).salvarEmLote(usuarios);

    service.salvarEmLote(usuarios);

    verify(repository, times(1)).salvarEmLote(usuarios);
  }

  @Test
  void givenUsuariosWhenSalvarEmLoteThenReturnBusinessException() {
    doThrow(new RuntimeException()).when(repository).salvarEmLote(any());

    Throwable exception = Assertions.catchThrowable(() -> service.salvarEmLote(new ArrayList<>()));

    assertThat(exception)
        .isInstanceOf(BusinessException.class)
        .hasMessage("Ocorreu um erro ao salvar usuários");
  }

}