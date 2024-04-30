package de.kochapp.domain.Rezept;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MengeTest {

    @Test
    void testConstructor_validInput() {
        // Arrange
        long expectedMenge = 500;
        Einheit expectedEinheit = Einheit.Gramm;

        // Act
        Menge menge = new Menge(expectedMenge, expectedEinheit);

        // Assert
        assertEquals(expectedMenge, menge.dieMenge());
        assertEquals(expectedEinheit, menge.dieEinheit());
    }

    @Test
    void testConstructor_negativeMenge() {
        // Arrange
        long negativeMenge = -100;
        Einheit einheit = Einheit.Stück;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Menge(negativeMenge, einheit));
        assertEquals("Gewicht kann nicht negativ sein: " + negativeMenge, exception.getMessage());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Menge menge1 = new Menge(500, Einheit.Gramm);
        Menge menge2 = new Menge(500, Einheit.Gramm);
        Menge menge3 = new Menge(600, Einheit.Gramm);
        Menge menge4 = new Menge(500, Einheit.Stück);

        // Act & Assert
        assertTrue(menge1.equals(menge2) && menge2.equals(menge1));
        assertFalse(menge1.equals(menge3) || menge3.equals(menge1));
        assertFalse(menge1.equals(menge4) || menge4.equals(menge1));

        assertEquals(menge1.hashCode(), menge2.hashCode());
        assertNotEquals(menge1.hashCode(), menge3.hashCode());
        assertNotEquals(menge1.hashCode(), menge4.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        Menge menge = new Menge(500, Einheit.Gramm);
        String expectedString = "500-g";

        // Act
        String actualString = menge.toString();

        // Assert
        assertEquals(expectedString, actualString);
    }
}

