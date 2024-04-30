package de.kochapp.domain.Kategorie;

import de.kochapp.domain.Kategorie.Kategorie;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KategorieTest {

    @Test
    void test_KonstruktorMitUUID() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        String bezeichnung = "TestKategorie";
        String kurzform = "TK";
        String beschreibung = "Test Beschreibung";

        // Act
        Kategorie kategorie = new Kategorie(uuid, bezeichnung, kurzform, beschreibung);

        // Assert
        assertEquals(uuid, kategorie.bekommeUUID());
        assertEquals(bezeichnung, kategorie.bekommeName());
        assertEquals(kurzform, kategorie.bekommeKurzform());
        assertEquals(beschreibung, kategorie.bekommeBeschreibung());
    }

    @Test
    void test_KonstruktorOhneUUID() {
        // Arrange
        String bezeichnung = "TestKategorie";
        String kurzform = "TK";
        String beschreibung = "Test Beschreibung";

        // Act
        Kategorie kategorie = new Kategorie(bezeichnung, kurzform, beschreibung);

        // Assert
        assertNotNull(kategorie.bekommeUUID()); // sicherstellen, dass UUID generiert wurde
        assertEquals(bezeichnung, kategorie.bekommeName());
        assertEquals(kurzform, kategorie.bekommeKurzform());
        assertEquals(beschreibung, kategorie.bekommeBeschreibung());
    }
}

