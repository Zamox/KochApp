package de.kochapp.adapter.Datenpersistenz.Interface;

/* ICSVPersistierbar Interface: Enthält Methoden zur Speicherung der Objekte */
public interface ICSVPersistierbar {
    Object bekommeUUID();
    String[] bekommeCSVKopf();
    String[] bekommeCSVDaten();
}
