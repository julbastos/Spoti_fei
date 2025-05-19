/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Historico;
import Model.HistoricoBanco;
import Model.Musica;
import Model.MusicaBanco;
import Model.Playlist;
import Model.PlaylistBanco;
import Model.Usuario;
import Model.UsuarioBanco;
import View.*;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

/**
 *
 * @author unifjbarreto
 */
public class Controller {

    private InterfaceCadastro interfaceCadastro;
    private InterfaceCurtidas interfaceCurtidas;
    private InterfaceDescurtidas interfaceDescurtidas;
    private InterfaceHistorico interfaceHistorico;
    private InterfaceJframe interfaceInicial;
    private InterfaceLogin interfaceLogin;
    private InterfaceEditarPlaylist interfaceEditarPlaylist;
    private InterfaceMenu interfaceMenu;
    private InterfacePlaylist interfacePlaylists;
    private int idUsuario;
    private Usuario usuario;

    public Controller() {
        this.interfaceInicial = new InterfaceJframe(this);
        this.interfaceLogin = new InterfaceLogin(this);
        this.interfaceCadastro = new InterfaceCadastro(this);
        interfaceInicial.setVisible(true);

    }

    public void Cadastrar() {
        // Criando o usuário com os dados fornecidos
        this.usuario = new Usuario(this.interfaceCadastro.getTxtNome().getText(), this.interfaceCadastro.getTxtEmail().getText(), new String(this.interfaceCadastro.getTxtSenha().getPassword()));
        // Instanciando o objeto da classe que vai inserir no banco
        UsuarioBanco usuarioBanco = new UsuarioBanco();
        // Tentando cadastrar o usuário no banco de dados
        boolean sucesso = usuarioBanco.inserirUsuario(usuario);
        this.idUsuario = usuarioBanco.obterIdUsuario(this.usuario.getEmail());
        // Verificando se o cadastro foi bem-sucedido
        if (sucesso) {
            JOptionPane.showMessageDialog(this.interfaceCadastro, "Cadastro realizado com sucesso!");
            fecharCadastro();
            abrirMenuPrincipal();
            fecharTelaInicial();
        } else {
            JOptionPane.showMessageDialog(this.interfaceCadastro, "Erro ao realizar o cadastro. Tente novamente.");
        }

    }

    public void Logar() {
        String email = this.interfaceLogin.getTxtEmail().getText();
        String senha = new String(this.interfaceLogin.getTxtSenha().getPassword());
        UsuarioBanco acesso = new UsuarioBanco();
        if (acesso.validarLogin(email, senha)) {
            this.idUsuario = acesso.obterIdUsuario(email);
            String nomeUsuario = acesso.obterNomeUsuario(email);
            this.usuario = new Usuario(nomeUsuario, email, senha);
            abrirMenuPrincipal();
            fecharLogin();
            fecharTelaInicial();
        } else {
            JOptionPane.showMessageDialog(this.interfaceLogin, "Erro ao realizar o cadastro. Tente novamente.");
        }
    }

    public void fecharTelaInicial() {
        this.interfaceInicial.setVisible(false);
    }

    public void abrirMenuPrincipal() {
        if (this.interfaceMenu == null) {
            this.interfaceMenu = new InterfaceMenu(this);
            this.interfaceMenu.getLblUsuario().setText("Bem-vindo, " + this.usuario.getNome() + "!");
        }
        this.interfaceMenu.setVisible(true);
    }

    public void abrirLogin() {
        this.interfaceLogin.setVisible(true);
    }

    public void fecharLogin() {
        this.interfaceLogin.setVisible(false);
    }

    public void abrirCadastro() {
        this.interfaceCadastro.setVisible(true);
    }

    public void fecharCadastro() {
        this.interfaceCadastro.setVisible(false);
    }

    public void pesquisarMusica() {
        JTextField txtBusca = interfaceMenu.getTxtBusca();
        JPanel painelResultados = interfaceMenu.getPainelResultados();
        String termo = txtBusca.getText().trim();

        if (termo.isEmpty() || termo.length() < 2) {
            painelResultados.removeAll();
            painelResultados.add(new JLabel("Digite um termo válido para buscar."));
            painelResultados.revalidate();
            painelResultados.repaint();
            return;
        }

        List<Musica> resultados = MusicaBanco.buscarMusicas(termo, this.idUsuario);

        painelResultados.removeAll();
        painelResultados.setLayout(new BoxLayout(painelResultados, BoxLayout.Y_AXIS));

        if (resultados == null || resultados.isEmpty()) {
            painelResultados.add(new JLabel("Nenhum resultado encontrado."));
        } else {
            for (Musica musica : resultados) {
                JPanel painelMusica = new JPanel();
                painelMusica.setLayout(new BoxLayout(painelMusica, BoxLayout.X_AXIS));
                JLabel lblMusica = new JLabel(musica.toString());

                JButton btnCurtir = new JButton("Curtir");
                btnCurtir.addActionListener(e -> {
                    boolean sucesso = Model.CurtidaBanco.avaliarMusica(idUsuario, musica.getId(), true);
                    if (sucesso) {
                        javax.swing.JOptionPane.showMessageDialog(interfaceMenu, "Música curtida com sucesso!");
                        abrirCurtidas();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(interfaceMenu, "Erro ao curtir música.");
                    }
                });

                JButton btnDescurtir = new JButton("Descurtir");
                btnDescurtir.addActionListener(e -> {
                    boolean sucesso = Model.CurtidaBanco.avaliarMusica(idUsuario, musica.getId(), false);
                    if (sucesso) {
                        javax.swing.JOptionPane.showMessageDialog(interfaceMenu, "Música descurtida com sucesso!");
                        abrirDescurtidas();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(interfaceMenu, "Erro ao descurtir música.");
                    }
                });

                painelMusica.add(lblMusica);
                painelMusica.add(btnCurtir);
                painelMusica.add(btnDescurtir);

                painelResultados.add(painelMusica);
            }
        }

        painelResultados.revalidate();
        painelResultados.repaint();
    }

    public void abrirHistorico() {
        if (interfaceHistorico == null) {
            interfaceHistorico = new InterfaceHistorico(this);
        }
        carregarHistoricoInterface();
        interfaceHistorico.setVisible(true);
    }

    public void carregarHistoricoInterface() {
        var PainelHistorico = interfaceHistorico.getPainelHistorico();
        // Define um tamanho preferencial maior para o PainelHistorico.
        // Isso fará com que o JFrame, ao usar pack(), se ajuste a esse novo tamanho,
        // e consequentemente a tabela terá mais espaço.
        PainelHistorico.setPreferredSize(new java.awt.Dimension(800, 600)); // Aumenta o tamanho do painel principal

        List<Historico> historico = new HistoricoBanco().getHistoricosUsuario(idUsuario);
        PainelHistorico.removeAll();
        PainelHistorico.setLayout(new java.awt.BorderLayout()); // Mudar para BorderLayout para JScrollPane

        if (historico.isEmpty()) {
            PainelHistorico.add(new JLabel("Nenhum histórico registrado.", JLabel.CENTER), java.awt.BorderLayout.CENTER);
        } else {
            String[] colunas = {"Ação", "Música", "Artista", "Gênero", "Data"};
            javax.swing.table.DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(colunas, 0) {
                // Tornar as células não editáveis
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (Historico histo : historico) {
                Object[] rowData = {
                    histo.getAcao(),
                    histo.getMusica().getNome(),
                    histo.getMusica().getArtista(),
                    histo.getMusica().getGenero(),
                    new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(histo.getData())
                };
                tableModel.addRow(rowData);
            }

            javax.swing.JTable tabelaHistorico = new javax.swing.JTable(tableModel);
            tabelaHistorico.setFillsViewportHeight(true); // Para a tabela preencher a altura do JScrollPane
            tabelaHistorico.setAutoCreateRowSorter(true); // Habilita a ordenação ao clicar nos cabeçalhos das colunas

            // Adicionar a tabela a um JScrollPane para permitir rolagem
            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(tabelaHistorico);

            // Campo de pesquisa
            javax.swing.JTextField campoPesquisa = new javax.swing.JTextField();
            javax.swing.JPanel painelPesquisa = new javax.swing.JPanel(new java.awt.BorderLayout());
            painelPesquisa.add(new JLabel("Pesquisar: "), java.awt.BorderLayout.WEST);
            painelPesquisa.add(campoPesquisa, java.awt.BorderLayout.CENTER);

            // Adiciona o painel de pesquisa ao NORTE (topo) do PainelHistorico
            PainelHistorico.add(painelPesquisa, java.awt.BorderLayout.NORTH);
            // Adiciona o scrollPane com a tabela ao CENTRO do PainelHistorico,
            // fazendo com que ocupe o espaço restante.
            PainelHistorico.add(scrollPane, java.awt.BorderLayout.CENTER);

            // Configurar o filtro de pesquisa
            javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(tableModel);
            tabelaHistorico.setRowSorter(sorter);

            campoPesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    filtrar();
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    filtrar();
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    filtrar();
                }

                private void filtrar() {
                    String texto = campoPesquisa.getText();
                    if (texto.trim().length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        // O (?i) torna a pesquisa case-insensitive
                        sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto)));
                    }
                }
            });
        }

        PainelHistorico.revalidate();
        PainelHistorico.repaint();
        // A fixação no topo e à esquerda do JFrame é primariamente controlada
        // pelo layout do contêiner pai do PainelHistorico (o contentPane do JFrame),
        // que é configurado em initComponents().
        // O PainelHistorico em si já posiciona a barra de pesquisa no topo (NORTH)
        // e a tabela abaixo (CENTER), o que cumpre a fixação interna.
    }

    public void abrirDescurtidas() {
        if (interfaceDescurtidas == null) {
            interfaceDescurtidas = new InterfaceDescurtidas(this);
        }
        carregarDescurtidas();
        interfaceDescurtidas.setVisible(true);
    }

    private void carregarDescurtidas() {
        JPanel painelDescurtidas = this.interfaceDescurtidas.getPainelDescurtidas();
        painelDescurtidas.removeAll();
        painelDescurtidas.setLayout(new java.awt.GridLayout(0, 1, 0, 5));
        List<Musica> musicas = MusicaBanco.buscarDescurtidas(idUsuario);
        if (musicas.isEmpty()) {
            painelDescurtidas.add(new JLabel("Nenhuma música descurtida."));
        } else {
            for (Musica m : musicas) {
                javax.swing.JPanel panel = new javax.swing.JPanel();
                panel.add(new JLabel("🎵 " + m.getNome() + " | " + m.getArtista() + " | " + m.getGenero()));
                javax.swing.JButton btnRemoverDescurtida = new javax.swing.JButton("Remover Descurtida");
                btnRemoverDescurtida.addActionListener(e -> {
                    boolean sucesso = Model.CurtidaBanco.avaliarMusica(idUsuario, m.getId(), true);
                    if (sucesso) {
                        javax.swing.JOptionPane.showMessageDialog(interfaceDescurtidas, "Música marcada como curtida!");
                        carregarDescurtidas();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(interfaceDescurtidas, "Erro ao atualizar.");
                    }
                });
                panel.add(btnRemoverDescurtida);
                painelDescurtidas.add(panel);
            }
        }
        painelDescurtidas.revalidate();
        painelDescurtidas.repaint();

    }

    public void abrirCurtidas() {
        if (interfaceCurtidas == null) {
            interfaceCurtidas = new InterfaceCurtidas(this);
        }
        carregarCurtidas();
        interfaceCurtidas.setVisible(true);
    }

    private void carregarCurtidas() {
        JPanel painelCurtidas = interfaceCurtidas.getPainelCurtidas();
        painelCurtidas.removeAll();
        painelCurtidas.setLayout(new java.awt.GridLayout(0, 1, 0, 5));
        List<Musica> musicas = MusicaBanco.buscarCurtidas(idUsuario);
        System.out.println(musicas);
        if (musicas.isEmpty()) {
            painelCurtidas.add(new JLabel("Nenhuma música curtida."));
        } else {
            for (Musica m : musicas) {
                javax.swing.JPanel panel = new javax.swing.JPanel();
                panel.add(new JLabel("🎵 " + m.getNome() + " | " + m.getArtista() + " | " + m.getGenero()));
                javax.swing.JButton btnDescurtir = new javax.swing.JButton("Descurtir");
                btnDescurtir.addActionListener(e -> {
                    boolean sucesso = Model.CurtidaBanco.avaliarMusica(idUsuario, m.getId(), false);
                    if (sucesso) {
                        javax.swing.JOptionPane.showMessageDialog(interfaceCurtidas, "Música descurtida!");
                        carregarCurtidas();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(interfaceCurtidas, "Erro ao atualizar.");
                    }
                });
                panel.add(btnDescurtir);
                painelCurtidas.add(panel);
            }
        }
        painelCurtidas.revalidate();
        painelCurtidas.repaint();
    }

    public void abrirPlaylists() {
        if (interfacePlaylists == null) {
            this.interfacePlaylists = new InterfacePlaylist(this);
        }
        mostrarListaPlaylists();
        interfacePlaylists.setVisible(true);
    }

    public void criarPlaylist() {
        String nomePlaylist = this.interfacePlaylists.getTxtNomePlaylist().getText();
        try {
            PlaylistBanco.criarPlaylist(nomePlaylist, idUsuario);
            JOptionPane.showMessageDialog(this.interfacePlaylists, "Playlist criada com sucesso!");
            mostrarListaPlaylists();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this.interfacePlaylists, "Erro ao criar a playlist. Tente novamente.");
        }

    }

    public void mostrarListaPlaylists() {
        JPanel painelListaPlaylist = this.interfacePlaylists.getPainelListaPlaylists();
        System.out.println("Mostrando!");
        painelListaPlaylist.removeAll();
        painelListaPlaylist.setLayout(new java.awt.GridLayout(0, 1, 0, 5));
        List<Playlist> playlists = PlaylistBanco.buscarPlaylists(idUsuario);
        for (Playlist playlist : playlists) {
            painelPlaylistIndividual playlistIndividualPanel = new painelPlaylistIndividual(this, playlist.getId());
            playlistIndividualPanel.getTxtNomePlaylist1().setText(playlist.getNome());
            painelListaPlaylist.add(playlistIndividualPanel);

        }
    }

    public void abrirEditorPlaylist(int id_playlist) {
        Playlist playlist = PlaylistBanco.buscarPlaylistIndividual(id_playlist);
        this.interfaceEditarPlaylist = new InterfaceEditarPlaylist(this, playlist);
        this.interfaceEditarPlaylist.getLabelNomePlaylist().setText(playlist.getNome());
        this.interfaceEditarPlaylist.getTxtNovoNomePlaylist().setText(playlist.getNome());
        mostrarMusicasPlaylist();
        this.interfaceEditarPlaylist.setVisible(true);

    }

    public void pesquisarMusicasEmPlaylist() {
        JTextField txtBusca = interfaceEditarPlaylist.getTxtBuscarMusicaPlaylist();
        JPanel painelResultados = interfaceEditarPlaylist.getPanelResultadosBuscaMusicaPlaylist();
        String termo = txtBusca.getText().trim();

        if (termo.isEmpty() || termo.length() < 2) {
            painelResultados.removeAll();
            painelResultados.add(new JLabel("Digite um termo válido para buscar."));
            painelResultados.revalidate();
            painelResultados.repaint();
            return;
        }

        List<Musica> resultados = MusicaBanco.buscarMusicas(termo, this.idUsuario);

        painelResultados.removeAll();
        painelResultados.setLayout(new BoxLayout(painelResultados, BoxLayout.Y_AXIS));

        if (resultados == null || resultados.isEmpty()) {
            painelResultados.add(new JLabel("Nenhum resultado encontrado."));
        } else {
            for (Musica musica : resultados) {
                JPanel painelMusica = new JPanel();
                painelMusica.setLayout(new BoxLayout(painelMusica, BoxLayout.X_AXIS));
                JLabel lblMusica = new JLabel(musica.toString());

                JButton btnCurtir = new JButton("Adicionar Musica");
                btnCurtir.addActionListener(e -> {
                    boolean musicaAdicionada = PlaylistBanco.adicionarMusicaPlaylist(musica.getId(), interfaceEditarPlaylist.getPlaylist().getId());
                    if (musicaAdicionada) {
                        JOptionPane.showMessageDialog(this.interfaceEditarPlaylist, "Música adicionada na playlist com sucesso!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);
                        mostrarMusicasPlaylist();
                    } else {
                        JOptionPane.showMessageDialog(this.interfaceEditarPlaylist, "Falha ao adicionar musica na playlist!",
                                    "Falha",
                                    JOptionPane.INFORMATION_MESSAGE);
                    }

                });

                painelMusica.add(lblMusica);
                painelMusica.add(btnCurtir);

                painelResultados.add(painelMusica);
            }
        }

        painelResultados.revalidate();
        painelResultados.repaint();
    }

    public void mostrarMusicasPlaylist() {
        JScrollPane painelRolagemMusicas = this.interfaceEditarPlaylist.getPanelMusicasPlaylist();
        JPanel painelConteudoMusicas = new JPanel();
        painelConteudoMusicas.setLayout(new BoxLayout(painelConteudoMusicas, BoxLayout.Y_AXIS)); // Empilha os itens verticalmente

        Playlist playlist = this.interfaceEditarPlaylist.getPlaylist();

        if (playlist == null) {
            // Se não houver playlist carregada, exibe uma mensagem.
            JLabel lblMensagem = new JLabel("Nenhuma playlist selecionada.");
            // Para centralizar com BoxLayout, podemos alinhar o componente em si.
            lblMensagem.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
            // Adicionar espaçadores para tentar centralizar verticalmente também
            painelConteudoMusicas.add(javax.swing.Box.createVerticalGlue());
            painelConteudoMusicas.add(lblMensagem);
            painelConteudoMusicas.add(javax.swing.Box.createVerticalGlue());
        } else {
            List<Musica> musicas = PlaylistBanco.buscarMusicasPlaylist(playlist.getId());

            if (musicas == null || musicas.isEmpty()) {
                JLabel lblMensagem = new JLabel("Esta playlist está vazia.");
                lblMensagem.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                painelConteudoMusicas.add(javax.swing.Box.createVerticalGlue());
                painelConteudoMusicas.add(lblMensagem);
                painelConteudoMusicas.add(javax.swing.Box.createVerticalGlue());
            } else {
                for (Musica m : musicas) {
                    JPanel painelMusicaItem = new JPanel();
                    painelMusicaItem.setLayout(new FlowLayout(FlowLayout.LEFT));
                    painelMusicaItem.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
                    JLabel lblInfoMusica = new JLabel("🎵 " + m.getNome() + " | " + m.getArtista() + " | " + m.getGenero());
                    painelMusicaItem.add(lblInfoMusica);
                    javax.swing.JButton btnRemoverDescurtida = new javax.swing.JButton("-");
                    btnRemoverDescurtida.setToolTipText("Remover esta música da playlist");
                    btnRemoverDescurtida.addActionListener(e -> {
                        boolean sucesso = PlaylistBanco.removerMusicaPlaylist(playlist.getId(), m.getId());
                        if (sucesso) {
                            JOptionPane.showMessageDialog(this.interfaceEditarPlaylist, "Música removida da playlist com sucesso!",
                                    "Sucesso",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this.interfaceEditarPlaylist, "Falha ao remover musica da playlist!",
                                    "Falha",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                        mostrarMusicasPlaylist();
                    });
                    painelMusicaItem.add(btnRemoverDescurtida);
                    painelConteudoMusicas.add(painelMusicaItem);
                }
            }
        }
        painelRolagemMusicas.setViewportView(painelConteudoMusicas);
        painelRolagemMusicas.revalidate();
        painelRolagemMusicas.repaint();

    }

    public void exluirPlaylist() {
        Playlist playlist = this.interfaceEditarPlaylist.getPlaylist();
        PlaylistBanco.excluirPlaylist(playlist.getId());
        this.interfaceEditarPlaylist.setVisible(false);
        mostrarListaPlaylists();
    }

    public void editarPlaylist() {
        String novoNomePlaylist = this.interfaceEditarPlaylist.getTxtNovoNomePlaylist().getText();
        Playlist playlistEditada = this.interfaceEditarPlaylist.getPlaylist();
        PlaylistBanco.editarPlaylist(playlistEditada.getId(), novoNomePlaylist);
        this.interfaceEditarPlaylist.getLabelNomePlaylist().setText(novoNomePlaylist);
        mostrarListaPlaylists();
    }

    public void fecharPlaylists() {
        interfacePlaylists.setVisible(false);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
