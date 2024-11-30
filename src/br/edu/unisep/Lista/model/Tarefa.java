package br.edu.unisep.Lista.model;

public class Tarefa {
    String tarefa;
    boolean status;

    public Tarefa(){}

    public Tarefa(String tarefa, boolean status)
    {
        this.tarefa = tarefa;
        this.status = status;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(int status) {
        boolean valor = false;
        if(status != 0) valor = true;
        this.status = valor;
    }
}