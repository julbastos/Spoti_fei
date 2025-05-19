/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Timestamp;
/**
 *
 * @author Julia
 */
public class Historico {
    private int id,idUsuario,idMusica;
    private Musica musica;
    private java.sql.Timestamp data;
    private String acao;

    public Historico(int id, int idUsuario, int id_musica, Timestamp data,String acao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idMusica = id_musica;
        this.data = data;
        this.acao = acao;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.idUsuario = id_usuario;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int id_musica) {
        this.idMusica = id_musica;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }
    
    
}
