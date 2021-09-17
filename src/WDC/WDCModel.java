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

    public String getg70a() {
        return this.g70a.get();
    }

    public void setg70a(String g70a) {
        this.g70a.set(g70a);
    }
    
    public SimpleStringProperty setg70aProperty() {
        return g70a;
    }
    public String getesp() {
        return this.esp.get();
    }

    public void setesp(String esp) {
        this.esp.set(esp);
    }
    
    public SimpleStringProperty setespProperty() {
        return esp;
    }
    public String getbel() {
        return this.bel.get();
    }

    public void setbel(String bel) {
        this.bel.set(bel);
    }
    
    public SimpleStringProperty setbelProperty() {
        return bel;
    }
    public String getita() {
        return this.ita.get();
    }

    public void setita(String ita) {
        this.ita.set(ita);
    }
    
    public SimpleStringProperty setitaProperty() {
        return ita;
    }
    public String gettus() {
        return this.tus.get();
    }

    public void settus(String tus) {
        this.tus.set(tus);
    }
    
    public SimpleStringProperty settusProperty() {
        return tus;
    }
    public String getrus() {
        return this.rus.get();
    }

    public void setrus(String rus) {
        this.rus.set(rus);
    }
    
    public SimpleStringProperty setrusProperty() {
        return rus;
    }
    public String geteif() {
        return this.eif.get();
    }

    public void seteif(String eif) {
        this.eif.set(eif);
    }
    
    public SimpleStringProperty seteifProperty() {
        return eif;
    }
    public String getpor() {
        return this.por.get();
    }

    public void setpor(String por) {
        this.por.set(por);
    }
    
    public SimpleStringProperty setporProperty() {
        return por;
    }
    public String getemi() {
        return this.emi.get();
    }

    public void setemi(String emi) {
        this.emi.set(emi);
    }
    
    public SimpleStringProperty setemiProperty() {
        return emi;
    }
    public String gettur() {
        return this.tur.get();
    }

    public void settur(String tur) {
        this.tur.set(tur);
    }
    
    public SimpleStringProperty setturProperty() {
        return tur;
    }
    
    public String getbhr() {
        return this.bhr.get();
    }

    public void setbhr(String bhr) {
        this.bhr.set(bhr);
    }
    
    public SimpleStringProperty setbhrProperty() {
        return bhr;
    }
    public String getskh() {
        return this.skh.get();
    }

    public void setskh(String skh) {
        this.skh.set(skh);
    }
    
    public SimpleStringProperty setskhProperty() {
        return skh;
    }

    public String getabu() {
        return this.abu.get();
    }

    public void setabu(String abu) {
        this.abu.set(abu);
    }
    
    public SimpleStringProperty setabuProperty() {
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
