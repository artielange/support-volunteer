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

@Service
public class AddSupportRequestForm extends JFrame implements ActionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SupportRequestRepository supportRequestRepository;

    private HashMap<String, Component> componentHashMap;

    public AddSupportRequestForm(SupportRequestRepository supportRequestRepository) {
        this.supportRequestRepository = supportRequestRepository;
        this.componentHashMap = new HashMap<>();
        this.setTitle("Ajouter une Demande d'accompagnement");
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);
        String[] labels = {"Date et Heure: ", "Type d'accompagnement: ", "NAS Client: "};
        String[] textFieldNames = {"dateTime", "supportType", "clientNas"};
        int numPairs = labels.length + 1;
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
                    formatter.parse(getTextFieldValueByName("dateTime")),
                    getTextFieldValueByName("supportType"),
                    getTextFieldValueByName("clientNas")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage(), e);
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
