package Model;

public class Musica {
    private int id;
    private String nome;
    private String artista;
    private String genero;

    public Musica(int id, String nome, String artista, String genero) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getArtista() { return artista; }
    public String getGenero() { return genero; }

    @Override
    public String toString() {
        // Você pode ajustar como a música é exibida na lista de busca
        return "🎵 " + nome + " | " + artista + " | " + genero;
    }
}
