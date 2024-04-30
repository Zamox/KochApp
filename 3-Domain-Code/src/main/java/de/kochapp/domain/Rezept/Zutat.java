package de.kochapp.domain.Rezept;

import de.kochapp.domain.Interface.IPersistierbar;

import java.util.UUID;

/* Zutat Klasse: Definiert eine Zutat eines Rezeptes */
public class Zutat implements IPersistierbar{
    private final UUID zutat_id;
    private final UUID rezept_id;
    private final Menge menge;
    private final String zutat_bezeichnung;

    public Zutat(UUID rezept_id, Menge menge, String zutat_bezeichnung) {
        this.zutat_id = UUID.randomUUID();
        this.rezept_id = rezept_id;
        this.menge = menge;
        this.zutat_bezeichnung = zutat_bezeichnung;
    }

    public Zutat(UUID zutat_id, UUID rezept_id, Menge menge, String zutat_bezeichnung) {
        this.zutat_id = zutat_id;
        this.rezept_id = rezept_id;
        this.menge = menge;
        this.zutat_bezeichnung = zutat_bezeichnung;
    }

    public UUID bekommeUUID() {
        return zutat_id;
    }

    public Menge bekommeMenge() {
        return menge;
    }

    public String bekommeBezeichnung() {
        return zutat_bezeichnung;
    }

    public UUID bekommeRezeptId() { return rezept_id; }

}
