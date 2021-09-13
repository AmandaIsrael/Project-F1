package listaPilotos;
import javafx.beans.property.SimpleStringProperty;

public class listaPilotosModel {
    SimpleStringProperty nome;
    SimpleStringProperty sobrenome;


    public listaPilotosModel() {
    }

    public listaPilotosModel(SimpleStringProperty nome, SimpleStringProperty sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return this.nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getSobrenome() {
        return this.sobrenome.get();
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome.set(sobrenome);
    }

    public SimpleStringProperty getNomeProperty() {
        return this.nome;
    }

    public void setNomeProperty(SimpleStringProperty nome) {
        this.nome = nome;
    }

    public SimpleStringProperty getSobrenomeProperty() {
        return this.sobrenome;
    }

    public void setSobrenomeProperty(SimpleStringProperty sobrenome) {
        this.sobrenome = sobrenome;
    }

}
