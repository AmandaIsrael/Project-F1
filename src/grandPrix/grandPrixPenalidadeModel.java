package grandPrix;

import javafx.beans.property.SimpleStringProperty;

public class grandPrixPenalidadeModel {
    SimpleStringProperty nome;
    SimpleStringProperty sobrenome;
    SimpleStringProperty penalidade;
    SimpleStringProperty causa;

    public grandPrixPenalidadeModel(SimpleStringProperty nome, SimpleStringProperty sobrenome, SimpleStringProperty penalidade, SimpleStringProperty causa) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.causa = causa;
        this.penalidade = penalidade;
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

    public String getSobrenome() {
        return this.sobrenome.get();
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome.set(sobrenome);
    }
    
    public SimpleStringProperty setSobrenomeProperty() {
        return sobrenome;
    }

    public String getCausa() {
        return this.causa.get();
    }

    public void setCausa(String causa) {
        this.causa.set(causa);;
    }

    public SimpleStringProperty setCausaProperty() {
        return causa;
    }

    public String getPenalidade() {
        return this.penalidade.get();
    }

    public void setPenalidade(String penalidade) {
        this.penalidade.set(penalidade);
    }
    
    public SimpleStringProperty setPenalidadeProperty() {
        return penalidade;
    }
}
