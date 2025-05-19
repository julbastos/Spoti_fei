/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Database.ConeccaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giova
 */
public class HistoricoBanco {
    
    static public boolean InserirHistorico(Historico dado){
        String sql = "INSERT INTO public.historico (id_usuario, id_musica, data, acao) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConeccaoBanco.conectar()) {
            if (conn == null) return false;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, dado.getIdUsuario());
                stmt.setInt(2, dado.getIdMusica());
                stmt.setTimestamp(3, dado.getData());
                stmt.setString(4, dado.getAcao());
                stmt.executeUpdate();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Historico> getHistoricosUsuario(int idUsuario){
        List<Historico> lista = new ArrayList<>();
        String sql = "SELECT historico.id as id_historico,musicas.id as id_musica_2, * FROM historico INNER JOIN musicas ON historico.id_musica = musicas.id WHERE id_usuario = ? ORDER BY DATA";
        try (Connection conn = ConeccaoBanco.conectar()) {
            if (conn == null) return lista;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idUsuario);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        
                        Musica musica = new Musica(rs.getInt("id_musica"), rs.getString("nome"), rs.getString("artista"), rs.getString("genero"));
                        Historico h = new Historico(
                            rs.getInt("id"),
                            rs.getInt("id_usuario"),
                            rs.getInt("id_musica"),
                            rs.getTimestamp("data"),
                            rs.getString("acao")
                        );
                        h.setMusica(musica);
                        lista.add(h);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
