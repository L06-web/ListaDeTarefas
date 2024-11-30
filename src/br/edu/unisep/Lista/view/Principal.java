package br.edu.unisep.Lista.view;

import br.edu.unisep.Lista.util.Funcoes;
import br.edu.unisep.Lista.model.Tarefa;
import br.edu.unisep.Lista.view.telas.Listagem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Principal {

    public static void main(String[] args) {
        Funcoes funcoes = new Funcoes();
        funcoes.validarExistencia(); // Garante que os arquivos necessários existem
        AtomicReference<List<Tarefa>> tarefas = new AtomicReference<>(funcoes.lerTarefas()); // Lê as tarefas salvas previamente

        JFrame frame = new JFrame("Lista de Compras");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> {
            tarefas.set(funcoes.adicionarTarefa(tarefas.get()));
            JOptionPane.showMessageDialog(frame, "Tarefa adicionada com sucesso!");
        });

        JButton btnMarcarConcluida = new JButton("Marcar como concluída");
        btnMarcarConcluida.addActionListener(e -> {
            tarefas.set(funcoes.marcarComoConcluido(tarefas.get()));
            JOptionPane.showMessageDialog(frame, "Tarefa marcada como concluída!");
        });

        JButton btnRemover = new JButton("Remover da lista");
        btnRemover.addActionListener(e -> {
            tarefas.set(funcoes.excluirTarefa(tarefas.get()));
            JOptionPane.showMessageDialog(frame, "Tarefa removida com sucesso!");
        });

        JButton btnExibir = new JButton("Exibir a lista");
        btnExibir.addActionListener(e -> {
            Listagem listagem = new Listagem();
            listagem.Listagem(tarefas.get());
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            funcoes.sairESalvar(tarefas.get());
            JOptionPane.showMessageDialog(frame, "Tarefas salvas com sucesso!");
            System.exit(0);
        });

        painelPrincipal.add(criarPainelComBotao(btnAdicionar));
        painelPrincipal.add(criarPainelComBotao(btnMarcarConcluida));
        painelPrincipal.add(criarPainelComBotao(btnRemover));
        painelPrincipal.add(criarPainelComBotao(btnExibir));
        painelPrincipal.add(criarPainelComBotao(btnSair));

        frame.add(painelPrincipal);
        frame.setVisible(true);
    }

    private static JPanel criarPainelComBotao(JButton botao) {
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painel.add(botao);
        return painel;
    }
}
