package br.edu.unisep.Lista.util;

import br.edu.unisep.Lista.model.Tarefa;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Funcoes {

    private static final String DIRETORIO_LISTA = "C:\\Lista";
    private static final String ARQUIVO_TAREFAS = "tarefas.txt";

    public Funcoes() {}

    public void validarExistencia() {
        File pasta = new File(DIRETORIO_LISTA);
        try {
            if (!pasta.exists() && pasta.mkdir()) {
                System.out.println("Diretório criado em: " + DIRETORIO_LISTA);
            }
            File arquivoTarefas = new File(pasta, ARQUIVO_TAREFAS);
            if (!arquivoTarefas.exists() && arquivoTarefas.createNewFile()) {
                System.out.println("Arquivo de tarefas criado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao criar diretório ou arquivo!");
        }
    }

    public List<Tarefa> lerTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        File arquivo = new File(DIRETORIO_LISTA, ARQUIVO_TAREFAS);

        if (arquivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (dados.length == 2) {
                        String descricao = dados[0];
                        boolean concluida = "1".equals(dados[1]);
                        tarefas.add(new Tarefa(descricao, concluida));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de tarefas.");
            }
        }
        return tarefas;
    }

    public List<Tarefa> excluirTarefa(List<Tarefa> tarefas) {
        try {
            String idExcluso = JOptionPane.showInputDialog("Qual o número da tarefa que deseja excluir?");
            if (idExcluso != null && !idExcluso.isEmpty()) {
                int id = Integer.parseInt(idExcluso);
                if (id > 0 && id <= tarefas.size()) {
                    tarefas.remove(id - 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Número inválido.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida! Por favor, insira um número.");
        }
        return tarefas;
    }

    public List<Tarefa> marcarComoConcluido(List<Tarefa> tarefas) {
        try {
            String index = JOptionPane.showInputDialog("Digite o número do item que concluiu:");
            if (index != null && !index.isEmpty()) {
                int id = Integer.parseInt(index);
                if (id > 0 && id <= tarefas.size()) {
                    tarefas.get(id - 1).setStatus(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Número inválido.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida! Por favor, insira um número.");
        }
        return tarefas;
    }

    public List<Tarefa> adicionarTarefa(List<Tarefa> tarefas) {
        String descricao = JOptionPane.showInputDialog("Digite a nova tarefa:");
        if (descricao != null && !descricao.trim().isEmpty()) {
            tarefas.add(new Tarefa(descricao.trim(), false));
        } else {
            JOptionPane.showMessageDialog(null, "Descrição inválida. Tarefa não adicionada.");
        }
        return tarefas;
    }

    public void sairESalvar(List<Tarefa> tarefas) {
        File arquivo = new File(DIRETORIO_LISTA, ARQUIVO_TAREFAS);

        try (FileWriter fw = new FileWriter(arquivo, false)) {
            for (Tarefa tarefa : tarefas) {
                String status = tarefa.isStatus() ? "1" : "0";
                fw.write(tarefa.getTarefa() + ";" + status + "\n");
            }
            JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar as tarefas.");
        }
    }
}
