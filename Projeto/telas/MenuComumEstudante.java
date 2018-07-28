package telas;

import trabalho1.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class MenuComumEstudante extends JFrame {

    private JPanel contentPane;
    private JButton btnCadastrarLivro, btnEmprestimoBV, btnEmprestimoComUm, btnAdicionarAmigo, btnInformacoesDeOutros, btnAlterarDados, btnVerMinhasInformacoes,
            btnEnviarMensagem, btnCaixaDeEntrada, btnAdicionarSaldo, btnSair, btnUsuariosMesmaUni, btnCaixaArquivo, btnMensagemArquivo;


    public MenuComumEstudante(Usuario usuarioAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Menu Principal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 310, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER));

        // Instanciamento de botões.

        btnCadastrarLivro = new JButton("Cadastrar livro");
        btnCadastrarLivro.setBounds(10, 59, 180, 23);
        contentPane.add(btnCadastrarLivro);

        btnEmprestimoBV = new JButton("Empréstimo com a biblioteca Virtual");
        btnEmprestimoBV.setBounds(10, 25, 200, 23);
        contentPane.add(btnEmprestimoBV);

        btnEmprestimoComUm = new JButton("Empréstimo com um usuário");
        btnEmprestimoComUm.setBounds(217, 25, 187, 23);
        contentPane.add(btnEmprestimoComUm);

        btnAdicionarAmigo = new JButton("Adicionar amigo");
        btnAdicionarAmigo.setBounds(10, 189, 180, 23);
        contentPane.add(btnAdicionarAmigo);

        btnInformacoesDeOutros = new JButton("Informações de outros usuários");
        btnInformacoesDeOutros.setBounds(217, 189, 187, 23);
        contentPane.add(btnInformacoesDeOutros);

        btnAlterarDados = new JButton("Alterar dados");
        btnAlterarDados.setBounds(217, 134, 187, 23);
        contentPane.add(btnAlterarDados);

        btnVerMinhasInformacoes = new JButton("Ver minhas informações");
        btnVerMinhasInformacoes.setBounds(10, 134, 180, 23);
        contentPane.add(btnVerMinhasInformacoes);

        btnEnviarMensagem = new JButton("Enviar mensagem");
        btnEnviarMensagem.setBounds(217, 258, 187, 23);
        contentPane.add(btnEnviarMensagem);

        btnCaixaDeEntrada = new JButton("Caixa de entrada");
        btnCaixaDeEntrada.setBounds(10, 258, 180, 23);
        contentPane.add(btnCaixaDeEntrada);

        btnAdicionarSaldo = new JButton("Adicionar Saldo");
        btnAdicionarSaldo.setBounds(200, 310, 150, 23);
        contentPane.add(btnAdicionarSaldo);

        btnCaixaArquivo = new JButton("Transformar a caixa de entrada em arquivo");
        contentPane.add(btnCaixaArquivo);

        btnMensagemArquivo = new JButton("Transformar um arquivo em mensagem");
        contentPane.add(btnMensagemArquivo);

        btnUsuariosMesmaUni = new JButton("Buscar usuários da mesma Universidade");
        if (usuarioAtual instanceof UsuarioEstudante) {
            contentPane.add(btnUsuariosMesmaUni);
        }


        btnSair = new JButton("Sair");
        btnSair.setBounds(163, 359, 140, 23);
        contentPane.add(btnSair);

        // Criamos um actionListener de modo que, para cada botão pressionado, ele mostra a tela/realiza a ação correspondente.

        ActionListener btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnCadastrarLivro) {

                    new TelaCadastrarLivro(usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnEmprestimoBV) {

                    new TelaEmprestimoBiblioteca(usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnEmprestimoComUm) {

                    new TelaEmprestimoUsuario(usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnAdicionarAmigo) {

                    new TelaAdicionarAmigo(usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnInformacoesDeOutros) {

                    new TelaInfoUsuario().setVisible(true);

                } else if (e.getSource() == btnAlterarDados) {

                    new TelaAlteraDados(usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnVerMinhasInformacoes) {

                    new TelaInfoPropria(usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnEnviarMensagem) {

                    new TelaEnviarMensagem(usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnCaixaDeEntrada) {

                    new TelaLerMensagens(usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnAdicionarSaldo) {

                    new TelaAddSaldo(usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnUsuariosMesmaUni) {

                    new TelaMesmaUniversidade((UsuarioEstudante) usuarioAtual).setVisible(true);

                } else if (e.getSource() == btnCaixaArquivo) {

                    try {

                        Writer writer = new FileWriter("testeArquivos/CaixaDeEntrada.txt");

                        writer.write("Caixa de mensagem\n");

                        usuarioAtual.salvar(writer);

                        writer.close();

                        JOptionPane.showMessageDialog(MenuComumEstudante.this, "Caixa de Entrada se tornou um arquivo texto com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException excecao) {
                        JOptionPane.showMessageDialog(MenuComumEstudante.this, "Erro ao tornar a caixa de entrada um arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } else if (e.getSource() == btnMensagemArquivo) {

                    new TelaMensagemArquivo(usuarioAtual).setVisible(true);

                } else { // Se nenhum desses botões foi apertado, quer dizer que o usuário apertou "sair".

                    JOptionPane.showMessageDialog(MenuComumEstudante.this, "Obrigado por utilizar a Biblioteca Virtual!", "Até logo!", JOptionPane.INFORMATION_MESSAGE);

                    MenuComumEstudante.this.dispose();

                    new TelaInicial().setVisible(true);

                }
            }
        };

        btnCadastrarLivro.addActionListener(btnListener);
        btnEmprestimoBV.addActionListener(btnListener);
        btnEmprestimoComUm.addActionListener(btnListener);
        btnAdicionarAmigo.addActionListener(btnListener);
        btnInformacoesDeOutros.addActionListener(btnListener);
        btnAlterarDados.addActionListener(btnListener);
        btnEnviarMensagem.addActionListener(btnListener);
        btnVerMinhasInformacoes.addActionListener(btnListener);
        btnCaixaDeEntrada.addActionListener(btnListener);
        btnAdicionarSaldo.addActionListener(btnListener);
        btnUsuariosMesmaUni.addActionListener(btnListener);
        btnCaixaArquivo.addActionListener(btnListener);
        btnMensagemArquivo.addActionListener(btnListener);
        btnSair.addActionListener(btnListener);
    }
}
