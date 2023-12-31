CREATE TABLE IF NOT EXISTS usuario (
  id BIGINT(20) AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  rg VARCHAR(12) NOT NULL,
  data_nascimento DATE NOT NULL,
  genero VARCHAR(255) NOT NULL,
  mae VARCHAR(255) NOT NULL,
  pai VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  cep VARCHAR(9) NOT NULL,
  logradouro VARCHAR(255) NOT NULL,
  numero INTEGER NOT NULL,
  bairro VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  estado VARCHAR(255) NOT NULL,
  telefone_fixo VARCHAR(14) NOT NULL,
  celular VARCHAR(15) NOT NULL,
  altura DOUBLE NOT NULL,
  peso INTEGER NOT NULL,
  tipo_sanguineo VARCHAR(255) NOT NULL,
  data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  data_ultima_atualizacao TIMESTAMP,
  CONSTRAINT unique_usuario_email UNIQUE (email),
  CONSTRAINT pk_users PRIMARY KEY (id)
);