package com.blackmomba.supportvolunteer.gui;

import com.blackmomba.supportvolunteer.data.ClientRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Service
public class InsertClientForm extends JFrame implements ActionListener {

    private ClientRepository clientRepository;

    private JFrame jFrame;

    public InsertClientForm(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        init();
    }

    private void init() {
        // Create the frame
        jFrame = getGuiFrame();

        // Create the labels
        JLabel sinLabel = getLabel("NAS:", "sinLabel");
        JLabel firstNameLabel = getLabel("Prenom:", "firstNameLabel");
        JLabel lastNameLabel = getLabel("Nom:", "lastNameLabel");
        JLabel dateOfBirthLabel = getLabel("Date de naisssance:", "dateOfBirthLabel");
        JLabel addressLabel = getLabel("Adresse:", "addressLabel");

        // Create the TextBoxes
        JTextField sin = getTextField("sinTextField");
        JTextField firstName = getTextField("firstNameTextField");
        JTextField lastName = getTextField("lastNameTextField");
        JTextField dateOfBirth = getTextField("dateOfBirthTextField");
        JTextField address = getTextField("addressTextField");

        int controlLength = 260;
        int controlHeight = 30;

        int xPosition = 60;
        int yPosition = 50;

        sinLabel.setBounds(xPosition, yPosition, controlLength, controlHeight);
        sin.setBounds(xPosition + controlLength, yPosition, controlLength, controlHeight);

        yPosition += 50;
        firstNameLabel.setBounds(xPosition, yPosition, controlLength, controlHeight);
        firstName.setBounds(xPosition + controlLength, yPosition, controlLength, controlHeight);

        yPosition += 50;
        lastNameLabel.setBounds(xPosition, yPosition, controlLength, controlHeight);
        lastName.setBounds(xPosition + controlLength, yPosition, controlLength, controlHeight);

        yPosition += 50;
        dateOfBirthLabel.setBounds(xPosition, yPosition, controlLength, controlHeight);
        dateOfBirth.setBounds(xPosition + controlLength, yPosition, controlLength, controlHeight);

        yPosition += 50;
        addressLabel.setBounds(xPosition, yPosition, controlLength, controlHeight);
        address.setBounds(xPosition + controlLength, yPosition, controlLength, controlHeight);

        // Create the buttons
        JButton addButton = getButton("Ajouter");
        JButton cancelButton = getButton("Annuler");

        addButton.setActionCommand("add");
        cancelButton.setActionCommand("cancel");

        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        addButton.setBounds(375, 300, 100, 30);
        cancelButton.setBounds(475, 300, 100, 30);

        jFrame.getContentPane().add(sinLabel);
        jFrame.getContentPane().add(sin);
        jFrame.getContentPane().add(firstNameLabel);
        jFrame.getContentPane().add(firstName);
        jFrame.getContentPane().add(lastNameLabel);
        jFrame.getContentPane().add(lastName);
        jFrame.getContentPane().add(dateOfBirthLabel);
        jFrame.getContentPane().add(dateOfBirth);
        jFrame.getContentPane().add(addressLabel);
        jFrame.getContentPane().add(address);

        jFrame.getContentPane().add(addButton);
        jFrame.getContentPane().add(cancelButton);

        jFrame.pack();
    }

    private JFrame getGuiFrame() {
        JFrame jFrame = new JFrame("Gestion des vols pour la compagnie ");
        jFrame.setPreferredSize(new Dimension(678, 382));
        jFrame.setLayout(null);
        return jFrame;
    }

    private JLabel getLabel(String caption, String name) {
        JLabel jLabel = new JLabel(caption);
        jLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        jLabel.setName(name);
        return jLabel;
    }

    private JTextField getTextField(String name) {
        JTextField jTextField = new JTextField();
        jTextField.setFont(new Font("Serif", Font.PLAIN, 24));
        jTextField.setName(name);
        return jTextField;
    }

    private JButton getButton(String caption) {
        JButton jButton = new JButton(caption);
        jButton.setFont(new Font("Serif", Font.PLAIN, 14));
        return jButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                break;
            case "cancel":
                break;
        }
    }

    public void showForm() {
        if (jFrame != null && !jFrame.isShowing()) {
            jFrame.setVisible(true);
        }
    }
}
