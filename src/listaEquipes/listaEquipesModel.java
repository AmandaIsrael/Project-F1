package listaEquipes;
import javafx.beans.property.SimpleStringProperty;

public class listaEquipesModel {
    SimpleStringProperty nome;
    SimpleStringProperty nacionalidade;

    public listaEquipesModel(SimpleStringProperty nome, SimpleStringProperty nacionalidade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
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
}
