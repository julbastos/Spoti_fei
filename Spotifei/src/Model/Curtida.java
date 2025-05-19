package Model;

public class Curtida {
    private int idUsuario;

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public boolean isStatus() {
        return status;
    }
    private int idMusica;
    private boolean status; // true = curtiu, false = descurtiu

    public Curtida(int idUsuario, int idMusica, boolean status) {
        this.idUsuario = idUsuario;
        this.idMusica = idMusica;
        this.status = status;
    }

    // getters e setters
}