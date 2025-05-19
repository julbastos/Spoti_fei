package Model;

import Database.ConeccaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioBanco {

    public boolean inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO public.\"usuarios\" (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = ConeccaoBanco.conectar()) {
            if (conn == null) {
                return false;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenha());
                int rows = stmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public int obterIdUsuario(String email) {
        String sql = "SELECT id FROM public.usuarios WHERE email = ?";
        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorna 0 se não encontrar (atenção: trate isso no login)
    }

    public boolean validarLogin(String email, String senha) {
        String sql = "SELECT 1 FROM public.usuarios WHERE email = ? AND senha = ?";

        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public String obterNomeUsuario(String email) {
        String sql = "SELECT nome FROM public.usuarios WHERE email = ?";

        try (Connection conn = ConeccaoBanco.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nome");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
