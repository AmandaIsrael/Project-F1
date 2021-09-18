package grandPrix.grandPrixResumo;

import javafx.beans.property.SimpleStringProperty;

public class grandPrixResumoMainTableModel {
    SimpleStringProperty nome;
    SimpleStringProperty dataInicio;
    SimpleStringProperty dataFim;
    SimpleStringProperty pista;

    public grandPrixResumoMainTableModel(SimpleStringProperty nome, SimpleStringProperty dataInicio, SimpleStringProperty dataFim, SimpleStringProperty pista) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.pista = pista;
        this.dataFim = dataFim;
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

    public String getDataInicio() {
        return this.dataInicio.get();
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio.set(dataInicio);
    }
    
    public SimpleStringProperty setDataInicioProperty() {
        return dataInicio;
    }

    public String getPista() {
        return this.pista.get();
    }

    public void setPista(String pista) {
        this.pista.set(pista);;
    }

    public SimpleStringProperty setPistaProperty() {
        return pista;
    }

    public String getDataFim() {
        return this.dataFim.get();
    }

    public void setDataFim(String dataFim) {
        this.dataFim.set(dataFim);
    }
    
    public SimpleStringProperty setDataFimProperty() {
        return dataFim;
    }
}
