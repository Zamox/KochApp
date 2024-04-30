package de.kochapp.domain.Rezept;

import de.kochapp.domain.Interface.IPersistierbar;
import de.kochapp.domain.Kategorie.Kategorie;

import java.util.ArrayList;
import java.util.UUID;

/* Rezept Klasse: Definiert ein Rezept */
public class Rezept implements IPersistierbar{
    private final UUID rezept_id;
    private String rezept_titel;
    private ArrayList<Kategorie> kategorien;
    private ArrayList<Zutat> zutaten;
    private String rezept_beschreibung;
    private Schwierigkeit schwierigkeitsgrad;
    private Bild bild;

    public Rezept(String rezept_titel, ArrayList<Kategorie> kategorien, ArrayList<Zutat> zutaten, String rezept_beschreibung, Schwierigkeit schwierigkeitsgrad, Bild bild) {
        this.rezept_id = UUID.randomUUID();
        this.rezept_titel = rezept_titel;
        this.kategorien = kategorien;
        this.zutaten = zutaten;
        this.rezept_beschreibung = rezept_beschreibung;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.bild = bild;
    }

    public Rezept(String rezept_titel, ArrayList<Kategorie> kategorien, ArrayList<Zutat> zutaten, String rezept_beschreibung, Schwierigkeit schwierigkeitsgrad) {
        this.rezept_id = UUID.randomUUID();
        this.rezept_titel = rezept_titel;
        this.kategorien = kategorien;
        this.zutaten = zutaten;
        this.rezept_beschreibung = rezept_beschreibung;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public Rezept(UUID rezept_id, String rezept_titel, ArrayList<Kategorie> kategorien, ArrayList<Zutat> zutaten, String rezept_beschreibung, Schwierigkeit schwierigkeitsgrad) {
        this.rezept_id = rezept_id;
        this.rezept_titel = rezept_titel;
        this.kategorien = kategorien;
        this.zutaten = zutaten;
        this.rezept_beschreibung = rezept_beschreibung;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public Rezept(UUID rezept_id, String rezept_titel, ArrayList<Kategorie> kategorien, ArrayList<Zutat> zutaten, String rezept_beschreibung, Schwierigkeit schwierigkeitsgrad, Bild bild) {
        this.rezept_id = rezept_id;
        this.rezept_titel = rezept_titel;
        this.kategorien = kategorien;
        this.zutaten = zutaten;
        this.rezept_beschreibung = rezept_beschreibung;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.bild = bild;
    }

    public Rezept() {
        this.rezept_id = UUID.randomUUID();
    }

    public UUID bekommeUUID() {
        return rezept_id;
    }

    public String bekommeTitel() {
        return rezept_titel;
    }

    public ArrayList<Kategorie> bekommeKategorien() {
        return kategorien;
    }

    public ArrayList<Zutat> bekommeZutaten() {
        return zutaten;
    }

    public String bekommeBeschreibung() {
        return rezept_beschreibung;
    }

    public Schwierigkeit bekommeSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public Bild bekommeBild() {
        return bild;
    }

    public void setzeTitel(String titel) {
        this.rezept_titel = titel;
    }

    public void setzeKategorien(ArrayList<Kategorie> kategorien) {
        this.kategorien = kategorien;
    }

    public void setzeZutaten(ArrayList<Zutat> zutaten) {
        this.zutaten = zutaten;
    }

    public void setzeBeschreibung(String beschreibung) {
        this.rezept_beschreibung = beschreibung;
    }

    public void setzeSchwierigkeitsgrad(Schwierigkeit schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public void setzeBild(Bild bild) {
        this.bild = bild;
    }
}
