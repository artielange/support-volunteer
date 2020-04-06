package com.blackmomba.supportvolunteer.gui;

import com.blackmomba.supportvolunteer.data.ClientRepository;
import com.blackmomba.supportvolunteer.data.SectorRepository;
import com.blackmomba.supportvolunteer.domain.Client;
import com.blackmomba.supportvolunteer.domain.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.stream.Collectors;

import static com.blackmomba.supportvolunteer.gui.GuiTools.getButton;
import static com.blackmomba.supportvolunteer.gui.GuiTools.getTextFieldValueByName;

@Service
public class AddClientForm extends JFrame implements ActionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ClientRepository clientRepository;

    private HashMap<String, Component> componentHashMap;

    public AddClientForm(
            ClientRepository clientRepository,
            SectorRepository sectorRepository) {
        this.clientRepository = clientRepository;
        this.componentHashMap = new HashMap<>();
        this.setTitle("Ajouter un client");
        this.setLayout(new SpringLayout());
        String[] labels = {"NAS: ", "Nom: ", "Prénom: ", "Date de naissance: ", "Address: "};
        String[] fieldNames = {"sin", "lastName", "firstName", "dob", "address"};
        // Make the form
        JPanel form = GuiTools.getForm(labels, fieldNames);
        for (Component component : form.getComponents()) {
            componentHashMap.put(component.getName(), component);
        }
        // Add the ComboBox
        JComboBox<ComboItem> jComboBox = new JComboBox<>();
        for (Sector sector : sectorRepository.findAll()) {
            jComboBox.addItem(new ComboItem(sector.getId(), sector.getName()));
        }
        JLabel jLabel = new JLabel("Secteur: ", JLabel.TRAILING);
        form.add(jLabel);
        jLabel.setLabelFor(jComboBox);
        form.add(jComboBox);
        componentHashMap.put("sector", jComboBox);
        JButton addButton = getButton("Ajouter", "add", this);
        JButton cancelButton = getButton("Annuler", "cancel", this);
        form.add(cancelButton);
        form.add(addButton);
        int numPairs = labels.length + 2;
        SpringUtilities.makeCompactGrid(form, numPairs, 2, 6, 6, 6, 6);
        this.setContentPane(form);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addClient();
                this.setVisible(false);
                break;
            case "cancel":
                this.setVisible(false);
                break;
        }
    }

    private void addClient() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Component component = componentHashMap.getOrDefault("sector", null);
        ComboItem comboItem = (ComboItem) ((JComboBox<?>) component).getSelectedItem();
        if (comboItem != null) {
            try {
                clientRepository.save(new Client(
                        getTextFieldValueByName(componentHashMap, "sin"),
                        getTextFieldValueByName(componentHashMap, "lastName"),
                        getTextFieldValueByName(componentHashMap, "firstName"),
                        formatter.parse(getTextFieldValueByName(componentHashMap, "dob")),
                        getTextFieldValueByName(componentHashMap, "address"),
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

    public JPanel getExistingRecordsJPanel() {
        String title = "Clients existants";
        String content = String.format("%-12s %-40s %-20s %-40s %-1s\n",
                "NAS", "Nom", "Date de naissance", "Adresse", "Secteur");
        content += clientRepository.findAll().stream().map(Client::toString).collect(Collectors.joining("\n"));
        return GuiTools.getExistingRecordsJPanel(title, content);
    }

}
