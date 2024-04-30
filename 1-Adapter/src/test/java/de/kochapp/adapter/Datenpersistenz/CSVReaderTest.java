package de.kochapp.adapter.Datenpersistenz;

import de.kochapp.adapter.Datenpersistenz.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CSVReaderTest {

    @Test
    void test_CSVReader_Constructor_ValidFileName() throws IOException {
        // Arrange
        String fileName = "valid.csv";
        File file = new File(fileName);
        file.createNewFile();
        // Act
        CSVReader csvReader = new CSVReader(fileName);

        // Assert
        Assertions.assertNotNull(csvReader);

        file.delete();
    }

    @Test
    void test_CSVReader_Constructor_NullFileName() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CSVReader(null));
    }

    @Test
    void test_CSVReader_Constructor_EmptyFileName() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CSVReader(""));
    }

    @Test
    void test_CSVReader_Constructor_NonExistentFile() {
        // Arrange
        String nonExistentFileName = "nonexistent.csv";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CSVReader(nonExistentFileName));
    }


    @Test
    void test_leseDaten() {
        // Arrange
        String fileName = "test.csv";
        String content = "Name;Alter\nJohn;30\nJane;25";
        File file = new File(fileName);

        try {
            file.createNewFile();
            java.io.FileWriter writer = new java.io.FileWriter(fileName);
            writer.write(content);
            writer.close();

            CSVReader csvReader = new CSVReader(fileName);

            // Act
            List<String[]> data = csvReader.leseDaten();

            // Assert
            Assertions.assertNotNull(data);
            Assertions.assertEquals(3, data.size());
            Assertions.assertArrayEquals(new String[]{"Name", "Alter"}, data.get(0));
            Assertions.assertArrayEquals(new String[]{"John", "30"}, data.get(1));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }
}
