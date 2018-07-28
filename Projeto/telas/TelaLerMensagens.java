package telas;

import trabalho1.SistemaExcecao;
import trabalho1.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaLerMensagens extends JFrame {

    // Declaração de componentes.

    private JPanel contentPane;
    private JLabel pergunta;
    private JButton todas, naoLidas;
    private JTextArea textoMensagens;
    private JScrollPane scrollbar;

    public TelaLerMensagens(Usuario usuarioAtual)  {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Caixa de entrada");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        pergunta = new JLabel("Você deseja ler as mensagens: ");

        todas = new JButton("Todas");
        naoLidas = new JButton("Não Lidas");

        textoMensagens = new JTextArea(25, 30);

        textoMensagens.setLineWrap(true);

        textoMensagens.setWrapStyleWord(true);

        scrollbar = new JScrollPane(textoMensagens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        ActionListener butonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (e.getSource() == naoLidas) {
                        textoMensagens.setText(usuarioAtual.verMensagens(1));
                    } else {
                        textoMensagens.setText(usuarioAtual.verMensagens(2));
                    }
                } catch (SistemaExcecao excecao) {

                    JOptionPane.showMessageDialog(TelaLerMensagens.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                }
            }
        };

        naoLidas.addActionListener(butonListener);
        todas.addActionListener(butonListener);

        contentPane.add(pergunta);
        contentPane.add(todas);
        contentPane.add(naoLidas);
        contentPane.add(scrollbar);

    }


}
