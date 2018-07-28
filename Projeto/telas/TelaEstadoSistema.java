package telas;

import trabalho1.Biblioteca;
import trabalho1.SistemaExcecao;
import trabalho1.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEstadoSistema extends JFrame {


    private JPanel contentPane;
    private JTextArea infoSistema;
    private JScrollPane scrollbar;
    private JButton botaoListaLivro, botaoListaUsuario, botaoListaEmprestimo, botaoListaCupom;

    public TelaEstadoSistema() {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Estado do Sistema");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 650);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        infoSistema = new JTextArea(30, 35);

        infoSistema.setLineWrap(true);

        infoSistema.setWrapStyleWord(true);

        infoSistema.setEditable(false);

        scrollbar = new JScrollPane(infoSistema, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        botaoListaLivro = new JButton("Listar livros no acervo da Biblioteca Virtual");
        botaoListaEmprestimo = new JButton("Listar os empréstimos do sistema");
        botaoListaUsuario = new JButton("Listar usuários do sistema");
        botaoListaCupom = new JButton("Listar cupons do sistema");

        // ActionListener que realiza a ação correspondente para cada botão pressionado.

        ActionListener btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == botaoListaLivro) {

                    infoSistema.setText(Biblioteca.acervo.toString());

                    if (Biblioteca.acervo.size() == 0) {
                        infoSistema.setText("Não há livros no sistema!\n");
                    }

                } else if (e.getSource() == botaoListaEmprestimo) {

                    infoSistema.setText(Biblioteca.emprestimos.toString());

                    if (Biblioteca.emprestimos.size() == 0) {
                        infoSistema.setText("Não há empréstimos no sistema!\n");
                    }

                } else if (e.getSource() == botaoListaUsuario) {

                    infoSistema.setText(Biblioteca.usuarios.toString());

                    if (Biblioteca.usuarios.size() == 0) {
                        infoSistema.setText("Não há usuários no sistema!\n");
                    }

                } else {

                    infoSistema.setText(Biblioteca.cupons.toString());

                    if (Biblioteca.cupons.size() == 0) {
                        infoSistema.setText("Não há cupons no sistema!\n");
                    }

                }
            }
        };


        botaoListaLivro.addActionListener(btnListener);
        botaoListaEmprestimo.addActionListener(btnListener);
        botaoListaUsuario.addActionListener(btnListener);
        botaoListaCupom.addActionListener(btnListener);

        contentPane.add(botaoListaLivro);
        contentPane.add(botaoListaEmprestimo);
        contentPane.add(botaoListaUsuario);
        contentPane.add(botaoListaCupom);
        contentPane.add(scrollbar);


    }
}
