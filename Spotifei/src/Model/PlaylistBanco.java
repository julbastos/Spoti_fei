package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.ConeccaoBanco;

public class PlaylistBanco {

    // Buscar playlists do usuário (retorna lista de objetos Playlist)
    public static List<Playlist> buscarPlaylists(int idUsuario) {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT id, nome FROM playlists WHERE id_usuario = ?";
        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                playlists.add(new Playlist(rs.getInt("id"), rs.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

    public static Playlist buscarPlaylistIndividual(int idPlaylist) {
        String sql = "SELECT id, nome FROM playlists WHERE id = ?";
        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Playlist(rs.getInt("id"), rs.getString("nome"));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    // Criar nova playlist
    public static boolean criarPlaylist(String nome, int idUsuario) {
        String sql = "INSERT INTO playlists (nome, id_usuario) VALUES (?, ?)";
        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Editar nome da playlist
    public static boolean editarPlaylist(int idPlaylist, String novoNome) {
        String sql = "UPDATE playlists SET nome = ? WHERE id = ?";
        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, idPlaylist);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Excluir playlist
    public static boolean excluirPlaylist(int idPlaylist) {
        String sql = "DELETE FROM playlists WHERE id = ?";
        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean adicionarMusicaPlaylist(int idMusica, int idPlaylist) {
        String sql = "INSERT INTO playlist_musicas(id_playlist,id_musica) VALUES (?,?)";
        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Musica> buscarMusicasPlaylist(int idPlaylist) {
        List<Musica> musicas = new ArrayList<>();
        String sql = "select *,musicas.id as musica_id from playlist_musicas INNER JOIN musicas on playlist_musicas.id_musica = musicas.id WHERE playlist_musicas.id_playlist = ?";
        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                musicas.add(new Musica(rs.getInt("musica_id"), rs.getString("nome"), rs.getString("artista"), rs.getString("genero")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicas;
    }

    public static boolean removerMusicaPlaylist(int idPlaylist, int idMusica) {
        System.out.printf("Id playlist %d e id da musica %d",idPlaylist,idMusica);
        String sql = "DELETE FROM playlist_musicas WHERE id_musica = ? AND id_playlist = ?";
        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMusica);
            stmt.setInt(2, idPlaylist);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
