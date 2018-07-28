package telas;

import trabalho1.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAlteraDados extends JFrame {

    // Declaração de variáveis/componentes.

    private JPanel contentPane;
    private JButton botaoConfirmar;
    private JLabel perguntaUsuario, perguntaSenha, perguntaEmail, perguntaAniversario, perguntaRA, perguntaInstituicao;
    private JTextField novoUsuarioNome, novaSenha, novoEmail, novoAniversario, novoRA, novaInstituicao;


    public TelaAlteraDados(Usuario usuarioAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Alteração de dados");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 330);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaUsuario = new JLabel("Digite o novo nome de usuário: ");

        novoUsuarioNome = new JTextField("", 40);

        perguntaSenha = new JLabel("Digite a sua nova senha: ");

        novaSenha = new JTextField("", 40);

        perguntaEmail = new JLabel(" Digite seu novo Email: ");

        novoEmail = new JTextField("", 40);

        perguntaAniversario = new JLabel("Digite a nova data de nascimento: ");

        novoAniversario = new JTextField("", 40);

        perguntaRA = new JLabel("Digite seu novo RA: ");

        novoRA = new JTextField("", 40);

        perguntaInstituicao = new JLabel("Digite sua nova instituição de ensino: ");

        novaInstituicao = new JTextField("", 40);

        // Botão de confirmação.

        botaoConfirmar = new JButton("Confirmar");

        // ActionListener do botão de confirmação.

        botaoConfirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Caso o nome de usuário já exista, exibimos uma mensagem de erro.
                    try {
                        Gerenciador.checaUsuario(novoUsuarioNome.getText());

                        // Se não foi lançada uma exceção em checaUsuario até esse ponto do código, existe um usuário com esse nome.

                        JOptionPane.showMessageDialog(TelaAlteraDados.this, "Esse nome de usuário já existe. Digite um nome válido!", "Erro", JOptionPane.ERROR_MESSAGE);

                    } catch (SistemaExcecao excecao) {// Nesse caso, queiramos que uma execeção quanto à checaUsuario ocorra -> Se o usuário com o novo nome não existe, podemos mudar o nome do usuário vigente.

                        // Condicional para tratar os dois casos possíveis de usuário.

                        if (usuarioAtual instanceof UsuarioEstudante) {
                            ((UsuarioEstudante) usuarioAtual).alteraDados(novoUsuarioNome.getText(), novaSenha.getText(), novoEmail.getText(), novoAniversario.getText(), Integer.parseInt(novoRA.getText()), novaInstituicao.getText());
                        } else {
                            ((UsuarioComum) usuarioAtual).alteraDados(novoUsuarioNome.getText(), novaSenha.getText(), novoEmail.getText(), novoAniversario.getText());
                        }

                        // Caso o fluxo não tenha sido desviado até agora, soltamos uma mensagem de sucesso e fechamos a frame correspondente.

                        JOptionPane.showMessageDialog(TelaAlteraDados.this, "Dados alterados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        TelaAlteraDados.this.dispose();

                    }
                } catch (NumberFormatException excecao1) {

                    JOptionPane.showMessageDialog(TelaAlteraDados.this, "Digite um número válido para o novo RA!", "Erro", JOptionPane.ERROR_MESSAGE);

                }
            }

        });

        // Componentes que são comuns tanto à UsuarioComum quanto à UsuarioEstudante.

        contentPane.add(perguntaUsuario);
        contentPane.add(novoUsuarioNome);
        contentPane.add(perguntaSenha);
        contentPane.add(novaSenha);
        contentPane.add(perguntaEmail);
        contentPane.add(novoEmail);
        contentPane.add(perguntaAniversario);
        contentPane.add(novoAniversario);

        // Caso estamos lidando com um UsuarioEstudante, há mais opções disponíveis.

        if (usuarioAtual instanceof UsuarioEstudante) {
            contentPane.add(perguntaRA);
            contentPane.add(novoRA);
            contentPane.add(perguntaInstituicao);
            contentPane.add(novaInstituicao);
        }

        contentPane.add(botaoConfirmar);
    }

}
