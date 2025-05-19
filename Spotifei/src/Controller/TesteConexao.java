/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author unifjbarreto
 */
public class TesteConexao {
    
      public static void main(String[] args) {
        String url ="jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:5432/postgres";
        String usuario =  "postgres.xymewaxsghizzghaziqs";
        String senha = "FEI2025Givas";
        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("onexão bem-sucedida!");
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
        }
    }
    
}
