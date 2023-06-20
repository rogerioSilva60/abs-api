package br.com.wk.abs.mother;

import br.com.wk.abs.entities.Endereco;
import br.com.wk.abs.entities.Usuario;
import br.com.wk.abs.enumerations.Genero;
import br.com.wk.abs.enumerations.TipoSanguineo;
import java.time.LocalDate;
import java.util.List;

public abstract class UsuarioMother {

  private static final long ID = 1L;
  private static final String NOME = "Milena Analu Pires";
  private static final String CPF = "775.256.099-50";
  private static final String RG = "44.084.541-5";
  private static final LocalDate DATA_NASCIMENTO = LocalDate.of(1964, 5,23);
  private static final Genero GENERO = Genero.FEMININO;
  private static final String MAE = "Isadora Marli";
  private static final String PAI = "Noah Severino César Pires";
  private static final String EMAIL = "mnilenaanalupires@keffin.com.br";
  private static final String CEP = "39801-678";
  private static final String LOGRADOURO = "Rua Kurt W. Rothe";
  private static final Integer NUMERO = 675;
  private static final String BAIRRO = "Castro Pires";
  private static final String CIDADE = "Teófilo Otoni";
  private static final String ESTADO = "MG";
  private static final String TELEFONE_FIXO = "(33) 3611-4613";
  private static final String CELULAR = "(33) 98481-0191";
  private static final Double ALTURA = 1.53;
  private static final Integer PESO = 80;
  private static final TipoSanguineo TIPO_SANGUINEO = TipoSanguineo.O_NEGATIVO;
  public static Usuario usuario;
  public static Endereco endereco;
  public static List<Usuario> usuarios;

  public static void startUsuario() {
    endereco = new Endereco();
    endereco.setCep(CEP);
    endereco.setLogradouro(LOGRADOURO);
    endereco.setNumero(NUMERO);
    endereco.setBairro(BAIRRO);
    endereco.setCidade(CIDADE);
    endereco.setEstado(ESTADO);

    usuario = new Usuario();
    usuario.setId(ID);
    usuario.setNome(NOME);
    usuario.setCpf(CPF);
    usuario.setRg(RG);
    usuario.setDataNascimento(DATA_NASCIMENTO);
    usuario.setGenero(GENERO);
    usuario.setMae(MAE);
    usuario.setPai(PAI);
    usuario.setEmail(EMAIL);
    usuario.setEndereco(endereco);
    usuario.setTelefoneFixo(TELEFONE_FIXO);
    usuario.setCelular(CELULAR);
    usuario.setAltura(ALTURA);
    usuario.setPeso(PESO);
    usuario.setTipoSanguineo(TIPO_SANGUINEO);

    usuarios = List.of(usuario);
  }
}
