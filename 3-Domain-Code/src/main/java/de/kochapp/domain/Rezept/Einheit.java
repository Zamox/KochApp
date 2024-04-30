package de.kochapp.domain.Rezept;

public enum Einheit {

    Kilogramm("kg", "Kilogramm"),
    Gramm("g", "Gramm"),
    Milliliter("ml", "Milliliter"),
    Liter("l", "Liter"),
    Esslöffel("EL", "Esslöffel"),
    Teelöffel("TL", "Teelöffel"),
    Stück("Stück", "Stück"),
    Packung("Pck", "Packung"),
    Priese("Priese", "Priese"),
    Glas("Glas", "Glas"),
    Dose("Dose", "Dose");

    private final String einheit_bezeichnung;
    private final String einheit_beschreibung;

    Einheit(String einheit_bezeichnung, String einheit_beschreibung) {
        if (einheit_bezeichnung.isEmpty()|| einheit_beschreibung.isEmpty()) {
            throw new IllegalArgumentException(
                    "Name oder Beschreibung dürfen nicht leer gelassen werden!");
        }
        this.einheit_bezeichnung = einheit_bezeichnung;
        this.einheit_beschreibung = einheit_beschreibung;
    }

    public String bekommeBezeichnung() {
        return this.einheit_bezeichnung;
    }

    public String bekommeBeschreibung() {
        return this.einheit_beschreibung;
    }

    @Override
    public String toString() {
        return einheit_bezeichnung;
    }
}
