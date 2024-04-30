package de.kochapp.adapter.Datenpersistenz;

import de.kochapp.domain.Kategorie.Kategorie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CSVKategorieTest {

    @Test
    void test_bekommeUUID() {
        // Arrange
        UUID kategorieId = UUID.randomUUID();
        String bezeichnung = "Hauptgerichte";
        String kurzform = "HG";
        String beschreibung = "Kategorie für Hauptgerichte";
        Kategorie kategorie = new Kategorie(kategorieId, bezeichnung, kurzform, beschreibung);

        // Act
        CSVKategorie csvKategorie = new CSVKategorie(kategorie);

        // Assert
        Assertions.assertEquals(kategorieId, csvKategorie.bekommeUUID());
    }

    @Test
    void test_bekommeCSVKopf() {
        // Arrange
        Kategorie kategorie = new Kategorie("Hauptgerichte", "HG", "Kategorie für Hauptgerichte");
        CSVKategorie csvKategorie = new CSVKategorie(kategorie);

        // Act
        String[] kopf = csvKategorie.bekommeCSVKopf();

        // Assert
        Assertions.assertArrayEquals(new String[]{"KategorieID", "Name", "Tag", "Beschreibung"}, kopf);
    }

    @Test
    void test_bekommeCSVDaten() {
        // Arrange
        Kategorie kategorie = new Kategorie(UUID.fromString("12345678-1234-1234-1234-123456789012"), "Hauptgerichte", "HG", "Kategorie für Hauptgerichte");
        CSVKategorie csvKategorie = new CSVKategorie(kategorie);

        // Act
        String[] daten = csvKategorie.bekommeCSVDaten();

        // Assert
        Assertions.assertArrayEquals(new String[]{"12345678-1234-1234-1234-123456789012", "Hauptgerichte", "HG", "Kategorie für Hauptgerichte"}, daten);
    }
}
