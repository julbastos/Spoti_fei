package Model;

import Database.ConeccaoBanco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaBanco {

    public static List<Musica> buscarDescurtidas(int idUsuario) {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT m.id, m.nome, m.artista, m.genero FROM musicas m "
                + "JOIN curtidas c ON m.id = c.id_musica "
                + "WHERE c.id_usuario = ? AND c.status = false";
        try (Connection conn = Database.ConeccaoBanco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                musicas.add(new Musica(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("artista"),
                        rs.getString("genero")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicas;
    }

    public static List<Musica> buscarCurtidas(int idUsuario) {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT m.id, m.nome, m.artista, m.genero FROM musicas m "
                + "JOIN curtidas c ON m.id = c.id_musica "
                + "WHERE c.id_usuario = ? and status = true";
        try (Connection conn = Database.ConeccaoBanco.conectar();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Lido");
                musicas.add(
                        new Musica(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("artista"),
                                rs.getString("genero")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicas;
    }
    
    public static List<Musica> buscarMusicas(String termo,int id_usuario) {
        List<Musica> resultados = new ArrayList<>();

        // Se o termo estiver vazio ou padrão, já retorna lista vazia
        if (termo == null || termo.trim().isEmpty() || termo.trim().equals("....")) {
            return resultados;
        }

        try {
            Connection conn = ConeccaoBanco.conectar();
            if (conn == null) {
                System.out.println("Não foi possível conectar ao banco de dados.");
                return resultados;
            }

            // A query SELECT * já deve incluir o ID se a tabela musicas tiver uma coluna id
            String sql = "SELECT id, nome, artista, genero FROM musicas WHERE UPPER(nome) LIKE ? OR UPPER(artista) LIKE ? OR UPPER(genero) LIKE ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            String termoBusca = "%" + termo.trim() + "%";

            stmt.setString(1, termoBusca.toUpperCase());
            stmt.setString(2, termoBusca.toUpperCase());
            stmt.setString(3, termoBusca.toUpperCase());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id"); // Obter o ID da música
                String nome = rs.getString("nome");
                String artista = rs.getString("artista");
                String genero = rs.getString("genero");
                // Criar e adicionar o objeto Musica à lista de resultados
                String acao = "buscou";
                Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
                Historico registroHistorico = new Historico(0, id_usuario, id, dataAtual, acao);
                HistoricoBanco.InserirHistorico(registroHistorico);
                resultados.add(new Musica(id, nome, artista, genero));
                
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar músicas: " + e.getMessage());
            e.printStackTrace();
        }

        return resultados;
    }
}
