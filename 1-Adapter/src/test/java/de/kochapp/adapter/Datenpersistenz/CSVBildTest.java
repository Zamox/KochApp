package de.kochapp.adapter.Datenpersistenz;


import de.kochapp.domain.Rezept.Bild;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;



public class CSVBildTest {

    @Test
    void test_bekommeUUID() {
        // Arrange
        UUID bildId = UUID.randomUUID();
        UUID rezeptId = UUID.randomUUID();
        String speicherpfad = "/pfad/zum/bild.png";
        Bild bild = new Bild(bildId, rezeptId, speicherpfad);

        // Act
        CSVBild csvBild = new CSVBild(bild);

        // Assert
        Assertions.assertEquals(bildId, csvBild.bekommeUUID());
    }

    @Test
    void test_bekommeCSVKopf() {
        // Arrange
        Bild bild = new Bild(UUID.randomUUID(), UUID.randomUUID(), "/pfad/zum/bild.png");
        CSVBild csvBild = new CSVBild(bild);

        // Act
        String[] kopf = csvBild.bekommeCSVKopf();

        // Assert
        Assertions.assertArrayEquals(new String[]{"BildID", "RezeptID", "Speicherpfad"}, kopf);
    }

    @Test
    void test_bekommeCSVDaten() {
        // Arrange
        Bild bild = new Bild(UUID.fromString("12345678-1234-1234-1234-123456789012"),UUID.fromString("12345678-4321-1234-1234-123456789012"), "/pfad/zum/bild.png");
        CSVBild csvBild = new CSVBild(bild);

        // Act
        String[] daten = csvBild.bekommeCSVDaten();

        // Assert
        Assertions.assertArrayEquals(new String[]{"12345678-1234-1234-1234-123456789012", "12345678-4321-1234-1234-123456789012", "/pfad/zum/bild.png"}, daten);
    }
}
