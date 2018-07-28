package telas;

import trabalho1.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEmprestimoBiblioteca extends JFrame {

    private JPanel contentPane;
    private JLabel perguntaLivro, perguntaCupom;
    private JTextField nomeLivro, codigoCupom;
    private JButton botaoConfirmar;

    public TelaEmprestimoBiblioteca(Usuario usuarioAtual) {


        // Definições de tamanho e criação do frame e painel.
        setTitle("Empréstimo com a Biblioteca virtual");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 200);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaLivro = new JLabel("Digite o nome do livro: ");
        nomeLivro = new JTextField("", 40);

        perguntaCupom = new JLabel("Digite o código do cupom: ");
        perguntaCupom.setToolTipText("Se não possui um cupom de desconto, deixe esse campo em branco");
        codigoCupom = new JTextField("", 40);
        codigoCupom.setToolTipText("Se não possui um cupom de desconto, deixe esse campo em branco");

        // Botão de confirmação.

        botaoConfirmar = new JButton("Confirmar");

        // ActionListener do botão de confirmação.

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                if(usuarioAtual instanceof UsuarioComum) {

                    Emprestimo emprestimoAtual = usuarioAtual.novoEmprestimo(nomeLivro.getText(), null, null);
                    DadosDoEmprestimo dados = new DadosDoEmprestimo(usuarioAtual, emprestimoAtual);
                    dados.setVisible(true);
                    TelaEmprestimoBiblioteca.this.dispose();

                } else {

                    if(codigoCupom.getText().equals("")) { // Se não foi preenchido texto nesse campo, não há um cupom.

                        Emprestimo emprestimoAtual = usuarioAtual.novoEmprestimo(nomeLivro.getText(), null, null);
                        DadosDoEmprestimo dados = new DadosDoEmprestimo(usuarioAtual, emprestimoAtual);
                        dados.setVisible(true);
                        TelaEmprestimoBiblioteca.this.dispose();

                    } else {

                        int resultadoCupom = Gerenciador.checaCupom(codigoCupom.getText());

                        // Se nenhuma exceção foi lançada, o cupom existe e é válido (Não foi utilizado).

                        Emprestimo emprestimoAtual = usuarioAtual.novoEmprestimo(nomeLivro.getText(), null, Biblioteca.cupons.get(resultadoCupom));
                        DadosDoEmprestimo dados = new DadosDoEmprestimo(usuarioAtual, emprestimoAtual);
                        dados.setVisible(true);
                        TelaEmprestimoBiblioteca.this.dispose();
                    }
                }

            } catch(SistemaExcecao excecao) {

                    JOptionPane.showMessageDialog(TelaEmprestimoBiblioteca.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        contentPane.add(perguntaLivro);
        contentPane.add(nomeLivro);

        if(usuarioAtual instanceof UsuarioEstudante) {
            contentPane.add(perguntaCupom);
            contentPane.add(codigoCupom);
        }

        contentPane.add(botaoConfirmar);

    }

}
