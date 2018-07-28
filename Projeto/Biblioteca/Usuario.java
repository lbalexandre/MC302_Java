package trabalho1;

import java.text.SimpleDateFormat;
import java.util.*;

// Classe Usuário: Abstração de um usuário para os efeitos do sistema. É a classe mãe de UsuarioAdmin e UsuarioEstudante.

public abstract class Usuario implements SalvarLer {

    // Atributos.

    private int id;
    private static int geradorId = 0;
    private final int idMax = 10000;
    private String nome;
    private String senha;
    private String dataNasc;
    private String email;
    private boolean status;
    private double saldo;
    private String infoPagamento;
    private ArrayList<Mensagem> mensagens;
    private ArrayList<Livro> livrosDoUsuario;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<Usuario> amigos;

    // Construtor.

    public Usuario(String nome, String senha, String dataNasc, String email, boolean status) {
        this.nome = nome;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.email = email;
        this.status = status;
        this.saldo = 0;
        this.infoPagamento = null;
        this.mensagens = new ArrayList<Mensagem>();
        this.livrosDoUsuario = new ArrayList<Livro>();
        this.emprestimos = new ArrayList<Emprestimo>();
        this.amigos = new ArrayList<Usuario>();
        Random geradorIdRandom = new Random(geradorId); // Aqui, há um processo de geração de id com auxílio da classe Random. Para cada usuário gerado, seu id é um número aleatório com seed geradorId.
        this.id = geradorIdRandom.nextInt(idMax);
        geradorId++;
        Biblioteca.usuarios.add(this);
    }

    // Getters & Setters.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getInfoPagamento() {
        return infoPagamento;
    }

    public void setInfoPagamento(String infoPagamento) {
        this.infoPagamento = infoPagamento;
    }

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }

    public ArrayList<Emprestimo> getemprestimos() {
        return emprestimos;
    }

    public ArrayList<Livro> getLivrosDoUsuario() {
        return livrosDoUsuario;
    }

    public ArrayList<Mensagem> getMensagens() {
        return mensagens;
    }

    // Métodos da classe Usuário. São herdados pelas classes UsuarioEstudante e UsuarioAdmin.

    // Método adicionarSaldo: Permite que o usuário adicione um valor(double) à seu seu saldo se a forma de pagamento provida pelo usuário for válida. Lança SistemaExcecao.

    public double adicionarSaldo(String infoPagamento, Double valor) throws SistemaExcecao {

        Gerenciador.pagamentoValido(infoPagamento); // Lança uma exceção se o número de cartão de crédito não for uma forma válida de pagamento.

        // Se até esse ponto do código não foi lançada nenhuma exceção, a forma de pagamento provida é válida.

        if (valor > 0)
            saldo = saldo + valor;
        else
            throw new SistemaExcecao("Por favor, digite um número positivo e maior que zero à ser inserido em seu saldo");

        return saldo;

    }

    // Método verMensagem: Permite o usuário ler o contéudo das mensagens de seu atributo AL<Mensagem> mensagens. Ele pode optar por ler todas as mensagens ou apenas as não lidas. Lança SistemaExceção.

    public String verMensagens(int opcao) throws SistemaExcecao {

        String out = "";

        if (this.mensagens.size() == 0) throw new SistemaExcecao("Não há mensagens em sua caixa de entrada!");

        if (opcao == 1) {
            for (int i = 0; i < mensagens.size(); i++) {
                if (!(mensagens.get(i).getLido())) { // Caso o atributo lido da mensagem seja false.
                    out += mensagens.get(i).toString();
                    mensagens.get(i).setLido(true);
                }
            }
        } else {
            for (int i = 0; i < mensagens.size(); i++) {
                out += mensagens.get(i);
                if (!(mensagens.get(i).getLido())) // Caso o atributo lido da mensagem seja false.
                    mensagens.get(i).setLido(true);
            }
        }

        return out;

    }

    // Método enviarMensagem: Permite o usuário enviar uma mensagem à outro usuário do sistema a partir de dados obtidos pela interface gráfica.

    public void enviarMensagem(String nomeUsuario, String texto) throws SistemaExcecao {

        int resultado = Gerenciador.checaUsuario(nomeUsuario);

        Mensagem mensagemAtual = new Mensagem(texto, nome);

        Biblioteca.usuarios.get(resultado).getMensagens().add(mensagemAtual);

    }

    // Método adicionarAmigo: Permite o usuário atual adicionar outro usuário do sistema à sua lista de amigos. Lança SistemaExcecao.

    public void adicionarAmigo(String nomeAlvo) throws SistemaExcecao {

        int resultado = Gerenciador.checaUsuario(nomeAlvo);

        // Caso tenhamos chegado à esse ponto do código, checaUsuario não lançou a exceção de que o Usuário não existe.

        // A chamada de checaExcecao ocorre para impedir o usuário de se adicionar ou adicionar um usuário que já consta em sua lista de amigos - o método lança as exceções correspondentes para cada caso.

        Gerenciador.checaExcecao(this, Biblioteca.usuarios.get(resultado));

        // Se nenhuma exceção foi lançada até esse ponto do código, obtivemos sucesso ao adicionar o usuário.

        amigos.add(Biblioteca.usuarios.get(resultado));

    }

    /*
       Método novoEmprestimo: Provavelmente o método mais complexo e importante do sistema. Ele recebe dois parâmetros: usuarioEmprestador
       e cupom, e ambos podem ou não ser nulos (null). A partir desse método, um usuário é capaz de realizar um empréstimo com o acervo
       da biblioteca ou com o acervo de outro usuário do sistema (usuarioEmprestador). No caso de usuarioEstudante, é possível realizar
       ambos tipos de empréstimo com um cupom de desconto. Lança SistemaExeceção.
     */

    public Emprestimo novoEmprestimo(String nome, @Nullable Usuario usuarioEmprestador, @Nullable Cupom cupom) throws SistemaExcecao {

        // Para determinar a data de empréstimo e devolução, foram utilizadas as classes Data e Calendar.

        Calendar cal = Calendar.getInstance();
        Date data = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
        String dataEmprestimo = sdf.format(data);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        data = cal.getTime();

        String dataDevolucao = sdf.format(data);

        if (usuarioEmprestador == null) { // Caso em que o empréstimo é com o acervo da biblioteca virtual
            for (int i = 0; i < Biblioteca.acervo.size(); i++) {
                if (Biblioteca.acervo.get(i).getNome().equals(nome)) {
                    if (Biblioteca.acervo.get(i).getLivrosDisponiveis() > 0 && saldo >= Biblioteca.acervo.get(i).getValorDeEmprestimo()) { // Verifica se existem livros disponíveis para aquele livro e se o saldo é suficiente para o empréstimo.

                        double valorNormal = Biblioteca.acervo.get(i).getValorDeEmprestimo();
                        double valorComDesconto = (cupom != null) ? (1 - (cupom.getDesconto() / 100)) * valorNormal : 0;

                        Biblioteca.acervo.get(i).setLivrosDisponiveis(Biblioteca.acervo.get(i).getLivrosDisponiveis() - 1); // Atualiza a quantidade de livros disponíveis.

                        if (Biblioteca.acervo.get(i).getLivrosDisponiveis() <= 0)
                            Biblioteca.acervo.remove(Biblioteca.acervo.get(i)); // Se agora temos 0 livros disponíveis, o livro deve ser retirado do acervo.

                        if (cupom != null) { // Se há um cupom de desconto, devemos aplicá-lo no saldo do usuário e defini-lo como usado.
                            saldo = saldo - valorComDesconto;
                            cupom.setFoiUsado(true);
                        } else
                            saldo = saldo - valorNormal;

                        Emprestimo emprestimoAtual = new Emprestimo(Biblioteca.acervo.get(i).getId(), id, dataEmprestimo, dataDevolucao, ((cupom != null) ? valorComDesconto : valorNormal)); // Instanciamento do empréstimo.
                        emprestimos.add(emprestimoAtual);

                        return emprestimoAtual;

                    } else { // Se a condição acima não foi satisfeita, um ou ambos dos critérios não foi satisfeito.
                        throw new SistemaExcecao("Saldo insuficiente e/ou livro indisponível!");
                    }
                }
            }
        } else { // Caso em que o empréstimo é realizado com um usuário do sistema.
            for (int i = 0; i < usuarioEmprestador.getLivrosDoUsuario().size(); i++) {
                if (usuarioEmprestador.getLivrosDoUsuario().get(i).getNome().equals(nome)) {
                    System.out.println();
                    if (usuarioEmprestador.getLivrosDoUsuario().get(i).getLivrosDisponiveis() > 0 && saldo >= usuarioEmprestador.getLivrosDoUsuario().get(i).getValorDeEmprestimo()) { // Verifica se existem livros disponíveis para aquele livro e se o saldo é suficiente para o empréstimo.

                        double valorNormal = usuarioEmprestador.getLivrosDoUsuario().get(i).getValorDeEmprestimo();

                        double valorComDesconto = (cupom != null) ? (1 - (cupom.getDesconto() / 100)) * valorNormal : 0;

                        usuarioEmprestador.getLivrosDoUsuario().get(i).setLivrosDisponiveis(usuarioEmprestador.getLivrosDoUsuario().get(i).getLivrosDisponiveis() - 1); // Atualiza a quantidade de livros disponíveis.

                        if (usuarioEmprestador.getLivrosDoUsuario().get(i).getLivrosDisponiveis() <= 0)
                            usuarioEmprestador.getLivrosDoUsuario().remove(usuarioEmprestador.getLivrosDoUsuario().get(i)); // Se agora temos 0 livros disponíveis, o livro deve ser retirado do acervo do usuário.

                        if (cupom != null) { // // Se há um cupom de desconto, devemos aplicá-lo no saldo do usuário e defini-lo como usado.
                            saldo = saldo - valorComDesconto;
                            cupom.setFoiUsado(true);
                        } else {
                            saldo = saldo - valorNormal;
                        }

                        usuarioEmprestador.setSaldo(usuarioEmprestador.getSaldo() + valorNormal); // Atualiza o saldo do usuário emprestador com o valor do livro emprestado.

                        EmprestimoEntreUsuarios emprestimoAtual = new EmprestimoEntreUsuarios(id, usuarioEmprestador.getId(), usuarioEmprestador.getLivrosDoUsuario().get(i).getId(), dataEmprestimo, dataDevolucao, ((cupom != null) ? valorComDesconto : valorNormal));
                        emprestimos.add(emprestimoAtual);
                        usuarioEmprestador.getemprestimos().add(emprestimoAtual);

                        return emprestimoAtual;
                    } else { // Se a condição acima não foi satisfeita, um ou ambos dos critérios não foi satisfeito.
                        throw new SistemaExcecao("Saldo insuficiente e/ou livro indisponível!");
                    }
                }
            }
        }

        throw new SistemaExcecao("Perdão, não encontramos um livro com esse nome!"); // Se não retornou nos for(s), o livro não existe.
    }


    // Método cadastrarLivro: Permite o usuário cadastrar um livro no sistema. Implementado em usuarioComum, usuarioAdmin e usuarioEstudante.

    public abstract void cadastrarLivro(String nome, String autor, int indice, int edicao, int ano,
                                        int livrosDisponiveis, double valor);


    // toString de Usuario.

    @Override
    public String toString() {
        String out = "Nome: " + getNome() + " (ID: " + getId() + ")\n";
        out = out + "Data de nascimento: " + getDataNasc() + "\n";
        out = out + "Email: " + getEmail() + "\n";

        if (getStatus()) {
            out = out + "Status: Positivo\n";
        } else {
            out = out + "Status: Negativo\n";
        }

        out = out + "Livros do Usuário\n";
        out = out + "Saldo R$: " + getSaldo() + "\n";
        out = out + "Livros do Usuário: \n";

        for (int i = 0; i < livrosDoUsuario.size(); i++) {
            out = out + livrosDoUsuario.get(i).getNome() + "\n";
        }

        out = out + "Empréstimos ativos:\n";

        for (int i = 0; i < emprestimos.size(); i++) {
            out = out + "Id do empréstimo ativo número " + i + " é " + emprestimos.get(i).getIdEmprestimo() + "\n";
        }

        out = out + "Amigos do Usuario " + getNome() + "\n";
        out = out + "Seus amigos:\n";
        for (int i = 0; i < amigos.size(); i++) {
            out = out + amigos.get(i).getNome() + "\n";
        }
        return out;
    }
}
