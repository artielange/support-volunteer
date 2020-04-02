package com.blackmomba.supportvolunteer.gui;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Service
public class StartupForm extends JFrame implements ActionListener {

    private InsertClientForm insertClientForm;

    public StartupForm(
            InsertClientForm insertClientForm) {
        this.insertClientForm = insertClientForm;
        // Create the frame
        this.setTitle("Gestion des benevoles pour l'ille de Montreal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Create the buttons
        JButton addCustomer = getButton("Ajouter un client","addCustomer");
        JButton addFeedback = getButton("Ajouter un commentaire", "addFeedback");
        JButton addSupportEvent = getButton("Ajouter un accompagnement", "addSupportEvent");
        JButton addSupportRequest = getButton("Ajouter une demande", "addSupportRequest");
        JButton addTeam = getButton("Creer un equipe", "addTeam");
        JButton addVehicle = getButton("Ajouter un vehicule", "addVehicle");
        JButton addVolunteer = getButton("Ajouter un benevole", "addVolunteer");
        JButton cancelButton = getButton("Annuler", "cancel");

        this.getContentPane().add(addCustomer);
        this.getContentPane().add(addFeedback);
        this.getContentPane().add(addSupportEvent);
        this.getContentPane().add(addSupportRequest);
        this.getContentPane().add(addTeam);
        this.getContentPane().add(addVehicle);
        this.getContentPane().add(addVolunteer);
        this.getContentPane().add(cancelButton);

        this.pack();
        this.setVisible(true);
    }

    private JButton getButton(String caption, String actionCommand) {
        JButton jButton = new JButton(caption);
        jButton.setFont(new Font("Serif", Font.PLAIN, 14));
        jButton.setActionCommand(actionCommand);
        jButton.addActionListener(this);
        jButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        return jButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "addCustomer":
                insertClientForm.setVisible(true);
                break;
            case "addFeedback":
                break;
            case "addSupportEvent":
                break;
            case "addSupportRequest":
                break;
            case "addTeam":
                break;
            case "addVehicle":
                break;
            case "addVolunteer":
                break;
            case "cancel":
                System.exit(0);
                break;
        }
    }


}
