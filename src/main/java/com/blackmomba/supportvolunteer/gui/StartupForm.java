package com.blackmomba.supportvolunteer.gui;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Service
public class StartupForm extends JFrame implements ActionListener {

    public StartupForm() {
        init();
    }

    private void init() {
        // Create the frame
        this.setTitle("Gestion des benevoles pour l'ille de Montreal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Create the buttons
        JButton addCustomer = getButton("Ajouter un client","addCustomer",
                375, 300, 100, 30);
        JButton addFeedback = getButton("Ajouter un commentaire", "addFeedback",
                375, 300, 100, 30);
        JButton addSupportEvent = getButton("Ajouter un accompagnement", "addSupportEvent",
                375, 300, 100, 30);
        JButton addSupportRequest = getButton("Ajouter une demande", "addSupportRequest",
                375, 300, 100, 30);
        JButton addTeam = getButton("Creer un equipe", "addTeam",
                375, 300, 100, 30);
        JButton addVehicle = getButton("Ajouter un vehicule", "addVehicle",
                375, 300, 100, 30);
        JButton addVolunteer = getButton("Ajouter un benevole", "addVolunteer",
                375, 300, 100, 30);
        JButton cancelButton = getButton("Annuler", "cancel",
                375, 300, 100, 30);

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

    private JButton getButton(String caption, String actionCommand, int x, int y, int width, int height) {
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
                break;
            case "cancel":
                break;
        }
    }


}
