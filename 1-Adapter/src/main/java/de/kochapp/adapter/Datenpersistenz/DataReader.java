package de.kochapp.adapter.Datenpersistenz;

import de.kochapp.adapter.Datenpersistenz.Interface.ICSVPersistierbar;
import de.kochapp.domain.Kategorie.*;
import de.kochapp.domain.Rezept.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataReader {

    public final String csvDateienPfad = "./resources/CSVFiles/";
    public EntityManager entityManager = new EntityManager();
    final static KategorieRepository kategorieRepository = new KategorieRepository();
    final static RezeptRepository rezeptRepository = new RezeptRepository();

    //Methode zum laden der Daten aus den CSV Dateien und Erstellung von Einträgen im Entitymanager
    public void ladeCSVDaten() throws IOException {
        CSVReader csvReader = new CSVReader(csvDateienPfad + "Kategorie.csv");
        List<String[]> csvDaten = csvReader.leseDaten();
        csvDaten.forEach(csvZeile -> {
            try {
                erstelleKategorieAusCSV(csvZeile);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        csvDaten.clear();


        csvReader = new CSVReader(csvDateienPfad + "Zutaten.csv");
        csvDaten = csvReader.leseDaten();
        csvDaten.forEach(csvZeile -> {
            try {
                erstelleZutatAusCSV(csvZeile);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        csvDaten.clear();

        csvReader = new CSVReader(csvDateienPfad + "Bild.csv");
        csvDaten = csvReader.leseDaten();
        csvDaten.forEach(csvZeile -> {
            try {
                erstelleBildAusCSV(csvZeile);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        csvDaten.clear();

        csvReader = new CSVReader(csvDateienPfad + "RezeptKategorie.csv");
        List<String[]> csvDatenRezeptKategorie = csvReader.leseDaten();

        csvReader = new CSVReader(csvDateienPfad + "Rezept.csv");
        csvDaten = csvReader.leseDaten();
        csvDaten.forEach(csvZeile -> {
            try {
                erstelleRezeptAusCSV(csvZeile, csvDatenRezeptKategorie);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        csvDaten.clear();
    }

    // Methode zur Datenspeicherung aus dem Entitymanager in CSV Dateien
    public <T extends ICSVPersistierbar> void speichereCSVDaten(String pfad, List<T> objekte) {
        CSVWriter writer = new CSVWriter(pfad, true);  // createIfNotExists

        List<Object[]> csvDaten = new ArrayList<>();

        objekte.forEach(e -> csvDaten.add(e.bekommeCSVDaten()));

        if(objekte.get(0).getClass().equals(CSVRezept.class)){
            CSVWriter writerKategorie = new CSVWriter(csvDateienPfad + "RezeptKategorie.csv", true);
            String[] kategorieKopf = new String[]{"RezeptID", "KategorieID"};
            List<Object[]> kategorieCSVDaten = new ArrayList<>();
            objekte.forEach(e -> {
                List<String[]> kategorieArray = ((CSVRezept) e).bekommeKategorienArray();
                kategorieCSVDaten.addAll(kategorieArray);
            });

            try {
                writerKategorie.schreibeDaten(kategorieCSVDaten, kategorieKopf);
            } catch (IllegalArgumentException | IOException e1) {
                e1.printStackTrace();
            }
        }

        try {
            writer.schreibeDaten(csvDaten, objekte.get(0).bekommeCSVKopf());
        } catch (IllegalArgumentException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public void erstelleKategorieAusCSV(String[] csvZeile) throws Exception {
        UUID kategorieID = UUID.fromString(csvZeile[ CSVKategorie.CSVPosition.KATEGORIE_ID.ordinal() ]);
        String name = csvZeile[ CSVKategorie.CSVPosition.KATEGORIE_TITEL.ordinal() ];
        String tag = csvZeile[ CSVKategorie.CSVPosition.KATEGORIE_KURZFORM.ordinal() ];
        String beschreibung = csvZeile[ CSVKategorie.CSVPosition.KATEGORIE_BESCHREIBUNG.ordinal() ];

        Kategorie kategorie = new Kategorie(kategorieID, name, tag, beschreibung);
        kategorieRepository.speichereKategorie( kategorie, entityManager);
    }

    public void erstelleZutatAusCSV(String[] csvZeile) throws Exception {

        UUID zutatenID = UUID.fromString(csvZeile[ CSVZutat.CSVPosition.ZUATAT_ID.ordinal() ]);
        UUID zutatenRezeptID = UUID.fromString(csvZeile[ CSVZutat.CSVPosition.REZEPT_ID.ordinal() ]);
        String mengeString = csvZeile[ CSVZutat.CSVPosition.ZUTAT_MENGE.ordinal() ];
        String zutatName = csvZeile[ CSVZutat.CSVPosition.ZUTAT_BEZEICHNUG.ordinal() ];

        String[] mengeStringParts = mengeString.split("-");
        long mengeAnzahl = Long.parseLong( mengeStringParts[0]);
        String einheitString = mengeStringParts[1];

        Einheit einheit = null;
        for(Einheit enumEinheit : Einheit.values()){
            if(enumEinheit.bekommeBezeichnung().equals(einheitString)){
                einheit = enumEinheit;
            }
        }
        Menge menge = new Menge(mengeAnzahl, einheit);

        Zutat zutat = new Zutat(zutatenID, zutatenRezeptID, menge, zutatName);
        rezeptRepository.speichereZutat( zutat, entityManager );
    }

    public void erstelleBildAusCSV(String[] csvZeile) throws Exception {
        UUID bildID = UUID.fromString(csvZeile[ CSVBild.CSVPosition.BILD_ID.ordinal() ]);
        UUID rezeptID = UUID.fromString(csvZeile[ CSVBild.CSVPosition.REZEPT_ID.ordinal() ]);
        String pfad = csvZeile[ CSVBild.CSVPosition.BILD_SPEICHERPFAD.ordinal() ];

        Bild bild = new Bild(bildID, rezeptID, pfad);
        rezeptRepository.speichereBild( bild, entityManager );
    }

    public void erstelleRezeptAusCSV(String[] csvZeile, List<String[]> csvDatenRezeptKategorie) throws Exception {

        UUID rezeptID = UUID.fromString(csvZeile[ CSVRezept.CSVPosition.REZEPT_ID.ordinal() ]);
        String titel = csvZeile[ CSVRezept.CSVPosition.REZEPT_TITEL.ordinal() ];
        String schwierigkeitsgrad = csvZeile[ CSVRezept.CSVPosition.REZEPT_SCHWIERIGKEITSGRAD.ordinal() ];
        String beschreibung = csvZeile[ CSVRezept.CSVPosition.REZEPT_BESCHREIBUNG.ordinal() ];
        Bild rezeptBild = null;
        ArrayList<Zutat> zutaten = new ArrayList<>();
        ArrayList<Kategorie> kategorien = new ArrayList<>();
        Schwierigkeit schwierigkeit = null;

        List<Bild> bilderListe = rezeptRepository.findeAlleBilder(entityManager);
        for (Bild bild : bilderListe){
            if (bild.bekommeRezeptID().equals(rezeptID)){
                rezeptBild = bild;
            }
        }

        List<Zutat> zutatenListe = rezeptRepository.findeAlleZutaten(entityManager);
        for (Zutat zutat : zutatenListe){
            if (zutat.bekommeRezeptId().equals(rezeptID)){
                zutaten.add(zutat);
            }
        }

        List<Kategorie> kategorieListe = kategorieRepository.findeAlleKategorien(entityManager);
        csvDatenRezeptKategorie.forEach(csvZeileKategorie -> {
            try {
                if (UUID.fromString(csvZeileKategorie[0]).equals(rezeptID)){
                    for (Kategorie kategorie : kategorieListe){
                        if (kategorie.bekommeUUID().equals(UUID.fromString(csvZeileKategorie[1]))){
                            kategorien.add(kategorie);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        for(Schwierigkeit enumSchwierigkeit : Schwierigkeit.values()){
            if(enumSchwierigkeit.toString().equals(schwierigkeitsgrad)){
                schwierigkeit = enumSchwierigkeit;
            }
        }

        Rezept rezept = new Rezept(rezeptID, titel, kategorien, zutaten, beschreibung, schwierigkeit, rezeptBild);
        rezeptRepository.speichereRezept( rezept, entityManager );
    }
}
