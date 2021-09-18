package grandPrix.grandPrixQuali;

import javafx.beans.property.SimpleStringProperty;

public class grandPrixQualiMainTableModel {
    SimpleStringProperty posicao;
    SimpleStringProperty tempo;
    SimpleStringProperty equipe;
    SimpleStringProperty num;
    SimpleStringProperty nome;
    SimpleStringProperty sobrenome;
    SimpleStringProperty substituto;

    public grandPrixQualiMainTableModel(SimpleStringProperty posicao, SimpleStringProperty num, SimpleStringProperty nome, SimpleStringProperty sobrenome, SimpleStringProperty equipe, SimpleStringProperty tempo, SimpleStringProperty substituto) {
        this.posicao = posicao;
        this.tempo = tempo;
        this.equipe = equipe;
        this.num = num;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.substituto = substituto;
    }

    public String getPosicao() {
        return this.posicao.get();
    }

    public void setPosicao(String posicao) {
        this.posicao.set(posicao);
    }

    public SimpleStringProperty setPosicaoProperty() {
        return posicao;
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

    public String getTempo() {
        return this.tempo.get();
    }

    public void setTempo(String tempo) {
        this.tempo.set(tempo);
    }
    
    public SimpleStringProperty setTempoProperty() {
        return tempo;
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
