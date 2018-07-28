package telas;

import trabalho1.Biblioteca;
import trabalho1.Gerenciador;
import trabalho1.Usuario;
import trabalho1.UsuarioAdmin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MenuAdmin extends JFrame {

    private JPanel contentPane;


    public MenuAdmin(UsuarioAdmin usuarioAdmin) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Menu Principal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 250, 250);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER));

        // Instanciamento de botões.

        JButton botaoBanirUsuario = new JButton("Banir usuário");
        contentPane.add(botaoBanirUsuario);

        JButton botaoCadastrarCupom = new JButton("Cadastrar Cupom");
        contentPane.add(botaoCadastrarCupom);

        JButton botaoCadastrarLivro = new JButton("Cadastrar livro");
        contentPane.add(botaoCadastrarLivro);

        JButton botaoListaEstadoDo = new JButton("Listar estado do sistema");
        contentPane.add(botaoListaEstadoDo);

        JButton botaoBibliotecaEmArquivo = new JButton("Conteúdo do sistema em arquivo");
        contentPane.add(botaoBibliotecaEmArquivo);

        JButton botaoSair = new JButton("Sair");
        contentPane.add(botaoSair);

        // Criamos um actionListener de modo que, para cada botão pressionado, ele mostra a tela/realiza a ação correspondente.

        ActionListener btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == botaoBanirUsuario) {

                    new TelaBanirUsuario(usuarioAdmin).setVisible(true);

                } else if (e.getSource() == botaoCadastrarCupom) {

                    new TelaCadastrarCupom(usuarioAdmin).setVisible(true);

                } else if (e.getSource() == botaoCadastrarLivro) {

                    new TelaCadastrarLivro(usuarioAdmin).setVisible(true);

                } else if (e.getSource() == botaoListaEstadoDo) {

                    new TelaEstadoSistema().setVisible(true);

                } else if (e.getSource() == botaoBibliotecaEmArquivo) {

                    try {

                        Writer writer = new FileWriter("testeArquivos/Biblioteca.txt");

                        writer.write("Dados do sistema\n");

                        Biblioteca bibliotecaParaArquivo = new Biblioteca();

                        bibliotecaParaArquivo.salvar(writer);

                        writer.close();

                        JOptionPane.showMessageDialog(MenuAdmin.this, "Dados da biblioteca se tornaram um arquivo texto com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException excecao) {
                        JOptionPane.showMessageDialog(MenuAdmin.this, "Erro ao tornar os dados da biblioteca um arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } else {

                    JOptionPane.showMessageDialog(MenuAdmin.this, "Obrigado por utilizar a Biblioteca Virtual!", "Até logo!", JOptionPane.INFORMATION_MESSAGE);

                    MenuAdmin.this.dispose();

                    new TelaInicial().setVisible(true);

                }
            }
        };

        botaoBanirUsuario.addActionListener(btnListener);
        botaoCadastrarCupom.addActionListener(btnListener);
        botaoCadastrarLivro.addActionListener(btnListener);
        botaoListaEstadoDo.addActionListener(btnListener);
        botaoBibliotecaEmArquivo.addActionListener(btnListener);
        botaoSair.addActionListener(btnListener);

    }

}
