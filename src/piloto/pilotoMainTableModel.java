package piloto;

import javafx.beans.property.SimpleStringProperty;

public class pilotoMainTableModel {
    SimpleStringProperty nome;
    SimpleStringProperty sobrenome;
    SimpleStringProperty numero;
    SimpleStringProperty abreviacao;
    SimpleStringProperty nascimento;
    SimpleStringProperty cidade;
    SimpleStringProperty nacionalidade;
    SimpleStringProperty numPoles;
    SimpleStringProperty numVitorias;

    public pilotoMainTableModel(SimpleStringProperty nome, SimpleStringProperty sobrenome, SimpleStringProperty numero, SimpleStringProperty abreviacao, SimpleStringProperty nascimento, SimpleStringProperty cidade, SimpleStringProperty nacionalidade, SimpleStringProperty numPoles, SimpleStringProperty numVitorias) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.cidade = cidade;
        this.sobrenome = sobrenome;
        this.numero = numero;
        this.abreviacao = abreviacao;
        this.nascimento = nascimento;
        this.numPoles = numPoles;
        this.numVitorias = numVitorias;
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

    public String getNumero() {
        return this.numero.get();
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }
    
    public SimpleStringProperty setNumeroProperty() {
        return numero;
    }

    public String getAbreviacao() {
        return this.abreviacao.get();
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao.set(abreviacao);
    }
    
    public SimpleStringProperty setAbreviacaoProperty() {
        return abreviacao;
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

    public String getNumPoles() {
        return this.numPoles.get();
    }

    public void setNumPoles(String numPoles) {
        this.numPoles.set(numPoles);
    }
    
    public SimpleStringProperty setNumPolesProperty() {
        return numPoles;
    }

    public String getNumVitorias() {
        return this.numVitorias.get();
    }

    public void setNumVitorias(String numVitorias) {
        this.numVitorias.set(numVitorias);
    }
    
    public SimpleStringProperty setNumVitoriasProperty() {
        return numVitorias;
    }
}
