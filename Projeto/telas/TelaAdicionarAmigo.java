package telas;

import trabalho1.SistemaExcecao;
import trabalho1.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicionarAmigo extends JFrame {

    private JPanel contentPane;
    private JLabel perguntaNomeUsuario;
    private JTextField nomeUsuario;
    private JButton botaoConfirmar;

    public TelaAdicionarAmigo(Usuario usuarioAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Adicionar amigo");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 150);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaNomeUsuario = new JLabel("Digite o nome do usuário que você deseja adicionar: ");

        nomeUsuario = new JTextField("", 30);

        botaoConfirmar = new JButton("Confirmar");

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    usuarioAtual.adicionarAmigo(nomeUsuario.getText());

                    // Caso nenhuma exceção foi lançada, o método obteve sucesso ao adicionar o usuário. Exibimos uma mensagem de sucesso e fechamos o frame.

                    JOptionPane.showMessageDialog(TelaAdicionarAmigo.this, "Amigo adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    TelaAdicionarAmigo.this.dispose();

                    // Caso capturemos uma exceção no processo, exibimos uma mensagem de erro e há desvio de fluxo.

                } catch (SistemaExcecao excecao) {
                    JOptionPane.showMessageDialog(TelaAdicionarAmigo.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        contentPane.add(perguntaNomeUsuario);
        contentPane.add(nomeUsuario);
        contentPane.add(botaoConfirmar);
    }
}
