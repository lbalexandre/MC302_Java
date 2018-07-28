package telas;

import trabalho1.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class TelaBanirUsuario extends JFrame {

    private JPanel contentPane;
    private JButton botaoConfirmar;
    private JTextField usuarioBanido;
    private JLabel perguntaUsuario;

    public TelaBanirUsuario(UsuarioAdmin admin) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Banir usuário");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 350, 150);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaUsuario = new JLabel("Digite o nome do usuário à ser banido: ");
        usuarioBanido = new JTextField("", 20);

        // Botão de confirmação.

        botaoConfirmar = new JButton("Confirmar");

        // ActionListener do botão de confirmação.

        botaoConfirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    admin.banirUsuario(usuarioBanido.getText());

                    // Se nenhuma exceção foi lançada até esse ponto, obtivemos sucesso ao banir o usuário.

                    JOptionPane.showMessageDialog(TelaBanirUsuario.this, "Usuário banido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    TelaBanirUsuario.this.dispose();

                } catch (SistemaExcecao excecao) {
                    JOptionPane.showMessageDialog(TelaBanirUsuario.this,excecao.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        contentPane.add(perguntaUsuario);
        contentPane.add(usuarioBanido);
        contentPane.add(botaoConfirmar);

    }
}
