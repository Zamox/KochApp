package de.kochapp.adapter.Datenpersistenz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DataReaderTest {

    @Test
    void test_ladeCSVDaten() {
        // Arrange
        DataReader dataReader = new DataReader();

        // Act & Assert
        Assertions.assertDoesNotThrow(dataReader::ladeCSVDaten);
    }

    @Test
    void test_erstelleBildAusCSV() {
        // Arrange
        DataReader dataReader = new DataReader();
        String[] mock_csvZeile = {"12345678-0303-4bd6-a812-d706a27c5663", "87654321-1edb-0adf-abcd-d706a27c5663", "TestRezeptID", "Pfad"};

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> dataReader.erstelleBildAusCSV(mock_csvZeile));
    }

    @Test
    void test_erstelleKategorieAusCSV() {
        // Arrange
        DataReader dataReader = new DataReader();
        String[] mock_csvZeile = {"123e4567-e89b-12d3-a456-556642440000", "TestName", "TestTag", "TestBeschreibung"};

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> dataReader.erstelleKategorieAusCSV(mock_csvZeile));
    }

    @Test
    void test_erstelleRezeptAusCSV() {
        // Arrange
        DataReader dataReader = new DataReader();
        String[] mock_csvZeile = {"123e4567-e89b-12d3-a456-556642440000", "TestTitel", "LEICHT", "TestBeschreibung"};

        String[] s1 = {"12345678-2f27-497f-9d88-d2626a02cca7", "87654321-8d28-41f3-80dc-b7646718984a"};
        String[] s2 = {"12345678-1234-497f-9d88-d2626a02cca7", "87654321-4321-41f3-80dc-b7646718984a"};
        List<String[]> mock_csvDatenRezeptKategorie = new ArrayList<>();

        mock_csvDatenRezeptKategorie.add(s1);
        mock_csvDatenRezeptKategorie.add(s2);

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> dataReader.erstelleRezeptAusCSV(mock_csvZeile, mock_csvDatenRezeptKategorie));
    }

    @Test
    void test_erstelleZutatAusCSV() {
        // Arrange
        DataReader dataReader = new DataReader();
        String[] csvZeile = {"12345678-5568-467c-b86b-7b264af88a1c", "87654321-7777-4e60-8437-3956a905bc2e", "1-Stück", "Testzutat"};

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> dataReader.erstelleZutatAusCSV(csvZeile));
    }

}

