package telas;

import trabalho1.SistemaExcecao;
import trabalho1.Usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAddSaldo extends JFrame {

    // Declaração de variáveis/componentes.

    private JPanel contentPane;
    private JTextField textCartao, textValor;
    private JLabel perguntaCartao, perguntaValor;
    private JButton botaoConfirmar;


    public TelaAddSaldo(Usuario usuarioAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Adicionar saldo");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 350, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaCartao = new JLabel("Digite o número do cartão de crédito: ");
        textCartao = new JTextField("", 25);

        perguntaValor = new JLabel("Valor à ser adicionado em seu saldo (R$): ");
        textValor = new JTextField("", 25);

        // Botão de confirmação.

        botaoConfirmar = new JButton("Confirmar");

        // ActionListener do botão de confirmação.
        
        botaoConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    double saldoAtual = usuarioAtual.adicionarSaldo(textCartao.getText(), Double.parseDouble(textValor.getText()));

					// Se nenhuma exceção foi lançada até esse ponto do código, houve sucesso ao adicionarmos o valor ao saldo do usuário.

                    JOptionPane.showMessageDialog(TelaAddSaldo.this, "Sucesso ao adicionar saldo, seu saldo agora é (R$) " + saldoAtual, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    TelaAddSaldo.this.dispose();

                } catch (SistemaExcecao excecao) {
                    JOptionPane.showMessageDialog(TelaAddSaldo.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        contentPane.add(perguntaCartao);
        contentPane.add(textCartao);
        contentPane.add(perguntaValor);
        contentPane.add(textValor);
        contentPane.add(botaoConfirmar);
    }

}
