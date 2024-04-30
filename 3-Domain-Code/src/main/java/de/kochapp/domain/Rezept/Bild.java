package de.kochapp.domain.Rezept;

import de.kochapp.domain.Interface.IPersistierbar;

import java.util.UUID;

/* Bild Klasse: Definiert ein Bild eines Rezeptes */
public class Bild implements IPersistierbar{
    private final UUID bild_id;
    private final UUID rezept_id;
    private final String speicherpfad;

    public Bild(UUID bild_id, UUID rezept_id, String speicherpfad) {
        this.bild_id = bild_id;
        this.rezept_id = rezept_id;
        this.speicherpfad = speicherpfad;
    }

    public Bild(UUID rezept_id, String speicherpfad) {
        this.bild_id = UUID.randomUUID();
        this.rezept_id = rezept_id;
        this.speicherpfad = speicherpfad;
    }

    public UUID bekommeUUID() {
        return bild_id;
    }

    public String bekommeSpeicherpfad() {
        return speicherpfad;
    }

    public UUID bekommeRezeptID() {
        return rezept_id;
    }

}
