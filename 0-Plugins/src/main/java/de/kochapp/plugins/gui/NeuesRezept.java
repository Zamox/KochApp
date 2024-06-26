package de.kochapp.plugins.gui;

import de.kochapp.adapter.Datenpersistenz.DataReader;
import de.kochapp.adapter.GUIFunktionen.ButtonEditor;
import de.kochapp.adapter.GUIFunktionen.ButtonRenderer;
import de.kochapp.adapter.GUIFunktionen.FunktionenNeuesRezept;
import de.kochapp.domain.Kategorie.Kategorie;
import de.kochapp.domain.Kategorie.KategorieRepository;
import de.kochapp.domain.Rezept.Schwierigkeit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NeuesRezept {
    final static KategorieRepository kategorieRepository = new KategorieRepository();
    JFrame frame = new JFrame();

    JTable tabelle;
    JLabel labelTitel;
    JLabel labelBeschreibung;
    JRadioButton[] radiobuttons;
    Checkbox[] checkboxen;
    String[] bildPfad = new String[1];

    public NeuesRezept(DataReader dataReader) {
        JPanel pnlWindow = new JPanel(new FlowLayout());
        JPanel pnlNeuesRezept = new JPanel(new BorderLayout());
        JPanel pnlMitteUnten = new JPanel(new GridLayout(1, 2));
        JPanel pnlFooter = new JPanel(new BorderLayout(1,1));


        initialisiereTitleUndTagsPanel(dataReader, pnlNeuesRezept);
        initialisiereZutatenPanel(pnlNeuesRezept);
        initialisiereBeschreibungPanel(pnlNeuesRezept);
        initialisiereSchwierigkeitPanel(pnlMitteUnten, pnlNeuesRezept);
        initialisiereFooterButtons(dataReader, pnlFooter);

        pnlWindow.add(pnlNeuesRezept);
        pnlWindow.add(pnlFooter);

        frame.add(pnlWindow);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBounds(400, 100, 1200, 500);
    }

    private void initialisiereTitleUndTagsPanel(DataReader dataReader, JPanel pnlNeuesRezept) {
        JPanel pnlMitte = new JPanel(new BorderLayout());
        JPanel pnlOben = new JPanel(new GridLayout(2, 2));
        System.out.println("Das Panel für ein neues Rezept wird gestartet");
        labelTitel = new JLabel("Titel: ");
        labelTitel.setPreferredSize(new Dimension(100,100));
        JTextField textfeldTitel = new JTextField();
        pnlOben.add(labelTitel);
        pnlOben.add(textfeldTitel);

        JLabel labelTags = new JLabel("Tags: ");
        labelTags.setPreferredSize(new Dimension(100,100));
        List<Kategorie> kategorien = kategorieRepository.findeAlleKategorien(dataReader.entityManager);
        JPanel pnlCheckboxen = new JPanel(new FlowLayout());
        checkboxen = new Checkbox[kategorien.size()];
        for (int i = 0; i < kategorien.size(); i++) {
            Checkbox checkboxKategorie = new Checkbox(kategorien.get(i).bekommeKurzform());
            checkboxen[i] = checkboxKategorie;
            pnlCheckboxen.add(checkboxKategorie);
        }
        pnlCheckboxen.setPreferredSize(new Dimension(200, 150));
        pnlOben.add(labelTags);
        pnlOben.add(pnlCheckboxen);

        pnlMitte.add(pnlOben, BorderLayout.NORTH);
        pnlNeuesRezept.add(pnlMitte, BorderLayout.CENTER);
    }

    private void initialisiereZutatenPanel(JPanel pnlNeuesRezept) {
        JPanel pnlZutaten = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        tabelle = new JTable(model);
        model.addColumn("Menge");
        model.addColumn("Einheit");
        model.addColumn("Zutat");
        model.addColumn("Löschen");
        model.addRow(new Object[]{"", "l", "", "-"});

        TableColumn einheitSpalte = tabelle.getColumnModel().getColumn(1);
        einheitSpalte.setCellEditor(new DefaultCellEditor(new JComboBox<>(new String[]{"l", "ml", "g"})));
        tabelle.getColumn("Löschen").setCellRenderer(new ButtonRenderer());
        tabelle.getColumn("Löschen").setCellEditor(new ButtonEditor(new JCheckBox()));
        tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabelle.setPreferredScrollableViewportSize(new Dimension(300, 150));


        pnlZutaten.add(new JScrollPane(tabelle), BorderLayout.CENTER);
        JButton buttonNeueZutat = new JButton("Zutat hinzufügen");
        buttonNeueZutat.addActionListener(ae -> model.addRow(new Object[]{"", "l", "", "-"}));
        pnlZutaten.add(buttonNeueZutat, BorderLayout.SOUTH);
        pnlNeuesRezept.add(pnlZutaten, BorderLayout.EAST);
    }

    private void initialisiereBeschreibungPanel(JPanel pnlNeuesRezept) {
        JPanel pnlWesten = new JPanel(new BorderLayout());
        labelBeschreibung = new JLabel("Beschreibung: ");
        JTextArea textAreaBeschreibung = new JTextArea("Hier sollte die Beschreibung abc stehen");
        textAreaBeschreibung.setPreferredSize(new Dimension(150, 350));
        textAreaBeschreibung.setLineWrap(true);
        textAreaBeschreibung.setWrapStyleWord(true);
        pnlWesten.add(labelBeschreibung, BorderLayout.NORTH);
        pnlWesten.add(new JScrollPane(textAreaBeschreibung), BorderLayout.CENTER);
        pnlNeuesRezept.add(pnlWesten, BorderLayout.WEST);
    }

    private void initialisiereSchwierigkeitPanel(JPanel pnlUnten, JPanel pnlNeuesRezept) {
        JPanel pnlRadioButton = new JPanel(new FlowLayout());
        JLabel labelSchwierigkeitsgrad = new JLabel("Schwierigkeitsgrad: ");
        Schwierigkeit[] alleSchwierigkeitsgrade = Schwierigkeit.values();
        radiobuttons = new JRadioButton[alleSchwierigkeitsgrade.length];
        ButtonGroup btnSchwierigkeitgruppe = new ButtonGroup();
        for (int i=0; i<alleSchwierigkeitsgrade.length; i++) {
            JRadioButton radioButtonSchwierigkeiten = new JRadioButton(alleSchwierigkeitsgrade[i].toString());
            radiobuttons[i]= radioButtonSchwierigkeiten;
            btnSchwierigkeitgruppe.add(radiobuttons[i]);
            pnlRadioButton.add(radiobuttons[i]);
        }
        pnlUnten.add(labelSchwierigkeitsgrad);
        pnlUnten.add(pnlRadioButton);

        JLabel labelDokument = new JLabel("Füge ein Dokument oder Bild hinzu ");
        JButton buttonDokument = new JButton("Wähle ein Dokument");
        buttonDokument.addActionListener(ae -> {
            FileChooser chooser = new FileChooser();
            bildPfad[0] = chooser.dateiAuswahl();
        });
        pnlUnten.add(labelDokument);
        pnlUnten.add(buttonDokument);
        pnlNeuesRezept.add(pnlUnten, BorderLayout.SOUTH);
    }

    private void initialisiereFooterButtons(DataReader dataReader, JPanel pnlFooter) {
        JButton buttonAbbrechen = new JButton("Abbrechen");
        buttonAbbrechen.addActionListener(ae -> frame.dispose());

        JButton buttonSpeichern = new JButton("Speichern");
        buttonSpeichern.addActionListener(ae -> {
            boolean tabelleBearbeitet = false;
            if (tabelle.getCellEditor() != null) {
                tabelle.getCellEditor().stopCellEditing();
                tabelleBearbeitet = true;
            }

            String titel = labelTitel.getText();
            String beschreibung = labelBeschreibung.getText();
            String pfadBild = bildPfad[0];

            ArrayList<String> checkedKategorien = new ArrayList<>();
            for (Checkbox checkbox : checkboxen) {
                if (checkbox.getState()) {
                    checkedKategorien.add(checkbox.getLabel());
                }
            }

            String ausgewaehlteSchwierigkeit = "";
            for (JRadioButton radiobutton : radiobuttons) {
                if (radiobutton.isSelected()) {
                    ausgewaehlteSchwierigkeit = radiobutton.getText();
                }
            }

            if (!titel.isEmpty() && !beschreibung.isEmpty() && tabelleBearbeitet && !checkedKategorien.isEmpty() && !ausgewaehlteSchwierigkeit.isEmpty()) {
                ArrayList<String[]> zutatenListe = new ArrayList<>();
                for (int i = 0; i < tabelle.getRowCount(); i++) {
                    String mengeText = (String) tabelle.getModel().getValueAt(i, 0);
                    String einheitText = (String) tabelle.getModel().getValueAt(i, 1);
                    String name = (String) tabelle.getModel().getValueAt(i, 2);
                    zutatenListe.add(new String[]{mengeText, einheitText, name});
                }

                FunktionenNeuesRezept.neuesRezeptSpeichern(titel, beschreibung, checkedKategorien,
                        ausgewaehlteSchwierigkeit, pfadBild, zutatenListe, dataReader);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Bitte füllen Sie alle Felder aus!");
            }
        });

        // Fügen Sie die Buttons zum Footer Panel hinzu
        JPanel pnlFusszeile = new JPanel(new FlowLayout());
        pnlFusszeile.add(buttonAbbrechen);
        pnlFusszeile.add(buttonSpeichern);
        pnlFooter.add(pnlFusszeile, BorderLayout.SOUTH);
    }

}
