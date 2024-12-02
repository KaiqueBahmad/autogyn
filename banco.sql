CREATE TABLE Marca(
	id_marca serial PRIMARY KEY,
	nome varchar(255) UNIQUE NOT NULL
);

CREATE TABLE Modelo(
	id_modelo serial PRIMARY KEY,
	nome varchar(255) UNIQUE NOT NULL,
	id_marca int NOT NULL,
	FOREIGN KEY(id_marca) REFERENCES Marca(id_marca)
);

CREATE TABLE Acessorio(
	id_acessorio serial PRIMARY KEY,
	descricao varchar(255) NOT NULL UNIQUE
);

CREATE TABLE Veiculo(
	placa varchar(12) primary key,
    ano_fabricacao int NOT NULL,
	num_chassi varchar(17) NOT NULL UNIQUE,
	km int not null,
	num_patrimonio int NOT NULL UNIQUE,
	ano_modelo int NOT NULL,
	id_modelo int NOT NULL,
	FOREIGN KEY (id_modelo) REFERENCES Modelo(id_modelo)
);

CREATE TABLE Acessorio_veiculo(
	placa varchar(12) NOT NULL,
	id_acessorio int NOT NULL,
	PRIMARY key(placa, id_acessorio),
	FOREIGN KEY (placa) REFERENCES Veiculo(placa),
	FOREIGN KEY (id_acessorio) REFERENCES Acessorio(id_acessorio)
);

CREATE TABLE Servico(
	id_servico serial primary key,
	descricao varchar(255) not null,
    valor float not null
);

create table Colaborador(
	cpf varchar(11) primary key,
	nome varchar(255) not null
);

create table Cliente(
	id_cliente serial primary key,
	nome varchar(255) not null,
	logradouro varchar(255) not null,
	complemento varchar(255) not null,
	numero varchar(50) not null,
	cep varchar(10) not null,
	cidade varchar(255) not null,
	uf varchar(2) not null,
	ddd int not null,
	telefone int not null,
	ddd2 int null,
	telefone2 int null,
	email varchar(255) not null unique,
	cpf varchar(11) null unique,
	cnpj varchar(20) null unique,
	inscricao_estadual varchar(255) null unique,
	contato varchar(255) null
);

create table Ordem_servico(
	id_os serial primary key,
	data date not null,
	valor_total float null,
    valor_pago float null,
	etapa varchar(30) null,
	placa varchar(12) NOT NULL,
	check(etapa in ('Orcamento', 'Aprovado', 'Execucao', 'Finalizado', 'Pago')),
	id_cliente int not null,
	foreign key (id_cliente) references Cliente(id_cliente),
	FOREIGN KEY (placa) REFERENCES Veiculo(placa)
);

create table Item_servico(
	id_item_servico serial primary key,
	valor_total float not null,
	data_inicio date not null,
	data_fim date null,
	quantidade int not null,
	valor_unitario float not null,
	id_servico int not null,
	cpf_colaborador varchar(11) not null,
	id_os int NOT NULL,
	FOREIGN KEY (id_os) REFERENCES Ordem_servico(id_os),
	foreign key (cpf_colaborador) references Colaborador(cpf),	
	foreign key (id_servico) references Servico(id_servico)
);

CREATE TABLE Propriedade (
	id_propriedade serial PRIMARY KEY,
	data_inicio date NOT NULL,
	data_fim date NULL,
	placa varchar(12) NOT NULL,
	id_cliente int NOT NULL,
	FOREIGN KEY (placa) REFERENCES veiculo(placa),
	FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE Pecas(
	id_peca serial PRIMARY KEY,
	codigo varchar(255) NOT NULL UNIQUE,
	descricao varchar(255) NOT NULL,
	sku varchar(255) NOT NULL UNIQUE,
	quantidade_estoque int NOT NULL,
	valor_unitario float NOT NULL
);

 CREATE TABLE Item_peca(
	id_item_peca serial PRIMARY KEY,
	quantidade int NOT NULL,
	valor_total float NOT NULL,
	valor_unitario float NOT NULL,
	id_os int NOT NULL,
	id_peca int NOT NULL,
	FOREIGN KEY (id_peca) REFERENCES Pecas (id_peca),
	FOREIGN KEY (id_os) REFERENCES  Ordem_servico (id_os)
 );
