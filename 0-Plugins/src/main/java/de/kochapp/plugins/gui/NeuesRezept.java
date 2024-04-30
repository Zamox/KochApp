package de.kochapp.plugins.gui;

import de.kochapp.adapter.Datenpersistenz.DataReader;
import de.kochapp.adapter.GUIFunktionen.ButtonEditor;
import de.kochapp.adapter.GUIFunktionen.ButtonRenderer;
import de.kochapp.domain.Kategorie.Kategorie;
import de.kochapp.domain.Kategorie.KategorieRepository;
import de.kochapp.domain.Rezept.Schwierigkeit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class NeuesRezept {
    final static KategorieRepository kategorieRepository = new KategorieRepository();
    JFrame frame = new JFrame();
    JPanel pnlNeuesRezept = new JPanel(new BorderLayout());
    JButton button = new JButton();

    public NeuesRezept(DataReader dataReader) {
        initialisiereTitleUndTagsPanel(dataReader);
        initialisiereZutatenPanel();
        initialisiereBeschreibungPanel();

        JPanel pnlUnten = new JPanel(new GridLayout(2,2));
        initialisiereSchwierigkeitPanel(pnlUnten);

        frame.add(pnlNeuesRezept);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBounds(400,100,1000,500);
    }

    private void initialisiereTitleUndTagsPanel(DataReader dataReader) {
        JPanel pnlMitte = new JPanel(new BorderLayout());
        JPanel pnlOben = new JPanel(new GridLayout(2,2));
        System.out.println("Das Panel für ein neues Rezept wird gestartet");
        JLabel labelTitel = new JLabel("Titel: ");
        JTextField textfeldTitel = new JTextField();
        pnlOben.add(labelTitel);
        pnlOben.add(textfeldTitel);

        JLabel labelTags = new JLabel("Tags: ");
        List<Kategorie> kategorien = kategorieRepository.findeAlleKategorien(dataReader.entityManager);
        JPanel pnlCheckboxen = new JPanel(new FlowLayout());
        Checkbox[] checkboxen = new Checkbox[kategorien.size()];
        for (int i = 0; i < kategorien.size(); i++) {
            Checkbox checkboxKategorie = new Checkbox(kategorien.get(i).bekommeKurzform());
            checkboxen[i] = checkboxKategorie;
            pnlCheckboxen.add(checkboxKategorie);
        }
        pnlCheckboxen.setPreferredSize(new Dimension(400,150));
        pnlOben.add(labelTags);
        pnlOben.add(pnlCheckboxen);

        pnlMitte.add(pnlOben, BorderLayout.NORTH);
        pnlNeuesRezept.add(pnlMitte, BorderLayout.CENTER);
    }

    private void initialisiereZutatenPanel() {
        JPanel pnlZutaten = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        JTable tabelle = new JTable(model);
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

    private void initialisiereBeschreibungPanel() {
        JPanel pnlWesten = new JPanel(new BorderLayout());
        JLabel labelBeschreibung = new JLabel("Beschreibung: ");
        JTextArea textAreaBeschreibung = new JTextArea("Hier sollte die Beschreibung abc stehen");
        textAreaBeschreibung.setPreferredSize(new Dimension(150, 350));
        textAreaBeschreibung.setLineWrap(true);
        textAreaBeschreibung.setWrapStyleWord(true);
        pnlWesten.add(labelBeschreibung, BorderLayout.NORTH);
        pnlWesten.add(new JScrollPane(textAreaBeschreibung), BorderLayout.CENTER);
        pnlNeuesRezept.add(pnlWesten, BorderLayout.WEST);
    }

    private void initialisiereSchwierigkeitPanel(JPanel pnlUnten) {
        JPanel pnlRadioButton = new JPanel(new FlowLayout());
        JLabel labelSchwierigkeitsgrad = new JLabel("Schwierigkeitsgrad: ");
        Schwierigkeit[] alleSchwierigkeitsgrade = Schwierigkeit.values();
        ButtonGroup gruppe = new ButtonGroup();
        for (Schwierigkeit schwierigkeit : alleSchwierigkeitsgrade) {
            JRadioButton radioButton = new JRadioButton(schwierigkeit.toString());
            gruppe.add(radioButton);
            pnlRadioButton.add(radioButton);
        }
        pnlUnten.add(labelSchwierigkeitsgrad);
        pnlUnten.add(pnlRadioButton);

        JLabel labelDokument = new JLabel("Füge ein Dokument oder Bild hinzu ");
        JButton buttonDokument = new JButton("Wähle ein Dokument");
        buttonDokument.addActionListener(ae -> {
            FileChooser chooser = new FileChooser();
            chooser.dateiAuswahl();
        });
        pnlUnten.add(labelDokument);
        pnlUnten.add(buttonDokument);
        pnlNeuesRezept.add(pnlUnten, BorderLayout.SOUTH);
    }
}
