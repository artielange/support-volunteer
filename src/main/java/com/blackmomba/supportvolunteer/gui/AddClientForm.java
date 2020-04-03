package com.blackmomba.supportvolunteer.gui;

import com.blackmomba.supportvolunteer.data.ClientRepository;
import com.blackmomba.supportvolunteer.data.SectorRepository;
import com.blackmomba.supportvolunteer.domain.Client;
import com.blackmomba.supportvolunteer.domain.Sector;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

@Service
public class AddClientForm extends JFrame implements ActionListener {

    private ClientRepository clientRepository;

    private HashMap<String, Component> componentMap;

    public AddClientForm(
            ClientRepository clientRepository,
            SectorRepository sectorRepository) {
        this.clientRepository = clientRepository;
        this.componentMap = new HashMap<>();

        this.setTitle("Ajouter un client");
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
            componentMap.put(textField.getName(), textField);
        }
        JComboBox<ComboItem> jComboBox = new JComboBox<>();
        for (Sector sector : sectorRepository.findAll()) {
            jComboBox.addItem(new ComboItem(sector.getId(), sector.getName()));
        }
        JLabel l = new JLabel("Secteur: ", JLabel.TRAILING);
        p.add(l);
        l.setLabelFor(jComboBox);
        p.add(jComboBox);
        componentMap.put("sector", jComboBox);
        JButton addButton = getButton("Ajouter", "addClient");
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
            case "addClient":
                addClient();
                break;
            case "cancel":
                this.setVisible(false);
                break;
        }
    }

    private void addClient() {
        Component component = componentMap.getOrDefault("sector", null);
        ComboItem comboItem = (ComboItem) ((JComboBox<?>) component).getSelectedItem();
        if (comboItem != null) {
            clientRepository.save(new Client(
                    getTextFieldValueByName("sin"),
                    getTextFieldValueByName("lastName"),
                    getTextFieldValueByName("firstName"),
                    getTextFieldValueByName("dob"),
                    getTextFieldValueByName("address"),
                    comboItem.getKey()));
        }
    }

    private JButton getButton(String caption, String actionCommand) {
        JButton jButton = new JButton(caption);
        jButton.setActionCommand(actionCommand);
        jButton.addActionListener(this);
        return jButton;
    }

    public String getTextFieldValueByName(String name) {
        JTextField textField = (JTextField) componentMap.getOrDefault(name, null);
        return textField != null ? textField.getText().trim() : "";
    }

}
