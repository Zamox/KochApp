package de.kochapp.adapter.Datenpersistenz;

import de.kochapp.adapter.Datenpersistenz.Interface.ICSVPersistierbar;
import de.kochapp.domain.Kategorie.Kategorie;

public class CSVKategorie implements ICSVPersistierbar {
    Kategorie kategorie;

    public CSVKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public enum CSVPosition {
        KATEGORIE_ID,
        KATEGORIE_TITEL,
        KATEGORIE_KURZFORM,
        KATEGORIE_BESCHREIBUNG
    }

    @Override
    public Object bekommeUUID() {
        return this.kategorie.bekommeUUID();
    }

    public String[] bekommeCSVKopf() {
        return new String[]{"KategorieID","Name","Tag","Beschreibung"};
    }

    @Override
    public String[] bekommeCSVDaten() {
        String[] daten = new String[CSVKategorie.CSVPosition.values().length];
        daten[CSVKategorie.CSVPosition.KATEGORIE_ID.ordinal()] = String.valueOf(this.kategorie.bekommeUUID());
        daten[CSVKategorie.CSVPosition.KATEGORIE_TITEL.ordinal()] = String.valueOf(this.kategorie.bekommeName());
        daten[CSVKategorie.CSVPosition.KATEGORIE_KURZFORM.ordinal()] = String.valueOf(this.kategorie.bekommeKurzform());
        daten[CSVKategorie.CSVPosition.KATEGORIE_BESCHREIBUNG.ordinal()] = String.valueOf(this.kategorie.bekommeBeschreibung());
        return daten;
    }
}
