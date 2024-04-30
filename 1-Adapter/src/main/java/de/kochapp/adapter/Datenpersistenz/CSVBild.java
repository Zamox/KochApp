package de.kochapp.adapter.Datenpersistenz;

import de.kochapp.adapter.Datenpersistenz.Interface.ICSVPersistierbar;
import de.kochapp.domain.Rezept.Bild;

public class CSVBild implements ICSVPersistierbar {

    Bild bild;

    public CSVBild(Bild bild) {
        this.bild = bild;
    }

    public enum CSVPosition {
        BILD_ID,
        REZEPT_ID,
        BILD_SPEICHERPFAD
    }

    @Override
    public Object bekommeUUID() {
        return bild.bekommeUUID();
    }

    public String[] bekommeCSVKopf() {
        return new String[]{"BildID", "RezeptID", "Speicherpfad"};
    }

    @Override
    public String[] bekommeCSVDaten() {
        String[] data = new String[CSVPosition.values().length];
        data[CSVPosition.BILD_ID.ordinal()] = String.valueOf(this.bild.bekommeUUID());
        data[CSVPosition.REZEPT_ID.ordinal()] = String.valueOf(this.bild.bekommeRezeptID());
        data[CSVPosition.BILD_SPEICHERPFAD.ordinal()] = String.valueOf(this.bild.bekommeSpeicherpfad());
        return data;
    }
}
