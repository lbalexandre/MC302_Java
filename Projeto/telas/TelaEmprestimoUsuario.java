package telas;


import trabalho1.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEmprestimoUsuario extends JFrame {

    private JPanel contentPane;
    private JLabel perguntaLivro, perguntaUsuario, perguntaCupom;
    private JTextField nomeLivro, nomeUsuario, codigoCupom;
    private JButton botaoConfirmar;

    public TelaEmprestimoUsuario(Usuario usuarioAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Empréstimo com um Usuário");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 250);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaLivro = new JLabel("Digite o nome do livro: ");
        nomeLivro = new JTextField("", 40);

        perguntaUsuario = new JLabel("Digite o nome do usuário: ");
        nomeUsuario = new JTextField("", 40);

        perguntaCupom = new JLabel("Digite o código do cupom: ");
        perguntaCupom.setToolTipText("Se não possui um cupom de desconto, deixe esse campo em branco");
        codigoCupom = new JTextField("",40);
        codigoCupom.setToolTipText("Se não possui um cupom de desconto, deixe esse campo em branco");

        // Botão de confirmação.

        botaoConfirmar = new JButton("Confirmar");

        // ActionListener do botão de confirmação.

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int resultadoUsuario = Gerenciador.checaUsuario(nomeUsuario.getText());

                    // Se não foi lançada uma exceção até esse ponto do código, o usuário com o qual se quer fazer um empréstimo existe.

                    if(usuarioAtual instanceof UsuarioComum) {

                        Emprestimo emprestimoAtual = usuarioAtual.novoEmprestimo(nomeLivro.getText(), Biblioteca.usuarios.get(resultadoUsuario), null);
                        DadosDoEmprestimo dados = new DadosDoEmprestimo(usuarioAtual, emprestimoAtual);
                        dados.setVisible(true);
                        TelaEmprestimoUsuario.this.dispose();

                    } else {

                        if(codigoCupom.getText().equals("")) { // Se não foi preenchido texto nesse campo, não há um cupom.

                            Emprestimo emprestimoAtual = usuarioAtual.novoEmprestimo(nomeLivro.getText(), Biblioteca.usuarios.get(resultadoUsuario), null);
                            DadosDoEmprestimo dados = new DadosDoEmprestimo(usuarioAtual, emprestimoAtual);
                            dados.setVisible(true);
                            TelaEmprestimoUsuario.this.dispose();

                        } else {

                            int resultadoCupom = Gerenciador.checaCupom(codigoCupom.getText());

                            // Se nenhuma exceção foi lançada, o cupom existe e é válido (Não foi utilizado).

                            Emprestimo emprestimoAtual = usuarioAtual.novoEmprestimo(nomeLivro.getText(), Biblioteca.usuarios.get(resultadoUsuario), Biblioteca.cupons.get(resultadoCupom));
                            DadosDoEmprestimo dados = new DadosDoEmprestimo(usuarioAtual, emprestimoAtual);
                            dados.setVisible(true);
                            TelaEmprestimoUsuario.this.dispose();
                        }
                    }

                } catch(SistemaExcecao excecao) {

                    JOptionPane.showMessageDialog(TelaEmprestimoUsuario.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                }
            }
        });


        contentPane.add(perguntaLivro);
        contentPane.add(nomeLivro);
        contentPane.add(perguntaUsuario);
        contentPane.add(nomeUsuario);

        if(usuarioAtual instanceof UsuarioEstudante) {
            contentPane.add(perguntaCupom);
            contentPane.add(codigoCupom);
        }

        contentPane.add(botaoConfirmar);
    }

}
