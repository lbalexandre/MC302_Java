package trabalho1;

import java.io.*;
import java.util.ArrayList;

// Classe biblioteca: É o banco de dados onde estão armazenadas as AL necessárias do sistema.

public class Biblioteca implements SalvarLer {

    // Dados do sistema.

    public static ArrayList<Livro> acervo = new ArrayList<Livro>();
    public static ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    public static ArrayList<Cupom> cupons = new ArrayList<Cupom>();

    // toString de Biblioteca.

    @Override
    public String toString() {
        String out = "Banco de Dados da Biblioteca\n";

        out = out + acervo.size() + "\n";
        for (int i = 0; i < acervo.size(); i++)
            out = out + acervo.get(i).toString();

        out = out + usuarios.size() + "\n";
        for (int i = 0; i < usuarios.size(); i++)
            out = out + usuarios.get(i).toString();

        out = out + emprestimos.size() + "\n";
        for (int i = 0; i < emprestimos.size(); i++)
            out = out + emprestimos.get(i).toString();

        out = out + cupons.size() + "\n";
        for (int i = 0; i < cupons.size(); i++)
            out = out + cupons.get(i).toString();

        return out;
    }

    // Implementação da interfaceSalvarLer.

    // Implementação de SalvarLer.

    public void salvar(Writer writer) throws IOException {

        writer.write(this.toString());

        writer.flush();
    }

    public String ler(File arquivo) throws IOException {
        String out = "";
        Reader arq = new FileReader(arquivo);
        BufferedReader ler = new BufferedReader(arq);
        String linha = ler.readLine();
        while (linha != null) {
            out += linha;
            linha = ler.readLine();
        }
        ler.close();
        arq.close();
        return out;
    }
}


