package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.*;
import javax.swing.border.*;

import trabalho1.*;

import java.awt.event.*;

public class Login extends JFrame {

    // Declaração de componentes.

    private JPanel contentPane;
    private JTextField textUsuario;
    private JPasswordField textSenha;


    public Login() {

        // Definições de tamanho e criação do frame e painel.

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 285, 228);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        JLabel txtUsuario = new JLabel("Usuário");
        txtUsuario.setBounds(24, 28, 56, 14);
        contentPane.add(txtUsuario);

        textUsuario = new JTextField();
        textUsuario.setBounds(24, 43, 139, 20);
        contentPane.add(textUsuario);
        textUsuario.setColumns(10);

        JLabel txtSenha = new JLabel("Senha");
        txtSenha.setBounds(24, 74, 46, 14);
        contentPane.add(txtSenha);

        textSenha = new JPasswordField();
        textSenha.setBounds(24, 90, 139, 20);
        contentPane.add(textSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textUsuario.getText();
                String senha = textSenha.getText();

                try { // Checa à qual classe pertence o usuário vigente e abre o menu correspondente.

                    if (Biblioteca.usuarios.get(Gerenciador.login(nome, senha)) instanceof UsuarioAdmin) {

                        new MenuAdmin((UsuarioAdmin) Biblioteca.usuarios.get(Gerenciador.login(nome, senha))).setVisible(true);

                        Login.this.dispose();

                    } else {

                        new MenuComumEstudante(Biblioteca.usuarios.get(Gerenciador.login(nome, senha))).setVisible(true);

                        Login.this.dispose();

                    }

                } catch (SistemaExcecao excecao) {
                    JOptionPane.showMessageDialog(Login.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEntrar.setBounds(84, 155, 89, 23);
        contentPane.add(btnEntrar);
    }
}
