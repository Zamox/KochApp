package de.kochapp.adapter.Datenpersistenz;

import de.kochapp.adapter.Datenpersistenz.Interface.ICSVPersistierbar;
import de.kochapp.domain.Rezept.Zutat;

public class CSVZutat implements ICSVPersistierbar {
    Zutat zutat;

    public CSVZutat(Zutat zutat) {
        this.zutat = zutat;
    }

    public enum CSVPosition {
        ZUATAT_ID,
        REZEPT_ID,
        ZUTAT_MENGE,
        ZUTAT_BEZEICHNUG
    }

    @Override
    public Object bekommeUUID() {
        return this.zutat.bekommeUUID();
    }

    public String[] bekommeCSVKopf() {
        return new String[]{"ZutatID","RezeptID","Menge","Name"};
    }

    public String[] bekommeCSVDaten() {
        String[] daten = new String[CSVPosition.values().length];
        daten[CSVPosition.ZUATAT_ID.ordinal()] = String.valueOf(this.zutat.bekommeUUID());
        daten[CSVPosition.REZEPT_ID.ordinal()] = String.valueOf(this.zutat.bekommeRezeptId());
        daten[CSVPosition.ZUTAT_MENGE.ordinal()] = String.valueOf(this.zutat.bekommeMenge());
        daten[CSVPosition.ZUTAT_BEZEICHNUG.ordinal()] = String.valueOf(this.zutat.bekommeBezeichnung());
        return daten;
    }
}
