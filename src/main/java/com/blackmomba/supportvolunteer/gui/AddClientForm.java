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
import java.util.List;
import java.util.stream.Collectors;

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
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);
        JPanel p = new JPanel(new SpringLayout());

        JLabel existingClientsLabel = new JLabel("Clients existants: ", JLabel.TRAILING);
        List<Client> clientList = clientRepository.findAll();

        String content = String.format("%-12s %-40s %-20s %-40s %-1s\n",
                "NAS", "Nom", "Date de naissance", "Address", "Secteur");
        content += clientList.stream().map(Client::toString).collect(Collectors.joining("\n"));
        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        jTextArea.setBounds(0, 0, 678, 382);
        jTextArea.setText(content);
        p.add(existingClientsLabel);
        existingClientsLabel.setLabelFor(jTextArea);
        p.add(jTextArea);

        String[] labels = {"NAS: ", "Nom: ", "Prenom: ", "Date de naissance: ", "Address: "};
        String[] textFieldNames = {"sin", "lastName", "firstName", "dob", "address"};
        int numPairs = labels.length + 3;
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
                        getTextFieldValueByName("sin"),
                        getTextFieldValueByName("lastName"),
                        getTextFieldValueByName("firstName"),
                        formatter.parse(getTextFieldValueByName("dob")),
                        getTextFieldValueByName("address"),
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
