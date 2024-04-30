package de.kochapp.adapter.Datenpersistenz;

import de.kochapp.adapter.Datenpersistenz.Interface.ICSVPersistierbar;
import de.kochapp.domain.Rezept.Rezept;
import de.kochapp.domain.Kategorie.Kategorie;

import java.util.ArrayList;
import java.util.List;

public class CSVRezept implements ICSVPersistierbar {
    Rezept rezept;

    public CSVRezept(Rezept rezept) {
        this.rezept = rezept;
    }

    public enum CSVPosition {
        REZEPT_ID,
        REZEPT_TITEL,
        REZEPT_SCHWIERIGKEITSGRAD,
        REZEPT_BESCHREIBUNG
    }

    @Override
    public Object bekommeUUID() {
        return this.rezept.bekommeUUID();
    }

    public String[] bekommeCSVKopf() {
        return new String[]{"RezeptID","Titel","Schwierigkeitsgrad","Beschreibung"};
    }

    public String[] bekommeCSVDaten() {
        String[] daten = new String[CSVPosition.values().length];
        daten[CSVPosition.REZEPT_ID.ordinal()] = String.valueOf(this.rezept.bekommeUUID());
        daten[CSVPosition.REZEPT_TITEL.ordinal()] = String.valueOf(this.rezept.bekommeTitel());
        daten[CSVPosition.REZEPT_SCHWIERIGKEITSGRAD.ordinal()] = String.valueOf(this.rezept.bekommeSchwierigkeitsgrad());
        daten[CSVPosition.REZEPT_BESCHREIBUNG.ordinal()] = String.valueOf(this.rezept.bekommeBeschreibung());
        return daten;
    }

    public List<String[]> bekommeKategorienArray() {
        List<String[]> kategorienListe = new ArrayList<>();
        for (Kategorie kategorie : this.rezept.bekommeKategorien()) {
            String[] daten = new String[2];
            daten[0] = String.valueOf(this.rezept.bekommeUUID());
            daten[1] = String.valueOf(kategorie.bekommeUUID());
            kategorienListe.add(daten);
        }
        return kategorienListe;
    }
}
