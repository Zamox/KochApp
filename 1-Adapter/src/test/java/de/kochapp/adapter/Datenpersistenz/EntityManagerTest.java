package de.kochapp.adapter.Datenpersistenz;

import de.kochapp.domain.Interface.IPersistierbar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import java.util.*;

public class EntityManagerTest {

    @Test
    void test_speichere_existiert_finde() {
        // Arrange
        EntityManager entityManager = new EntityManager();
        MockPersistierbar mockObject = new MockPersistierbar(UUID.randomUUID());

        // Act
        Assertions.assertDoesNotThrow(() -> entityManager.speichere(mockObject));

        // Assert
        Assertions.assertTrue(entityManager.existiert(mockObject));
        Assertions.assertEquals(mockObject, entityManager.finde(MockPersistierbar.class, mockObject.bekommeUUID()));
    }

    @Test
    void test_findeAlle() {
        // Arrange
        EntityManager entityManager = new EntityManager();
        MockPersistierbar mockObject1 = new MockPersistierbar(UUID.randomUUID());
        MockPersistierbar mockObject2 = new MockPersistierbar(UUID.randomUUID());

        // Act
        Assertions.assertDoesNotThrow(() -> {
            entityManager.speichere(mockObject1);
            entityManager.speichere(mockObject2);
        });

        // Assert
        List<MockPersistierbar> foundObjects = entityManager.findeAlle(MockPersistierbar.class);
        Assertions.assertEquals(2, foundObjects.size());
        Assertions.assertTrue(foundObjects.contains(mockObject1));
        Assertions.assertTrue(foundObjects.contains(mockObject2));
    }

    @Test
    void test_entferne() {
        // Arrange
        EntityManager entityManager = new EntityManager();
        MockPersistierbar mockObject = new MockPersistierbar(UUID.randomUUID());

        // Act
        Assertions.assertDoesNotThrow(() -> entityManager.speichere(mockObject));
        entityManager.entferne(mockObject);

        // Assert
        Assertions.assertFalse(entityManager.existiert(mockObject));
        Assertions.assertNull(entityManager.finde(MockPersistierbar.class, mockObject.bekommeUUID()));
    }
}

class MockPersistierbar implements IPersistierbar {
    private final UUID uuid;

    public MockPersistierbar(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID bekommeUUID() {
        return uuid;
    }
}