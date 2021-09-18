package grandPrix.grandPrixResumo;

import javafx.beans.property.SimpleStringProperty;

public class grandPrixTempoModel {
    SimpleStringProperty dotd;
    SimpleStringProperty melhorVoltaTempo;
    SimpleStringProperty melhorVoltaPiloto;

    public grandPrixTempoModel(SimpleStringProperty dotd, SimpleStringProperty melhorVoltaTempo, SimpleStringProperty melhorVoltaPiloto) {
        this.dotd = dotd;
        this.melhorVoltaPiloto = melhorVoltaPiloto;
        this.melhorVoltaTempo = melhorVoltaTempo;
    }

    public String getDotd() {
        return this.dotd.get();
    }

    public void setDotd(String dotd) {
        this.dotd.set(dotd);
    }

    public SimpleStringProperty setDotdProperty() {
        return dotd;
    }

    public String getMelhorVoltaPiloto() {
        return this.melhorVoltaPiloto.get();
    }

    public void setMelhorVoltaPiloto(String melhorVoltaPiloto) {
        this.melhorVoltaPiloto.set(melhorVoltaPiloto);;
    }

    public SimpleStringProperty setMelhorVoltaPilotoProperty() {
        return melhorVoltaPiloto;
    }

    public String getMelhorVoltaTempo() {
        return this.melhorVoltaTempo.get();
    }

    public void setMelhorVoltaTempo(String melhorVoltaTempo) {
        this.melhorVoltaTempo.set(melhorVoltaTempo);
    }
    
    public SimpleStringProperty setMelhorVoltaTempoProperty() {
        return melhorVoltaTempo;
    }
}
