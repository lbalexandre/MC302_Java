package telas;

import trabalho1.Emprestimo;
import trabalho1.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaInfoPropria extends JFrame {

    private JPanel contentPane;
    private JTextArea dados;
    private JScrollPane scrollbar;

    public TelaInfoPropria(Usuario usuarioAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Suas informações");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 330);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        dados = new JTextArea(15, 20);

        dados.setLineWrap(true);

        dados.setWrapStyleWord(true);

        dados.setEditable(false);

        scrollbar = new JScrollPane(dados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        dados.setText(usuarioAtual.toString());

        contentPane.add(scrollbar);

    }

}

