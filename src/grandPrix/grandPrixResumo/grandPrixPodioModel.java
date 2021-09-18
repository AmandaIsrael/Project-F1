package grandPrix.grandPrixResumo;

import javafx.beans.property.SimpleStringProperty;  

public class grandPrixPodioModel {
    SimpleStringProperty primeiro;
    SimpleStringProperty segundo;
    SimpleStringProperty terceiro;

    public grandPrixPodioModel(SimpleStringProperty primeiro, SimpleStringProperty segundo, SimpleStringProperty terceiro) {
        this.primeiro = primeiro;
        this.terceiro = terceiro;
        this.segundo = segundo;
    }

    public String getPrimeiro() {
        return this.primeiro.get();
    }

    public void setPrimeiro(String primeiro) {
        this.primeiro.set(primeiro);
    }

    public SimpleStringProperty setPrimeiroProperty() {
        return primeiro;
    }

    public String getTerceiro() {
        return this.terceiro.get();
    }

    public void setTerceiro(String terceiro) {
        this.terceiro.set(terceiro);;
    }

    public SimpleStringProperty setTerceiroProperty() {
        return terceiro;
    }

    public String getSegundo() {
        return this.segundo.get();
    }

    public void setSegundo(String segundo) {
        this.segundo.set(segundo);
    }
    
    public SimpleStringProperty setSegundoProperty() {
        return segundo;
    }
}
