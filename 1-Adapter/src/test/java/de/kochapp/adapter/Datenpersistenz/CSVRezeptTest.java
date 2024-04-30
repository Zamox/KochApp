package de.kochapp.adapter.Datenpersistenz;

import de.kochapp.adapter.Datenpersistenz.CSVRezept;
import de.kochapp.domain.Rezept.Rezept;
import de.kochapp.domain.Rezept.Schwierigkeit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CSVRezeptTest {

    @Test
    void test_bekommeUUID() {
        // Arrange
        UUID rezeptId = UUID.randomUUID();
        Rezept rezept = new Rezept(rezeptId, "Test Rezept", new ArrayList<>(), new ArrayList<>(), "Test Beschreibung", Schwierigkeit.LEICHT);

        // Act
        CSVRezept csvRezept = new CSVRezept(rezept);

        // Assert
        Assertions.assertEquals(rezeptId, csvRezept.bekommeUUID());
    }

    @Test
    void test_bekommeCSVKopf() {
        // Arrange
        Rezept rezept = new Rezept(UUID.randomUUID(), "Test Rezept", new ArrayList<>(), new ArrayList<>(), "Test Beschreibung", Schwierigkeit.LEICHT);
        CSVRezept csvRezept = new CSVRezept(rezept);

        // Act
        String[] kopf = csvRezept.bekommeCSVKopf();

        // Assert
        Assertions.assertArrayEquals(new String[]{"RezeptID", "Titel", "Schwierigkeitsgrad", "Beschreibung"}, kopf);
    }

    @Test
    void test_bekommeCSVDaten() {
        // Arrange
        UUID rezeptId = UUID.fromString("12345678-1234-1234-1234-123456789012");
        Rezept rezept = new Rezept(rezeptId, "Test Rezept", new ArrayList<>(), new ArrayList<>(), "Test Beschreibung", Schwierigkeit.LEICHT);
        CSVRezept csvRezept = new CSVRezept(rezept);

        // Act
        String[] daten = csvRezept.bekommeCSVDaten();

        // Assert
        Assertions.assertArrayEquals(new String[]{"12345678-1234-1234-1234-123456789012", "Test Rezept", "LEICHT", "Test Beschreibung"}, daten);
    }

    @Test
    void test_bekommeKategorienArray() {
        // Arrange
        UUID rezeptId = UUID.randomUUID();
        Rezept rezept = new Rezept(rezeptId, "Test Rezept", new ArrayList<>(), new ArrayList<>(), "Test Beschreibung", Schwierigkeit.LEICHT);
        CSVRezept csvRezept = new CSVRezept(rezept);

        // Act
        List<String[]> kategorienArray = csvRezept.bekommeKategorienArray();

        // Assert
        Assertions.assertTrue(kategorienArray.isEmpty());
    }
}

