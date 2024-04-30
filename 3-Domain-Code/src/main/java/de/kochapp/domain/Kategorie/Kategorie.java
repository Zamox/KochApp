package de.kochapp.domain.Kategorie;

import de.kochapp.domain.Interface.IPersistierbar;

import java.util.UUID;

/* Kategorie Klasse: Definiert eine Kategorie eines Rezeptes */
public class Kategorie implements IPersistierbar{
    private final UUID kategorie_id;
    private final String kategorie_bezeichnung;
    private final String kategorie_kurzform;
    private final String kategorie_beschreibung;

    public Kategorie(String bezeichnung, String kurzform, String beschreibung) {
        this.kategorie_id = UUID.randomUUID();
        this.kategorie_bezeichnung = bezeichnung;
        this.kategorie_kurzform = kurzform;
        this.kategorie_beschreibung = beschreibung;
    }

    public Kategorie(UUID kategorie_id, String bezeichnung, String kurzform, String beschreibung) {
        this.kategorie_id = kategorie_id;
        this.kategorie_bezeichnung = bezeichnung;
        this.kategorie_kurzform = kurzform;
        this.kategorie_beschreibung = beschreibung;
    }


    public String toString() {
        return this.kategorie_bezeichnung;
    }

    public Object bekommeUUID() {
        return kategorie_id;
    }

    public String bekommeName() {
        return kategorie_bezeichnung;
    }

    public String bekommeKurzform() {
        return kategorie_kurzform;
    }

    public String bekommeBeschreibung() {
        return kategorie_beschreibung;
    }

}
