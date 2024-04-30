package de.kochapp.adapter.GUIFunktionen;

import de.kochapp.adapter.Datenpersistenz.DataReader;
import de.kochapp.domain.Rezept.Rezept;
import de.kochapp.domain.Rezept.RezeptRepository;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class FunktionenZufallsGenerator {
    final static RezeptRepository rezeptRepository = new RezeptRepository();

    //Erzeugt aus den vorhandenen UUIDs eine zufällige UUID
    public static UUID zufälligeRezeptUUID(DataReader dataReader){
        List<Rezept> alleRezepte = rezeptRepository.findeAlleRezepte(dataReader.entityManager);
        int zufallszahl = new Random().nextInt(alleRezepte.size());
        return alleRezepte.get(zufallszahl).bekommeUUID();
    }
}
