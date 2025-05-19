package Model;

import Database.ConeccaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CurtidaBanco {
    public static boolean avaliarMusica(int idUsuario, int idMusica, boolean status) {
        String sql = "INSERT INTO public.curtidas (id_usuario, id_musica, status) VALUES (?, ?, ?) " +
                     "ON CONFLICT (id_usuario, id_musica) DO UPDATE SET status = EXCLUDED.status";
        try (Connection conn = ConeccaoBanco.conectar()) {
            if (conn == null) return false;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idUsuario);
                stmt.setInt(2, idMusica);
                stmt.setBoolean(3, status);
                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    String acao = status ? "curtiu" : "descurtiu";
                    Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
                    Historico registroHistorico = new Historico(0, idUsuario, idMusica, dataAtual, acao);
                    HistoricoBanco.InserirHistorico(registroHistorico);
                    return true;
                } else {
                    return false; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}