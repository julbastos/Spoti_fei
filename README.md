
# Spotifei

## Desenvolvido por
Júlia Bastos Barreto  
RA: 22124071-6

---

## Sumário
- [Descrição do Projeto](#descrição-do-projeto)  
- [Arquitetura e Estrutura de Pastas](#arquitetura-e-estrutura-de-pastas)  
- [Banco de Dados](#banco-de-dados)  
- [Principais Funcionalidades](#principais-funcionalidades)  
- [Principais Arquivos e Classes](#principais-arquivos-e-classes)  
- [Fluxo Geral do Projeto](#fluxo-geral-do-projeto)  
- [Resumo Técnico](#resumo-técnico)  
- [Camada View (Interface Gráfica)](#1-camada-view-interface-gráfica)  
- [Camada Database (Banco de Dados)](#2-camada-database-banco-de-dados)  
- [Funcionalidades e Lógicas Principais](#3-funcionalidades-e-lógicas-principais)  
- [Integração Geral](#4-integração-geral)  

---

## Descrição do Projeto

O Spotifei é uma aplicação desktop desenvolvida em Java. O objetivo é permitir que usuários possam criar playlists, curtir/descurtir músicas, visualizar histórico de ações e gerenciar músicas. O sistema utiliza uma arquitetura MVC (Model-View-Controller) e faz integração com um banco de dados PostgreSQL.

---

## Arquitetura e Estrutura de Pastas

O projeto segue a arquitetura MVC, separando claramente as responsabilidades de cada camada:

- **Model:** Contém as classes que representam os dados do sistema, como `Usuario`.
- **View:** Contém as interfaces gráficas (JFrames e formulários) que interagem com o usuário.
- **Controller:** Gerencia a lógica de negócio e faz a ponte entre a View e o Model.
- **Database:** Responsável pela conexão e manipulação do banco de dados.

### Estrutura de Pastas:

```
Spotifei/
│
├── src/
│   ├── Controller/
│   ├── Database/
│   ├── Model/
│   └── View/
│
├── DDL                # Script SQL para criação e popular o banco de dados
├── postgresql/        # Driver JDBC do PostgreSQL
├── build.xml          # Arquivo de build do NetBeans/Ant
├── nbproject/         # Configurações do NetBeans
└── ...
```

---

## Banco de Dados

O banco de dados utilizado é o PostgreSQL, hospedado no Supabase. O script DDL contém toda a estrutura de tabelas e dados de exemplo.

### Estrutura das Tabelas:

- **usuarios:** Armazena informações dos usuários (`id`, `nome`, `email`, `senha`).
- **musicas:** Armazena músicas disponíveis (`id`, `nome`, `artista`, `gênero`).
- **playlists:** Playlists criadas pelos usuários (`id`, `nome`, `id_usuario`).
- **playlist_musicas:** Relação N:N entre playlists e músicas.
- **curtidas:** Registro de músicas curtidas/descurtidas por usuários.
- **historico:** Histórico de ações dos usuários (curtidas, execuções, etc).

### Exemplo de criação de tabela:

```sql
CREATE TABLE public.usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);
```

### Dados de Exemplo

O script insere usuários, músicas, playlists, relações e curtidas para facilitar testes e demonstrações.

---

## Principais Funcionalidades

- Cadastro e Login de Usuário: Usuários podem se cadastrar e acessar o sistema.
- Criação de Playlists: Usuários podem criar, editar e remover playlists.
- Adicionar/Remover Músicas em Playlists: Gerenciamento dinâmico das playlists.
- Curtir/Descurtir Músicas: Registro de preferências do usuário.
- Histórico de Ações: Visualização do histórico de interações do usuário.
- Busca de Músicas: Pesquisa por músicas pelo nome.

---

## Principais Arquivos e Classes

### Model

Representa as entidades do domínio e suas operações de persistência.

1. **Usuario.java**  
   Entidade Usuário: contém atributos como nome, email, senha.  
   Métodos: getters, setters e métodos auxiliares.

2. **UsuarioBanco.java**  
   Persistência de Usuário: responsável por inserir, buscar, validar login e obter informações do usuário no banco de dados.  
   Métodos principais:  
   - `inserirUsuario(Usuario usuario)`  
   - `validarLogin(email, senha)`  
   - `obterIdUsuario(email)`  
   - `obterNomeUsuario(email)`

3. **Musica.java**  
   Entidade Música: representa uma música com id, nome, artista, gênero, etc.

4. **MusicaBanco.java**  
   Persistência de Música: métodos para buscar, inserir, remover músicas.  
   Exemplo: `buscarMusicas(termo, idUsuario)` retorna lista de músicas conforme o termo.

5. **Playlist.java**  
   Entidade Playlist: representa uma playlist com id, nome, lista de músicas, etc.

6. **PlaylistBanco.java**  
   Persistência de Playlist: métodos para criar, editar, excluir e buscar playlists do usuário.

7. **Historico.java**  
   Entidade Histórico: representa ações do usuário, como curtir/descurtir músicas.

8. **HistoricoBanco.java**  
   Persistência de Histórico: métodos para registrar e buscar histórico.

9. **CurtidaBanco.java**  
   Persistência de Curtidas: métodos para curtir/descurtir músicas, registrar ações e consultar músicas curtidas.

---

### Controller

Arquivo: `Controller.java`

O Controller é o cérebro da aplicação, responsável pela interação entre as telas (View) e as regras de negócio (Model). Ele gerencia o fluxo do usuário, incluindo cadastro, login, busca de músicas, curtidas, descurtidas, histórico, playlists, etc.

#### Principais funcionalidades:

- **Cadastro de Usuário:** Recebe dados da tela de cadastro, cria objeto `Usuario` e usa `UsuarioBanco` para inserir no banco. Em caso de sucesso, exibe mensagem e direciona para o menu principal.
- **Login:** Valida login via `UsuarioBanco`. Se válido, carrega dados do usuário e abre menu principal.
- **Gerenciamento de Telas:** Métodos para abrir/fechar telas como inicial, login, cadastro, menu, histórico, curtidas, playlists.
- **Pesquisa de Músicas:** Recebe termo da interface, consulta o banco via `MusicaBanco` e exibe resultados, permitindo curtir/descurtir.
- **Histórico:** Carrega histórico de ações via `HistoricoBanco` e exibe em tabela.
- **Curtidas/Descurtidas:** Permite ao usuário curtir ou descurtir músicas, usando `CurtidaBanco`.
- **Playlists:** Gerencia criação, edição, exclusão e visualização de playlists, interagindo com `PlaylistBanco` e `MusicaBanco`.

#### Lógica de funcionamento:

- Mantém referências para todas as telas (Views) e para o usuário logado.
- Toda ação do usuário nas telas é encaminhada para métodos do Controller, que processa a lógica e atualiza as telas.
- Faz a ponte entre Views e Models, garantindo separação de responsabilidades.

---

## Fluxo Geral do Projeto

1. Usuário inicia o app: Controller exibe tela inicial.
2. Cadastro/Login: usuário pode se cadastrar ou logar. Controller valida e direciona o fluxo.
3. Menu Principal: usuário pode buscar músicas, ver playlists, histórico, curtidas.
4. Ações: todas as ações (curtir, descurtir, adicionar à playlist) são processadas pelo Controller, que usa Models para acessar o banco e atualizar Views.
5. Persistência: manipulação de dados é feita via classes *Banco do Model, que encapsulam lógica de acesso ao banco.

---

## Resumo Técnico

- **Controller:** Centraliza lógica de navegação e regras de negócio, conectando Views e Models.
- **Model:** Define entidades do domínio e suas operações de persistência.
- **Banco:** Classes *Banco implementam acesso ao banco de dados (CRUD).
- **View:** Interfaces gráficas que interagem com usuário e disparam ações para o Controller.

---

## 1. Camada View (Interface Gráfica)

Responsável pela interação com o usuário, exibindo telas e capturando ações (cliques, preenchimento de campos).

### Exemplo: InterfaceJframe

- Função: Tela inicial com botões para Login e Cadastro.
- Componentes: Dois botões (`btnLogin`, `btnCadastro`) e um painel (`jLayeredPane1`).
- Lógica: Ao clicar em Login ou Cadastro, o controle é passado para o Controller, que abre a tela correspondente.

#### Código relevante:

```java
private void btnCadastroActionPerformed(java.awt.event.ActionEvent evt) {
    this.controller.abrirCadastro();
}

private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
    controller.abrirLogin();
}
```

- Esses métodos são chamados quando os botões são pressionados, delegando ação ao Controller.

### Outros arquivos `.form`

Arquivos XML gerados pelo NetBeans GUI Builder que descrevem a disposição dos componentes visuais nas telas.

---

## 2. Camada Database (Banco de Dados)

Responsável pela persistência dos dados: salvar, buscar, atualizar e remover informações no PostgreSQL.

### Exemplo: Teste de Conexão

- Arquivo: `TesteConexao.java`
- Função: testar conexão com banco PostgreSQL usando JDBC.
- Lógica: tenta abrir conexão e imprime mensagem de sucesso ou erro.

```java
Connection conexao = DriverManager.getConnection(url, usuario, senha);
```

### Classes de acesso ao banco

No Controller, são usadas classes como `UsuarioBanco`, `MusicaBanco`, `HistoricoBanco`, etc.  
Essas classes implementam métodos para:

- Inserir usuários (`inserirUsuario`)
- Validar login (`validarLogin`)
- Buscar músicas (`buscarMusicas`)
- Buscar histórico (`getHistoricosUsuario`)
- Avaliar músicas (curtir/descurtir)

Todas usam JDBC para executar comandos SQL.

---

## 3. Funcionalidades e Lógicas Principais

### Cadastro de Usuário

- Usuário preenche nome, email e senha na tela de cadastro.
- Controller cria objeto `Usuario` e chama `UsuarioBanco.inserirUsuario(usuario)`.
- Se sucesso, exibe mensagem e abre menu principal.

### Login

- Usuário informa email e senha.
- Controller chama `UsuarioBanco.validarLogin(email, senha)`.
- Se válido, busca ID e nome do usuário, armazena e abre menu principal.

### Pesquisa de Música

- Usuário digita termo de busca.
- Controller chama `MusicaBanco.buscarMusicas(termo, idUsuario)`.
- Resultados exibidos dinamicamente, cada música com botões “Curtir” e “Descurtir”.
- Ao clicar, chama `CurtidaBanco.avaliarMusica(idUsuario, musica.getId(), true/false)`.

### Histórico

- Controller chama `HistoricoBanco.getHistoricosUsuario(idUsuario)`.
- Exibe dados em tabela na interface de histórico.

### Navegação entre Telas

- Controller centraliza a lógica de abrir/fechar telas, garantindo que só a tela correta fique visível.

---

## 4. Integração Geral

- O método main da classe Spotifei instancia o Controller, que por sua vez instancia as telas principais.
- Toda navegação e lógica de negócio passam pelo Controller, atuando como ponte entre View e Database.
- O banco de dados armazena informações persistentes, como usuários, músicas, curtidas e histórico.

---
