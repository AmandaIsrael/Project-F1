package equipe;

import javafx.beans.property.SimpleStringProperty;

public class equipeMainTableModel {
    SimpleStringProperty nome;
    SimpleStringProperty cidade;
    SimpleStringProperty nacionalidade;

    public equipeMainTableModel(SimpleStringProperty nome, SimpleStringProperty nacionalidade, SimpleStringProperty cidade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.cidade = cidade;
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

    public String getNacionalidade() {
        return this.nacionalidade.get();
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade.set(nacionalidade);
    }
    
    public SimpleStringProperty setNacionalidadeProperty() {
        return nacionalidade;
    }

    public String getCidade() {
        return this.cidade.get();
    }

    public void setCidade(String cidade) {
        this.cidade.set(cidade);;
    }

    public SimpleStringProperty setCidadeProperty() {
        return cidade;
    }
}
