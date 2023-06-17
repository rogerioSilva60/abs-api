package br.com.wk.abs.entities;


import br.com.wk.abs.enumerations.Genero;
import br.com.wk.abs.enumerations.TipoSanguineo;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario",
    uniqueConstraints = { @UniqueConstraint( name = "unique_usuario_email", columnNames = { "email" }) })
public class Usuario implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false, length = 14)
  private String cpf;

  @Column(nullable = false, length = 12)
  private String rg;

  @Column(name = "data_nascimento", nullable = false)
  private LocalDate dataNascimento;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Genero genero;

  @Column(nullable = false)
  private String mae;

  @Column(nullable = false)
  private String pai;

  @Column(nullable = false)
  private String email;

  @Embedded
  private Endereco endereco;

  @Column(name = "telefone_fixo", nullable = false, length = 14)
  private String telefoneFixo;

  @Column(nullable = false, length = 15)
  private String celular;

  @Column(nullable = false)
  private Double altura;

  @Column(nullable = false)
  private Integer peso;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_sanguineo", nullable = false)
  private TipoSanguineo tipoSanguineo;

  @CreationTimestamp
  @Column(name="data_criacao", updatable = false, nullable = false)
  private OffsetDateTime dataCriacao;

  @UpdateTimestamp
  @Column(name = "data_ultima_atualizacao", insertable = false)
  private OffsetDateTime dataUltimaAtualizacao;

}
