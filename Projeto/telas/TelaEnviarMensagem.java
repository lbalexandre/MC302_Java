package telas;

import trabalho1.SistemaExcecao;
import trabalho1.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEnviarMensagem extends JFrame {

    private JPanel contentPane;
    private JLabel perguntaUsuario, explicacao;
    private JTextField nomeUsuario;
    private JTextArea corpoMensagem;
    private JScrollPane scrollbar;
    private JButton botaoConfirmar;

    public TelaEnviarMensagem(Usuario usuarioAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Enviar mensagem");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 650);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaUsuario = new JLabel("Digite o nome do usuário para o qual que deseja mandar uma mensagem: ");
        nomeUsuario = new JTextField("", 25);

        explicacao = new JLabel("Digite abaixo sua mensagem!");

        corpoMensagem = new JTextArea(30, 35);

        corpoMensagem.setLineWrap(true);

        corpoMensagem.setWrapStyleWord(true);

        corpoMensagem.setEditable(true);

        scrollbar = new JScrollPane(corpoMensagem, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        botaoConfirmar = new JButton("Confirmar");

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    usuarioAtual.enviarMensagem(nomeUsuario.getText(), corpoMensagem.getText());

                    // Se nenhuma exceção foi lançada até esse ponto, quer dizer que obtivemos sucesso ao enviar a mensagem.

                    JOptionPane.showMessageDialog(TelaEnviarMensagem.this, "Mensagem enviada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    TelaEnviarMensagem.this.dispose();

                } catch (SistemaExcecao excecao) {

                    JOptionPane.showMessageDialog(TelaEnviarMensagem.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        contentPane.add(perguntaUsuario);
        contentPane.add(nomeUsuario);
        contentPane.add(explicacao);
        contentPane.add(scrollbar);
        contentPane.add(botaoConfirmar);

    }
}
