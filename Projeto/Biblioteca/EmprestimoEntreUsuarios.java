package trabalho1;

// Classe EmprestimoEntreUsuarios: Abstração de um empréstimo entre dois usuários para os efeitos do sistema. Extende empréstimo.

public class EmprestimoEntreUsuarios extends Emprestimo {

    // Atributo.

    private int idUsuarioEmprestador;

    // Construtor.

    public EmprestimoEntreUsuarios(int idUsuario, int idUsuarioEmprestador, int idLivro, String dataEmprestimo,
                                   String dataDevolucao, double valor) {
        super(idUsuario, idLivro, dataEmprestimo, dataDevolucao, valor);
        this.idUsuarioEmprestador = idUsuarioEmprestador;
    }

    // Getter & Setter.

    public int getIdUsuarioEmprestador() {
        return idUsuarioEmprestador;
    }

    public void setIdUsuarioEmprestador(int idUsuarioEmprestador) {
        this.idUsuarioEmprestador = idUsuarioEmprestador;
    }

    // toString de EmprestimoEntreUsuarios.

     @Override

    public String toString() {
        String out = "Empréstimo (ID) : " + getIdEmprestimo() + "\n";
        out = out + "Data do empréstimo: " + getDataEmprestimo() + "\n";
        out = out + "Data de devolução: " + getDataDevolucao() + "\n";
        out = out + "Usuário (ID): " + getIdUsuario() + "\n";
        out = out + "Usuário que emprestou (ID): " + getIdUsuarioEmprestador() + "\n";
        out = out + "Livro (ID) : " + getIdLivro() + "\n";
        out = out + "Valor: R$" + getValor() + "\n";

        if (getAtivo())
            out = out + "O livro ainda está emprestado\n";
        else
            out = out + "O livro já foi devolvido\n";

        return out;
    }
}
