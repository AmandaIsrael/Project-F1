package ano;

public class anoModel {
    SimpleStringProperty nome;
    SimpleStringProperty pista;
    SimpleStringProperty data;

    public anoModel(SimpleStringProperty nome, SimpleStringProperty pista, SimpleStringProperty data) {
        this.nome = nome;
        this.pista = pista;
        this.data = data;
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

    public String getPista() {
        return this.pista.get();
    }

    public void setPista(String pista) {
        this.pista.set(pista);
    }
    
    public SimpleStringProperty setPistaProperty() {
        return pista;
    }

    public String getData() {
        return this.data.get();
    }

    public void setData(String data) {
        this.data.set(data);;
    }

    public SimpleStringProperty setDataProperty() {
        return data;
    }
}
