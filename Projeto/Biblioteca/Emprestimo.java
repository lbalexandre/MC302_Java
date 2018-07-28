package trabalho1;

import java.util.Random;

// Classe empréstimo: Abstração de um empréstimo para os efeitos do sistema. Classe mãe de EmprestimoEntreUsuarios.

public class Emprestimo {

    // Atributos.

    private int idEmprestimo;
    private static int geradorId = 0;
    private final int idMax = 10000;
    private int idUsuario;
    private int idLivro;
    private String dataEmprestimo;
    private String dataDevolucao;
    private double valor;
    private boolean ativo;

    // Construtor.

    public Emprestimo(int idLivro, int idUsuario, String dataEmprestimo, String dataDevolucao,
                      double valor) {
        Random geraIdRandom = new Random(geradorId);
        this.idEmprestimo = geraIdRandom.nextInt(idMax);
        geradorId++;
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
        this.ativo = true;
        Biblioteca.emprestimos.add(this);
    }

    // Getters & Setters.

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // toString de empréstimo.

     @Override
    public String toString() {
        String out = "Empréstimo (ID): " + getIdEmprestimo() + "\n";
        out = out + "Data do empréstimo: " + getDataEmprestimo() + "\n";
        out = out + "Data de devolução: " + getDataDevolucao() + "\n";
        out = out + "Usuário (ID): " + getIdUsuario() + "\n";
        out = out + "Livro (ID): " + getIdLivro() + "\n";
        out = out + "Valor: R$" + getValor() + "\n";

        if (getAtivo())
            out = out + "O livro ainda está emprestado\n";
        else
            out = out + "O livro já foi devolvido\n";

        return out;
    }
}
