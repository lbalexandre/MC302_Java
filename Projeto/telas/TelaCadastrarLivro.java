package telas;

import trabalho1.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public class TelaCadastrarLivro extends JFrame {

    // Declaração de componentes

    private JPanel contentPane;
    private JLabel perguntaNomeLivro, perguntaNomeAutor, perguntaGeneroLivro, perguntaEdicaoLivro, perguntaAnoLivro, perguntaQntdLivro, perguntaValorLivro;
    private JTextField nomeLivro, nomeAutor, generoLivro, edicaoLivro, anoLivro, qntdLivro, valorLivro;
    private JButton botaoConfirmar;


    public TelaCadastrarLivro(Usuario usuarioAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Cadastramento do livro");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaNomeLivro = new JLabel("Digite o nome do livro: ");

        nomeLivro = new JTextField("", 40);

        perguntaNomeAutor = new JLabel("Digite o nome do autor: ");

        nomeAutor = new JTextField("", 40);

        perguntaGeneroLivro = new JLabel(" Digite o gênero do livro: ");

        generoLivro = new JTextField("", 40);

        perguntaEdicaoLivro = new JLabel("Digite a edição do livro: ");

        edicaoLivro = new JTextField("", 40);

        perguntaAnoLivro = new JLabel("Digite o ano do livro: ");

        anoLivro = new JTextField("", 40);

        perguntaQntdLivro = new JLabel("Digite quantos exemplares estão disponíveis desse livro: ");

        qntdLivro = new JTextField("", 40);

        perguntaValorLivro = new JLabel("Digite o valor de empréstimo do livro: ");

        valorLivro = new JTextField("", 40);

        // Botão de confirmação.

        botaoConfirmar = new JButton("Confirmar");

        // ActionListener do botão de confirmação.

        botaoConfirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Caso o usuário já possua o livro em questão, exibimos uma mensagem de erro e lançamos uma exceção.

                try {

                    for (int i = 0; i < usuarioAtual.getLivrosDoUsuario().size(); i++) {
                        if (usuarioAtual.getLivrosDoUsuario().get(i).getNome().equals(nomeLivro.getText())) {
                            throw new SistemaExcecao("Você já possui esse livro em seu acervo!");
                        }
                    }

                    // Caso o usuário entre com um gênero inválido, exibimos uma mensagem de erro e lançamos uma exceção.

                    boolean flag = false;
                    int indice = 0;

                    List<Genero> list = new ArrayList<Genero>(EnumSet.allOf(Genero.class));

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getTipo().equals(generoLivro.getText())) {
                            flag = true;
                            indice = i;
                        }
                    }


                    if (!flag) throw new SistemaExcecao("Digite um gênero válido!");

                    // Se o em nenhum dos casos acima houve desvio de fluxo, podemos dar prosseguimento ao cadastramentro do livro. Caso haja sucesso, exibimos uma mensagem de sucesso.

                    if (usuarioAtual instanceof UsuarioAdmin) {

                        ((UsuarioAdmin) usuarioAtual).cadastrarLivro(nomeLivro.getText(), nomeAutor.getText(), indice, Integer.parseInt(edicaoLivro.getText()), Integer.parseInt(anoLivro.getText()),
                                Integer.parseInt(qntdLivro.getText()), Double.parseDouble(valorLivro.getText()));

                        JOptionPane.showMessageDialog(TelaCadastrarLivro.this, "Livro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        TelaCadastrarLivro.this.dispose();

                    } else if (usuarioAtual instanceof UsuarioEstudante) {

                        ((UsuarioEstudante) usuarioAtual).cadastrarLivro(nomeLivro.getText(), nomeAutor.getText(), indice, Integer.parseInt(edicaoLivro.getText()), Integer.parseInt(anoLivro.getText()),
                                Integer.parseInt(qntdLivro.getText()), Double.parseDouble(valorLivro.getText()));

                        JOptionPane.showMessageDialog(TelaCadastrarLivro.this, "Livro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        TelaCadastrarLivro.this.dispose();

                    } else {

                        ((UsuarioComum) usuarioAtual).cadastrarLivro(nomeLivro.getText(), nomeAutor.getText(), indice, Integer.parseInt(edicaoLivro.getText()), Integer.parseInt(anoLivro.getText()),
                                Integer.parseInt(qntdLivro.getText()), Double.parseDouble(valorLivro.getText()));

                        JOptionPane.showMessageDialog(TelaCadastrarLivro.this, "Livro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        TelaCadastrarLivro.this.dispose();
                    }

                } catch (NumberFormatException excecao) {

                    JOptionPane.showMessageDialog(TelaCadastrarLivro.this, "Entre com números válidos", "Erro", JOptionPane.ERROR_MESSAGE);

                } catch (SistemaExcecao excecao1) {

                    JOptionPane.showMessageDialog(TelaCadastrarLivro.this, excecao1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                }
            }

        });

        add(perguntaNomeLivro);
        add(nomeLivro);
        add(perguntaNomeAutor);
        add(nomeAutor);
        add(perguntaGeneroLivro);
        add(generoLivro);
        add(perguntaEdicaoLivro);
        add(edicaoLivro);
        add(perguntaAnoLivro);
        add(anoLivro);
        add(perguntaQntdLivro);
        add(qntdLivro);
        add(perguntaValorLivro);
        add(valorLivro);
        add(botaoConfirmar);

    }

}
