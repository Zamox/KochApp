package de.kochapp.domain.Kategorie;

import de.kochapp.domain.Interface.IEntityManager;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class KategorieRepositoryTest {

    @Test
    void test_ExistiertKategorie() {
        // Arrange
        Kategorie kategorie = new Kategorie("TestKategorie", "TK", "Test Beschreibung");
        UUID uuid = UUID.randomUUID();
        IEntityManager entityManager = EasyMock.createMock(IEntityManager.class);
        expect(entityManager.existiert(kategorie)).andReturn(true);
        replay(entityManager);
        KategorieRepository repository = new KategorieRepository();

        // Act
        boolean exists = repository.existiertKategorie(kategorie, entityManager);

        // Assert
        assertTrue(exists);

        // Verify
        EasyMock.verify(entityManager);
    }

    @Test
    void test_SpeichereKategorie() throws Exception {
        // Arrange
        Kategorie kategorie = new Kategorie("TestKategorie", "TK", "Test Beschreibung");
        IEntityManager entityManager = EasyMock.createMock(IEntityManager.class);
        entityManager.speichere(kategorie);
        expectLastCall().once();
        replay(entityManager);
        KategorieRepository repository = new KategorieRepository();

        // Act
        repository.speichereKategorie(kategorie, entityManager);

        // Verify
        EasyMock.verify(entityManager);
    }

    @Test
    void test_FindeKategorie() {
        // Arrange
        Kategorie kategorie = new Kategorie("TestKategorie", "TK", "Test Beschreibung");
        UUID uuid = UUID.randomUUID();
        IEntityManager entityManager = EasyMock.createMock(IEntityManager.class);
        expect(entityManager.finde(Kategorie.class, uuid)).andReturn(kategorie);
        replay(entityManager);
        KategorieRepository repository = new KategorieRepository();

        // Act
        Kategorie foundKategorie = repository.findeKategorie(uuid, entityManager);

        // Assert
        assertEquals(kategorie, foundKategorie);

        // Verify
        EasyMock.verify(entityManager);
    }

    @Test
    void test_FindeAlleKategorien() {
        // Arrange
        Kategorie kategorie1 = new Kategorie("TestKategorie1", "TK1", "Test Beschreibung1");
        Kategorie kategorie2 = new Kategorie("TestKategorie2", "TK2", "Test Beschreibung2");
        List<Kategorie> kategorien = new ArrayList<>();
        kategorien.add(kategorie1);
        kategorien.add(kategorie2);
        IEntityManager entityManager = EasyMock.createMock(IEntityManager.class);
        expect(entityManager.findeAlle(Kategorie.class)).andReturn(kategorien);
        replay(entityManager);
        KategorieRepository repository = new KategorieRepository();

        // Act
        List<Kategorie> foundKategorien = repository.findeAlleKategorien(entityManager);

        // Assert
        assertEquals(kategorien.size(), foundKategorien.size());
        assertEquals(kategorie1, foundKategorien.get(0));
        assertEquals(kategorie2, foundKategorien.get(1));

        // Verify
        EasyMock.verify(entityManager);
    }

    @Test
    void test_EntferneKategorie() {
        // Arrange
        Kategorie kategorie = new Kategorie("TestKategorie", "TK", "Test Beschreibung");
        IEntityManager entityManager = EasyMock.createMock(IEntityManager.class);
        entityManager.entferne(kategorie);
        expectLastCall().once();
        replay(entityManager);
        KategorieRepository repository = new KategorieRepository();

        // Act
        repository.entferneKategorie(kategorie, entityManager);

        // Verify
        EasyMock.verify(entityManager);
    }
}

