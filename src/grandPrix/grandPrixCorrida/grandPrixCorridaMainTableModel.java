package grandPrix.grandPrixCorrida;

import javafx.beans.property.SimpleStringProperty;

public class grandPrixCorridaMainTableModel {
    SimpleStringProperty colocacao;
    SimpleStringProperty grid;
    SimpleStringProperty equipe;
    SimpleStringProperty num;
    SimpleStringProperty nome;
    SimpleStringProperty sobrenome;
    SimpleStringProperty pontos;
    SimpleStringProperty dnf;
    SimpleStringProperty substituto;

    public grandPrixCorridaMainTableModel(SimpleStringProperty colocacao, SimpleStringProperty num, SimpleStringProperty nome, SimpleStringProperty sobrenome, SimpleStringProperty equipe, SimpleStringProperty grid, SimpleStringProperty pontos, SimpleStringProperty dnf, SimpleStringProperty substituto) {
        this.colocacao = colocacao;
        this.grid = grid;
        this.equipe = equipe;
        this.num = num;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.pontos = pontos;
        this.dnf = dnf;
        this.substituto = substituto;
    }

    public String getColocacao() {
        return this.colocacao.get();
    }

    public void setColocacao(String colocacao) {
        this.colocacao.set(colocacao);
    }

    public SimpleStringProperty setColocacaoProperty() {
        return colocacao;
    }

    public String getNum() {
        return this.num.get();
    }

    public void setNum(String num) {
        this.num.set(num);
    }
    
    public SimpleStringProperty setNumProperty() {
        return num;
    }

    public String getNome() {
        return this.nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }
    
    public SimpleStringProperty setNomeProperty() {
        return nome;
    }

    public String getGrid() {
        return this.grid.get();
    }

    public void setGrid(String grid) {
        this.grid.set(grid);
    }
    
    public SimpleStringProperty setGridProperty() {
        return grid;
    }

    public String getSobrenome() {
        return this.sobrenome.get();
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome.set(sobrenome);
    }
    
    public SimpleStringProperty setSobrenomeProperty() {
        return sobrenome;
    }

    public String getEquipe() {
        return this.equipe.get();
    }

    public void setEquipe(String equipe) {
        this.equipe.set(equipe);;
    }

    public SimpleStringProperty setEquipeProperty() {
        return equipe;
    }

    public String getPontos() {
        return this.pontos.get();
    }

    public void setPontos(String pontos) {
        this.pontos.set(pontos);
    }
    
    public SimpleStringProperty setPontosProperty() {
        return pontos;
    }

    public String getDnf() {
        return this.dnf.get();
    }

    public void setDnf(String dnf) {
        this.dnf.set(dnf);
    }
    
    public SimpleStringProperty setDnfProperty() {
        return dnf;
    }

    public String getSubstituto() {
        return this.substituto.get();
    }

    public void setSubstituto(String substituto) {
        this.substituto.set(substituto);;
    }

    public SimpleStringProperty setSubstitutoProperty() {
        return substituto;
    }
}
