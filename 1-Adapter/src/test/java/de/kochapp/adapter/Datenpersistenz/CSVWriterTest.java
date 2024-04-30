package de.kochapp.adapter.Datenpersistenz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVWriterTest {

    @Test
    void test_CSVWriter_Constructor_ValidFileName() throws IOException {
        // Arrange
        String fileName = "valid.csv";
        File file = new File(fileName);
        file.createNewFile();

        // Act
        CSVWriter csvWriter = new CSVWriter(fileName, false);

        // Assert
        Assertions.assertNotNull(csvWriter);

        //CleanUp
        file.delete();
    }

    @Test
    void test_CSVWriter_Constructor_NullFileName() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CSVWriter(null, false));
    }

    @Test
    void test_CSVWriter_Constructor_EmptyFileName() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CSVWriter("", false));
    }

    @Test
    void test_CSVWriter_Constructor_NonExistentFile() {
        // Arrange
        String nonExistentFileName = "nonexistent.csv";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CSVWriter(nonExistentFileName, false));
    }

    @Test
    void test_schreibeDaten_NullData() {
        // Arrange
        CSVWriter csvWriter = new CSVWriter("test.csv", true);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> csvWriter.schreibeDaten(null, null));

        // CleanUp
        try {
            File f = new File("test.csv");
            f.delete();
        }catch (Exception ignored) {}
    }

    @Test
    void test_schreibeDaten_ValidData() throws IOException {
        // Arrange
        CSVWriter csvWriter = new CSVWriter("test.csv", true);
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"1", "John", "Doe"});
        String[] header = {"ID", "First Name", "Last Name"};

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> csvWriter.schreibeDaten(data, header));

        // Clean up
        File file = new File("test.csv");
        Assertions.assertTrue(file.exists());
        Assertions.assertTrue(file.delete());
    }
}
