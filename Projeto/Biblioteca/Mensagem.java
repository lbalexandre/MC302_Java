package trabalho1;

// Classe mensagem: Abstração de uma mensagem para os efeitos do sistema.

public class Mensagem {

    // Atributos.

    private String texto;
    private boolean lido;
    private String enviadaPor;

    // Construtor.

    public Mensagem(String texto, String enviadaPor) {
        this.texto = texto;
        this.enviadaPor = enviadaPor;
        this.lido = false;
    }

    // Setters & Getters.

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean getLido() {
        return lido;
    }

    public void setLido(boolean lido) {
        this.lido = lido;
    }

    // toString de mensagem.

    @Override
    public String toString() {

        String out = "Status da mensagem: ";
        if (lido) {
            out = out + "Lida\n";
        } else {
            out = out + "Não lida\n";
        }
        out = out + "Mensagem enviada por: " + enviadaPor + "\n";
        out = out + texto + "\n";
        return out;
    }
}
