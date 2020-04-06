package com.blackmomba.supportvolunteer.gui;

import com.blackmomba.supportvolunteer.data.VehicleRepository;
import com.blackmomba.supportvolunteer.domain.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.stream.Collectors;

import static com.blackmomba.supportvolunteer.gui.GuiTools.getButton;
import static com.blackmomba.supportvolunteer.gui.GuiTools.getTextFieldValueByName;

@Service
public class AddVehicleForm extends JFrame implements ActionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private VehicleRepository vehicleRepository;

    private HashMap<String, Component> componentHashMap;

    public AddVehicleForm(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.componentHashMap = new HashMap<>();
        this.setTitle("Ajouter un Véhicule");
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);
        String[] labels = {"No. Immatriculation: ", "Marque: ", "Modèle: ", "Année: ", "NAS Bénévole: "};
        String[] fieldNames = {"registrationId", "make", "model", "year", "volunteerSin"};
        // Make the form
        JPanel form = GuiTools.getForm(labels, fieldNames);
        for (Component component : form.getComponents()) {
            componentHashMap.put(component.getName(), component);
        }
        JButton addButton = getButton("Ajouter", "add", this);
        JButton cancelButton = getButton("Annuler", "cancel", this);
        form.add(cancelButton);
        form.add(addButton);
        int numPairs = labels.length + 1;
        SpringUtilities.makeCompactGrid(form, numPairs, 2, 6, 6, 6, 6);
        this.setContentPane(form);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addSupportRequest();
                this.setVisible(false);
                break;
            case "cancel":
                this.setVisible(false);
                break;
        }
    }

    private void addSupportRequest() {
        try {
            vehicleRepository.save(new Vehicle(
                    getTextFieldValueByName(componentHashMap, "registrationId"),
                    getTextFieldValueByName(componentHashMap, "make"),
                    getTextFieldValueByName(componentHashMap, "model"),
                    getTextFieldValueByName(componentHashMap, "year"),
                    getTextFieldValueByName(componentHashMap, "volunteerSin"),
                    null));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage(), e);
        }
    }

    public JPanel getExistingRecordsJPanel() {
        String title = "Véhicules existants";
        String content = String.format("%-20s %-10s %-10s %-5s %-11s\n",
                "No. Immatriculation", "Marque", "Modèle", "Année", "NAS Bénévole");
        content += vehicleRepository.findAll().stream().map(Vehicle::toString).collect(Collectors.joining("\n"));
        return GuiTools.getExistingRecordsJPanel(title, content);
    }

}
