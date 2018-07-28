package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


public class TelaInicial extends JFrame {

    // Declaração de componentes.

    private JPanel contentPane;

    public TelaInicial() {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Biblioteca Virtual");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 198);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);

        // Fizemos uma label em html para dar (o único, inclusive) um toque artístico à tela inicial.

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        add(new JLabel("<html><h1><strong><i>Seja Bem-Vindo!</i></strong></h1><hr></html>"), gbc);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(175, 89, 89, 23);
        contentPane.add(btnSair, BorderLayout.CENTER);
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                TelaInicial.this.dispose();
            }
        });
        btnLogin.setBounds(27, 89, 89, 23);
        contentPane.add(btnLogin, BorderLayout.CENTER);

        JButton btnCadastro = new JButton("Cadastro");
        btnCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new Cadastro().setVisible(true);
            }
        });
        btnCadastro.setBounds(175, 89, 89, 23);
        contentPane.add(btnCadastro, BorderLayout.CENTER);
    }
}
