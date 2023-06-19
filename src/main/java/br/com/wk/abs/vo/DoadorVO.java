package br.com.wk.abs.vo;

import br.com.wk.abs.enumerations.TipoSanguineo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoadorVO {

  private TipoSanguineo tipoSanguineo;
  private Long quantidade;

}
