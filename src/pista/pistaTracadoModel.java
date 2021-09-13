package pista;
import javafx.beans.property.SimpleStringProperty;

public class pistaTracadoModel {
    SimpleStringProperty anoAlteracao;
    SimpleStringProperty distancia;
    SimpleStringProperty numeroVoltas;


    public pistaTracadoModel(SimpleStringProperty anoAlteracao, SimpleStringProperty distancia, SimpleStringProperty numeroVoltas) {
        this.anoAlteracao = anoAlteracao;
        this.distancia = distancia;
        this.numeroVoltas = numeroVoltas;
    }
    
    public String getAnoAlteracao() {
        return this.anoAlteracao.get();
    }

    public void setAnoAlteracao(String anoAlteracao) {
        this.anoAlteracao.set(anoAlteracao);
    }

    public SimpleStringProperty setAnoAlteracaoProperty() {
        return anoAlteracao;
    }

    public String getDistancia() {
        return this.distancia.get();
    }

    public void setDistancia(String distancia) {
        this.distancia.set(distancia);
    }
    
    public SimpleStringProperty setDistanciaProperty() {
        return distancia;
    }

    public String getNumeroVoltas() {
        return this.numeroVoltas.get();
    }

    public void setNumeroVoltas(String numeroVoltas) {
        this.numeroVoltas.set(numeroVoltas);;
    }

    public SimpleStringProperty setNumeroVoltasProperty() {
        return numeroVoltas;
    }
}
