package com.blackmomba.supportvolunteer.gui;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Service
public class StartupForm extends JFrame implements ActionListener {

    private AddClientForm addClientForm;
    private AddFeedbackForm addFeedbackForm;
    private AddSupportEventForm addSupportEventForm;
    private AddSupportRequestForm addSupportRequestForm;
    private AddTeamForm addTeamForm;
    private AddVehicleForm addVehicleForm;
    private AddVolunteerForm addVolunteerForm;

    public StartupForm(
            AddClientForm addClientForm,
            AddFeedbackForm addFeedbackForm,
            AddSupportEventForm addSupportEventForm,
            AddSupportRequestForm addSupportRequestForm,
            AddTeamForm addTeamForm,
            AddVehicleForm addVehicleForm,
            AddVolunteerForm addVolunteerForm) {
        this.addClientForm = addClientForm;
        this.addFeedbackForm = addFeedbackForm;
        this.addSupportEventForm = addSupportEventForm;
        this.addSupportRequestForm = addSupportRequestForm;
        this.addTeamForm = addTeamForm;
        this.addVehicleForm = addVehicleForm;
        this.addVolunteerForm = addVolunteerForm;
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
                addClientForm.setVisible(true);
                break;
            case "addFeedback":
                addFeedbackForm.setVisible(true);
                break;
            case "addSupportEvent":
                addSupportEventForm.setVisible(true);
                break;
            case "addSupportRequest":
                addSupportRequestForm.setVisible(true);
                break;
            case "addTeam":
                addTeamForm.setVisible(true);
                break;
            case "addVehicle":
                addVehicleForm.setVisible(true);
                break;
            case "addVolunteer":
                addVolunteerForm.setVisible(true);
                break;
            case "cancel":
                System.exit(0);
                break;
        }
    }


}
