package equipe;

import javafx.beans.property.SimpleStringProperty;

public class equipeLiderModel {
    SimpleStringProperty nome;
    SimpleStringProperty nacionalidade;
    SimpleStringProperty cidade;
    SimpleStringProperty sobrenome;
    SimpleStringProperty cargo;
    SimpleStringProperty nascimento;
    SimpleStringProperty anoInicio;
    SimpleStringProperty anoFim;

    public equipeLiderModel(SimpleStringProperty nome, SimpleStringProperty sobrenome, SimpleStringProperty cargo, SimpleStringProperty nascimento, SimpleStringProperty cidade, SimpleStringProperty nacionalidade, SimpleStringProperty anoInicio, SimpleStringProperty anoFim) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.cidade = cidade;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.anoInicio = anoInicio;
        this.anoFim = anoFim;
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

    public String getCargo() {
        return this.cargo.get();
    }

    public void setCargo(String cargo) {
        this.cargo.set(cargo);
    }
    
    public SimpleStringProperty setCargoProperty() {
        return cargo;
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

    public String getNascimento() {
        return this.nascimento.get();
    }

    public void setNascimento(String nascimento) {
        this.nascimento.set(nascimento);
    }
    
    public SimpleStringProperty setNascimentoProperty() {
        return nascimento;
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

    public String getAnoInicio() {
        return this.anoInicio.get();
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio.set(anoInicio);
    }
    
    public SimpleStringProperty setAnoInicioProperty() {
        return anoInicio;
    }

    public String getAnoFim() {
        return this.anoFim.get();
    }

    public void setAnoFim(String anoFim) {
        this.anoFim.set(anoFim);
    }
    
    public SimpleStringProperty setAnoFimProperty() {
        return anoFim;
    }
}
