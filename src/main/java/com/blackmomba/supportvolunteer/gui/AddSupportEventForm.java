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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddSupportEventForm extends JFrame implements ActionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SupportEventRepository supportEventRepository;

    private HashMap<String, Component> componentHashMap;

    public AddSupportEventForm(SupportEventRepository supportEventRepository) {
        this.supportEventRepository = supportEventRepository;
        this.componentHashMap = new HashMap<>();
        this.setTitle("Ajouter un Accompagnement");
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);
        JPanel p = new JPanel(new SpringLayout());
        JLabel existingSupportEventsLabel = new JLabel("Accompagnement existants: ", JLabel.TRAILING);
        List<SupportEvent> supportEventList = supportEventRepository.findAll();
        String content = String.format("%-6s %-20s %-20s %-30s %-12s %-12s %-6s\n",
                "ID", "Heure de debut", "Heure de fin", "Type d'accompagnement", "NAS Benevole", "NAS Client", "ID Equipe");
        content += supportEventList.stream().map(SupportEvent::toString).collect(Collectors.joining("\n"));
        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        jTextArea.setBounds(0, 0, 678, 382);
        jTextArea.setText(content);
        jTextArea.setEditable(false);
        p.add(existingSupportEventsLabel);
        existingSupportEventsLabel.setLabelFor(jTextArea);
        p.add(jTextArea);
        String[] labels = {"Heure de debut: ", "Heure de fin: ", "Type d'accompagnement: ",
                "NAS Benevole: ", "NAS Client: "};
        String[] textFieldNames =
                {"startTime", "endTime", "supportEventType", "volunteerSin", "clientSin"};
        int numPairs = labels.length + 2;
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
        p.add(cancelButton);
        p.add(addButton);
        SpringUtilities.makeCompactGrid(p, numPairs, 2, 6, 6, 6, 6);
        p.setOpaque(true);
        this.setContentPane(p);
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
                    formatter.parse(getTextFieldValueByName("startTime")),
                    formatter.parse(getTextFieldValueByName("endTime")),
                    getTextFieldValueByName("supportEventType"),
                    getTextFieldValueByName("volunteerSin"),
                    getTextFieldValueByName("clientSin"),
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
