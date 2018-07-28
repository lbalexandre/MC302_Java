package telas;

import trabalho1.Biblioteca;
import trabalho1.Gerenciador;
import trabalho1.SistemaExcecao;
import trabalho1.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class TelaMensagemArquivo extends JFrame {

    // Declaração de componentes.

    JPanel contentPane;
    JButton botaoConfirmar;
    JLabel perguntaUsuario, perguntaArquivo;
    JTextField nomeUsuario, nomeArquivo;


    public TelaMensagemArquivo(Usuario usuarioAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Transformar arquivo em mensagem");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 200);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaUsuario = new JLabel("Digite o nome do usuário que deseja enviar uma mensagem: ");
        nomeUsuario = new JTextField("", 30);

        perguntaArquivo = new JLabel("Digite o caminho para o arquivo à ser transformado em mensagem: ");
        nomeArquivo = new JTextField("", 30);

        // Botão de confirmação.

        botaoConfirmar = new JButton("Confirmar");

        // ActionListener do botão de confirmação.

        botaoConfirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    File file = new File(nomeArquivo.getText());

                    usuarioAtual.enviarMensagem(nomeUsuario.getText(), usuarioAtual.ler(file));

                    // Se o fluxo não foi desviado até esse ponto, obtivemos sucesso ao enviar a mensagem.

                    JOptionPane.showMessageDialog(TelaMensagemArquivo.this, "Arquivo transformado em mensagem com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (SistemaExcecao excecao) {

                    JOptionPane.showMessageDialog(TelaMensagemArquivo.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                } catch (IOException excecao1) {

                    JOptionPane.showMessageDialog(TelaMensagemArquivo.this, "Erro ao transformar o arquivo em mensagem", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        contentPane.add(perguntaUsuario);
        contentPane.add(nomeUsuario);
        contentPane.add(perguntaArquivo);
        contentPane.add(nomeArquivo);
        contentPane.add(botaoConfirmar);

    }
}
