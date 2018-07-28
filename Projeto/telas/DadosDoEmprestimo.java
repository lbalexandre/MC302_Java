package telas;

import trabalho1.Emprestimo;
import trabalho1.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DadosDoEmprestimo extends JFrame {

    // Declaração de componentes.

    private JPanel contentPane;
    private JTextArea dados;
    private JLabel infoSaldo;
    private JScrollPane scrollbar;

    public DadosDoEmprestimo(Usuario usuarioAtual, Emprestimo emprestimoAtual) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Empréstimo realizado com sucesso!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        infoSaldo = new JLabel("Seu saldo agora é (R$) " + usuarioAtual.getSaldo());

        dados = new JTextArea(25, 30);

        dados.setLineWrap(true);

        dados.setWrapStyleWord(true);

        dados.setEditable(false);


        scrollbar = new JScrollPane(dados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        dados.setText("*********** DADOS DO EMPRESTIMO ***********\n");
        dados.append(emprestimoAtual.toString());

        contentPane.add(infoSaldo);
        contentPane.add(scrollbar);

    }
}
