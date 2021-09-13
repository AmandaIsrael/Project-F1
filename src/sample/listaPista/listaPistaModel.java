package sample.listaPista;

import javafx.beans.property.SimpleStringProperty;

public class listaPistaModel{
    SimpleStringProperty nome;
    SimpleStringProperty pais;
    SimpleStringProperty cidade;


    public listaPistaModel(SimpleStringProperty nome, SimpleStringProperty pais, SimpleStringProperty cidade) {
        this.nome = nome;
        this.pais = pais;
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

    public String getPais() {
        return this.pais.get();
    }

    public void setPais(String pais) {
        this.pais.set(pais);
    }
    
    public SimpleStringProperty setPaisProperty() {
        return pais;
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