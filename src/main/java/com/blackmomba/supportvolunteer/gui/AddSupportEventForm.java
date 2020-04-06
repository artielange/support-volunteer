package com.blackmomba.supportvolunteer.gui;

import com.blackmomba.supportvolunteer.data.SupportEventRepository;
import com.blackmomba.supportvolunteer.domain.SupportEvent;
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
public class AddSupportEventForm extends JFrame implements ActionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SupportEventRepository supportEventRepository;

    private HashMap<String, Component> componentHashMap;

    public AddSupportEventForm(SupportEventRepository supportEventRepository) {
        this.supportEventRepository = supportEventRepository;
        this.componentHashMap = new HashMap<>();
        this.setTitle("Ajouter un Accompagnement");
        this.setLayout(new SpringLayout());
        String[] labels = {"Heure de debut: ", "Heure de fin: ", "Type d'accompagnement: ", "NAS Bénévole: ", "NAS Client: "};
        String[] fieldNames = {"startTime", "endTime", "supportEventType", "volunteerSin", "clientSin"};
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
                addSupportEvent();
                this.setVisible(false);
                break;
            case "cancel":
                this.setVisible(false);
                break;
        }
    }

    private void addSupportEvent() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        try {
            supportEventRepository.save(new SupportEvent(
                    0L,
                    formatter.parse(getTextFieldValueByName(componentHashMap, "startTime")),
                    formatter.parse(getTextFieldValueByName(componentHashMap, "endTime")),
                    getTextFieldValueByName(componentHashMap, "supportEventType"),
                    getTextFieldValueByName(componentHashMap, "volunteerSin"),
                    getTextFieldValueByName(componentHashMap, "clientSin"),
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
        String title = "Accompagnement existants";
        String content = String.format("%-6s %-20s %-20s %-30s %-12s %-12s %-6s\n",
                "ID", "Heure de debut", "Heure de fin", "Type d'accompagnement", "NAS Bénévole", "NAS Client", "ID Équipe");
        content += supportEventRepository.findAll().stream().map(SupportEvent::toString).collect(Collectors.joining("\n"));
        return GuiTools.getExistingRecordsJPanel(title, content);
    }

}
