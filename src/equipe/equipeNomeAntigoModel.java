package equipe;

import javafx.beans.property.SimpleStringProperty;

public class equipeNomeAntigoModel {
    SimpleStringProperty nomeAntigo;
    SimpleStringProperty anoInicio;
    SimpleStringProperty anoFim;

    public equipeNomeAntigoModel(SimpleStringProperty nomeAntigo, SimpleStringProperty anoInicio, SimpleStringProperty anoFim) {
        this.nomeAntigo = nomeAntigo;
        this.anoFim = anoFim;
        this.anoInicio = anoInicio;
    }

    public String getNomeAntigo() {
        return this.nomeAntigo.get();
    }

    public void setNomeAntigo(String nomeAntigo) {
        this.nomeAntigo.set(nomeAntigo);
    }

    public SimpleStringProperty setNomeAntigoProperty() {
        return nomeAntigo;
    }

    public String getAnoFim() {
        return this.anoFim.get();
    }

    public void setAnoFim(String anoFim) {
        this.anoFim.set(anoFim);;
    }

    public SimpleStringProperty setAnoFimProperty() {
        return anoFim;
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
}
