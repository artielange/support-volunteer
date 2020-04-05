package com.blackmomba.supportvolunteer.gui;

import com.blackmomba.supportvolunteer.data.SupportRequestRepository;
import com.blackmomba.supportvolunteer.domain.SupportRequest;
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
public class AddSupportRequestForm extends JFrame implements ActionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SupportRequestRepository supportRequestRepository;

    private HashMap<String, Component> componentHashMap;

    public AddSupportRequestForm(SupportRequestRepository supportRequestRepository) {
        this.supportRequestRepository = supportRequestRepository;
        this.componentHashMap = new HashMap<>();
        this.setTitle("Ajouter une Demande d'accompagnement");
        this.setLayout(new SpringLayout());
        String[] labels = {"Date et Heure: ", "Type d'accompagnement: ", "NAS Client: "};
        String[] fieldNames = {"dateTime", "supportType", "clientNas"};
        // Make the form
        JPanel form = GuiTools.getForm(labels, fieldNames);
        for (Component component : form.getComponents()) {
            componentHashMap.put(component.getName(), component);
        }
        JButton addButton = getButton("Ajouter", "add", this);
        JButton cancelButton = getButton("Annuler", "cancel", this);
        form.add(addButton);
        form.add(cancelButton);
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        try {
            supportRequestRepository.save(new SupportRequest(
                    0L,
                    formatter.parse(getTextFieldValueByName(componentHashMap, "dateTime")),
                    getTextFieldValueByName(componentHashMap, "supportType"),
                    getTextFieldValueByName(componentHashMap, "clientNas")));
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
        String title = "Demandes existantes";
        String content = String.format("%-6s %-20s %-20s %-11s\n",
                "ID", "Date et Heure", "Type d'Accompagnement", "NAS Client");
        content += supportRequestRepository.findAll().stream().map(SupportRequest::toString).collect(Collectors.joining("\n"));
        return GuiTools.getExistingRecordsJPanel(title, content);
    }

}
