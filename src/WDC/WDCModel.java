package WDC;

import javafx.beans.property.SimpleStringProperty;

public class WDCModel {
    SimpleStringProperty equipe;
    SimpleStringProperty aut;
    SimpleStringProperty sty;
    SimpleStringProperty hun;
    SimpleStringProperty gbr;
    SimpleStringProperty g70a;
    SimpleStringProperty esp;
    SimpleStringProperty bel;
    SimpleStringProperty ita;
    SimpleStringProperty tus;
    SimpleStringProperty rus;
    SimpleStringProperty eif;
    SimpleStringProperty por;
    SimpleStringProperty emi;
    SimpleStringProperty tur;
    SimpleStringProperty bhr;
    SimpleStringProperty skh;
    SimpleStringProperty abu;
    SimpleStringProperty total;

    public WDCModel(SimpleStringProperty equipe, 
    SimpleStringProperty aut, 
    SimpleStringProperty sty, 
    SimpleStringProperty hun,
    SimpleStringProperty gbr,
    SimpleStringProperty g70a,
    SimpleStringProperty esp,
    SimpleStringProperty bel,
    SimpleStringProperty ita,
    SimpleStringProperty tus,
    SimpleStringProperty rus,
    SimpleStringProperty eif,
    SimpleStringProperty por,
    SimpleStringProperty emi,
    SimpleStringProperty tur,
    SimpleStringProperty bhr,
    SimpleStringProperty skh,
    SimpleStringProperty abu,
    SimpleStringProperty total) {
        this.equipe = equipe;
        this.aut = aut;
        this.sty = sty;
        this.hun = hun;
        this.gbr = gbr;
        this.g70a = g70a;
        this.esp = esp;
        this.bel = bel;
        this.ita = ita;
        this.tus = tus;
        this.rus = rus;
        this.eif = eif;
        this.por = por;
        this.emi = emi;
        this.tur = tur;
        this.bhr = bhr;
        this.skh = skh;
        this.abu = abu;
        this.total = total;
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

    public String getAut() {
        return this.aut.get();
    }

    public void setAut(String aut) {
        this.aut.set(aut);
    }
    
    public SimpleStringProperty setAutProperty() {
        return aut;
    }

    public String getSty() {
        return this.sty.get();
    }

    public void setSty(String sty) {
        this.sty.set(sty);
    }
    
    public SimpleStringProperty setStyProperty() {
        return sty;
    }

    public String getHun() {
        return this.hun.get();
    }

    public void setHun(String hun) {
        this.hun.set(hun);
    }
    
    public SimpleStringProperty setHunProperty() {
        return hun;
    }

    public String getGbr() {
        return this.gbr.get();
    }

    public void setGbr(String gbr) {
        this.gbr.set(gbr);
    }
    
    public SimpleStringProperty setGbrProperty() {
        return gbr;
    }

    public String getG70a() {
        return this.g70a.get();
    }

    public void setG70a(String g70a) {
        this.g70a.set(g70a);
    }
    
    public SimpleStringProperty setG70aProperty() {
        return g70a;
    }
    public String getEsp() {
        return this.esp.get();
    }

    public void setEsp(String esp) {
        this.esp.set(esp);
    }
    
    public SimpleStringProperty setEspProperty() {
        return esp;
    }
    public String getBel() {
        return this.bel.get();
    }

    public void setBel(String bel) {
        this.bel.set(bel);
    }
    
    public SimpleStringProperty setBelProperty() {
        return bel;
    }
    public String getIta() {
        return this.ita.get();
    }

    public void setIta(String ita) {
        this.ita.set(ita);
    }
    
    public SimpleStringProperty setItaProperty() {
        return ita;
    }
    public String getTus() {
        return this.tus.get();
    }

    public void setTus(String tus) {
        this.tus.set(tus);
    }
    
    public SimpleStringProperty setTusProperty() {
        return tus;
    }
    public String getRus() {
        return this.rus.get();
    }

    public void setRus(String rus) {
        this.rus.set(rus);
    }
    
    public SimpleStringProperty setRusProperty() {
        return rus;
    }
    public String getEif() {
        return this.eif.get();
    }

    public void setEif(String eif) {
        this.eif.set(eif);
    }
    
    public SimpleStringProperty setEifProperty() {
        return eif;
    }
    public String getPor() {
        return this.por.get();
    }

    public void setPor(String por) {
        this.por.set(por);
    }
    
    public SimpleStringProperty setPorProperty() {
        return por;
    }
    public String getEmi() {
        return this.emi.get();
    }

    public void setEmi(String emi) {
        this.emi.set(emi);
    }
    
    public SimpleStringProperty setEmiProperty() {
        return emi;
    }
    public String getTur() {
        return this.tur.get();
    }

    public void setTur(String tur) {
        this.tur.set(tur);
    }
    
    public SimpleStringProperty setTurProperty() {
        return tur;
    }
    
    public String getBhr() {
        return this.bhr.get();
    }

    public void setBhr(String bhr) {
        this.bhr.set(bhr);
    }
    
    public SimpleStringProperty setBhrProperty() {
        return bhr;
    }
    public String getSkh() {
        return this.skh.get();
    }

    public void setSkh(String skh) {
        this.skh.set(skh);
    }
    
    public SimpleStringProperty setSkhProperty() {
        return skh;
    }

    public String getAbu() {
        return this.abu.get();
    }

    public void setAbu(String abu) {
        this.abu.set(abu);
    }
    
    public SimpleStringProperty setAbuProperty() {
        return abu;
    }

    
    public String getTotal() {
        return this.total.get();
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
    
    public SimpleStringProperty setTotalProperty() {
        return total;
    }
}
