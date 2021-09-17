package piloto;

import javafx.beans.property.SimpleStringProperty;

public class pilotoContratoModel {
    SimpleStringProperty equipe;
    SimpleStringProperty salario;
    SimpleStringProperty anoInicio;
    SimpleStringProperty anoFim;

    public pilotoContratoModel(SimpleStringProperty equipe, SimpleStringProperty anoInicio, SimpleStringProperty anoFim, SimpleStringProperty salario) {
        this.equipe = equipe;
        this.salario = salario;
        this.anoFim = anoFim;
        this.anoInicio = anoInicio;
    }

    public String getEquipe() {
        return this.equipe.get();
    }

    public void setEquipe(String equipe) {
        this.equipe.set(equipe);
    }

    public SimpleStringProperty setEquipeProperty() {
        return equipe;
    }

    public String getSalario() {
        return this.salario.get();
    }

    public void setSalario(String salario) {
        this.salario.set(salario);
    }
    
    public SimpleStringProperty setSalarioProperty() {
        return salario;
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
