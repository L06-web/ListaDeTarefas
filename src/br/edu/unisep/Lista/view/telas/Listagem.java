package br.edu.unisep.Lista.view.telas;

import br.edu.unisep.Lista.model.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Listagem {
    public void Listagem(List<Tarefa> tarefinhas)
    {
        List<Tarefa> tarefas = tarefinhas;

        JFrame janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setTitle("Listagem");
        janela.setSize(500,500);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        for (int i = 0; i < tarefas.size(); i++) {
            JPanel painelTarefa = new JPanel();

            String complemento;
            if (tarefas.get(i).isStatus())
            {
                complemento = "REALIZADO";
            }
            else
            {
                complemento = "NÃO REALIZADO";
            }

            JLabel JLID = new JLabel("Tarefa: " + (i+1) +" | " + tarefas.get(i).getTarefa() + " " + complemento);
            painelTarefa.add(JLID);
            painelPrincipal.add(painelTarefa);
        }
        janela.add(painelPrincipal);
        janela.setVisible(true);
    }
}
