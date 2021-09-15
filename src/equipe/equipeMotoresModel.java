package equipe;

import javafx.beans.property.SimpleStringProperty;

public class equipeMotoresModel {
    SimpleStringProperty motor;
    SimpleStringProperty nacionalidade;
    SimpleStringProperty anoInicio;
    SimpleStringProperty anoFim;

    public equipeMotoresModel(SimpleStringProperty motor, SimpleStringProperty nacionalidade, SimpleStringProperty anoInicio, SimpleStringProperty anoFim) {
        this.motor = motor;
        this.nacionalidade = nacionalidade;
        this.anoFim = anoFim;
        this.anoInicio = anoInicio;
    }

    public String getMotor() {
        return this.motor.get();
    }

    public void setMotor(String motor) {
        this.motor.set(motor);
    }

    public SimpleStringProperty setMotorProperty() {
        return motor;
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
