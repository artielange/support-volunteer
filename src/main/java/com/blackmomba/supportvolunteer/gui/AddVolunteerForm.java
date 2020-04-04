package com.blackmomba.supportvolunteer.gui;

import com.blackmomba.supportvolunteer.data.SectorRepository;
import com.blackmomba.supportvolunteer.data.VolunteerRepository;
import com.blackmomba.supportvolunteer.domain.Sector;
import com.blackmomba.supportvolunteer.domain.Volunteer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

@Service
public class AddVolunteerForm extends JFrame implements ActionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private VolunteerRepository volunteerRepository;

    private HashMap<String, Component> componentHashMap;

    public AddVolunteerForm(
            VolunteerRepository volunteerRepository,
            SectorRepository sectorRepository) {
        this.volunteerRepository = volunteerRepository;
        this.componentHashMap = new HashMap<>();
        this.setTitle("Ajouter un Benevole");
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);
        String[] labels = {"NAS: ", "Nom: ", "Prenom: ", "Date de naissance: ", "Address: "};
        String[] textFieldNames = {"sin", "lastName", "firstName", "dob", "address"};
        int numPairs = labels.length + 2;
        JPanel p = new JPanel(new SpringLayout());
        for (int x = 0; x < labels.length; x++) {
            JLabel l = new JLabel(labels[x], JLabel.TRAILING);
            p.add(l);
            JTextField textField = new JTextField(10);
            textField.setName(textFieldNames[x]);
            l.setLabelFor(textField);
            p.add(textField);
            componentHashMap.put(textField.getName(), textField);
        }
        JComboBox<ComboItem> jComboBox = new JComboBox<>();
        for (Sector sector : sectorRepository.findAll()) {
            jComboBox.addItem(new ComboItem(sector.getId(), sector.getName()));
        }
        JLabel l = new JLabel("Secteur: ", JLabel.TRAILING);
        p.add(l);
        l.setLabelFor(jComboBox);
        p.add(jComboBox);
        componentHashMap.put("sector", jComboBox);
        JButton addButton = getButton("Ajouter", "add");
        JButton cancelButton = getButton("Annuler", "cancel");
        p.add(addButton);
        p.add(cancelButton);
        SpringUtilities.makeCompactGrid(p, numPairs, 2, 6, 6, 6, 6);
        p.setOpaque(true);
        this.setContentPane(p);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addVolunteer();
                this.setVisible(false);
                break;
            case "cancel":
                this.setVisible(false);
                break;
        }
    }

    private void addVolunteer() {
        Component component = componentHashMap.getOrDefault("sector", null);
        ComboItem comboItem = (ComboItem) ((JComboBox<?>) component).getSelectedItem();
        if (comboItem != null) {
            try {
                volunteerRepository.save(new Volunteer(
                        getTextFieldValueByName("sin"),
                        getTextFieldValueByName("lastName"),
                        getTextFieldValueByName("firstName"),
                        getTextFieldValueByName("address"),
                        true,
                        (Long) comboItem.getKey()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage(), e);
            }
        }
    }

    private JButton getButton(String caption, String actionCommand) {
        JButton jButton = new JButton(caption);
        jButton.setActionCommand(actionCommand);
        jButton.addActionListener(this);
        return jButton;
    }

    public String getTextFieldValueByName(String name) {
        JTextField textField = (JTextField) componentHashMap.getOrDefault(name, null);
        return textField != null ? textField.getText().trim() : "";
    }

}
