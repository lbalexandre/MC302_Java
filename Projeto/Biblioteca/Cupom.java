package trabalho1;

// Classe Cupom: abstração de um cupom para os efeitos do sistema.

public class Cupom {

    // Atributos.

    private String codigo;
    private boolean foiUsado;
    private double desconto;

    // Getters & Setters.

    public Cupom(String codigo, double desconto) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.foiUsado = false;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean getFoiUsado() {
        return foiUsado;
    }

    public void setFoiUsado(boolean foiUsado) {
        this.foiUsado = foiUsado;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    // toString de Cupom.

    @Override
    public String toString() {
        String out = "Código do cupom: " + getCodigo() + "\n";
        if (foiUsado) {
            out = out + "Cupom foi utilizado";
        } else {
            out = out + "Cupom não foi utilizado";
        }
        return out;
    }
}
