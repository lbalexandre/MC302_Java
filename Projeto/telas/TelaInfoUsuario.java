package telas;

import trabalho1.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInfoUsuario extends JFrame {

    // Declaração de componentes.

    private JPanel contentPane;
    private JTextArea dadosUsuario;
    private JTextField nomeUsuario;
    private JLabel perguntaUsuario;
    private JScrollPane scrollbar;
    private JButton botaoConfirmar;


    public TelaInfoUsuario() {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Informações de outros usuários");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaUsuario = new JLabel("Qual o nome do usuário que deseja saber as informações? ");
        nomeUsuario = new JTextField("", 20);

        dadosUsuario = new JTextArea(30, 30);

        dadosUsuario.setLineWrap(true);

        dadosUsuario.setWrapStyleWord(true);

        scrollbar = new JScrollPane(dadosUsuario, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        dadosUsuario.setText("As informações vão aparecer aqui");

        botaoConfirmar = new JButton("Confirmar");

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int resultado = Gerenciador.checaUsuario(nomeUsuario.getText());

                    // Se nenhuma exceção foi lançada, é porque um usuário com esse nome foi encontrado.

                    dadosUsuario.setText(Biblioteca.usuarios.get(resultado).toString());

                } catch (SistemaExcecao excecao) {

                    JOptionPane.showMessageDialog(TelaInfoUsuario.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        contentPane.add(perguntaUsuario);
        contentPane.add(nomeUsuario);
        contentPane.add(botaoConfirmar);
        contentPane.add(scrollbar);

    }

}
