package trabalho1;

// Classe enum Genero: abstração do gênero de um livro para os efeitos do sistema.

public enum Genero {

    MATEMATICA("Matemetica"),
    BIOLOGIA("Biologia"),
    PORTUGUES("Portugues"),
    HISTORIA("Historia"),
    GEOGRAFIA("Geografia"),
    FISICA("Fisica"),
    QUIMICA("Quimica"),
    ROMANCE("Romance"),
    DRAMA("Drama"),
    RELIGIOSO("Religioso"),
    AVENTURA("Aventura"),
    TRAGEDIA("Tragedia");

    private String tipo;
    private Genero(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return ("Gênero: " + getTipo() + "\n");
    }
}
