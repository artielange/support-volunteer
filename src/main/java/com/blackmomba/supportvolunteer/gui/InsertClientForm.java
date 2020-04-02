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

    public InsertClientForm(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        // Create the frame
        this.setTitle("Ajouter un client");
        this.setLayout(null);

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
        JButton addButton = getButton("Ajouter", "add",
                375, 300, 100, 30);
        JButton cancelButton = getButton("Annuler", "cancel",
                475, 300, 100, 30);

        this.getContentPane().add(sinLabel);
        this.getContentPane().add(sin);
        this.getContentPane().add(firstNameLabel);
        this.getContentPane().add(firstName);
        this.getContentPane().add(lastNameLabel);
        this.getContentPane().add(lastName);
        this.getContentPane().add(dateOfBirthLabel);
        this.getContentPane().add(dateOfBirth);
        this.getContentPane().add(addressLabel);
        this.getContentPane().add(address);

        this.getContentPane().add(addButton);
        this.getContentPane().add(cancelButton);

        this.pack();
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

    private JButton getButton(String caption, String actionCommand, int x, int y, int width, int height) {
        JButton jButton = new JButton(caption);
        jButton.setFont(new Font("Serif", Font.PLAIN, 14));
        jButton.setActionCommand(actionCommand);
        jButton.addActionListener(this);
        jButton.setBounds(x, y, width, height);
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

}
